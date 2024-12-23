package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.dto.UserProfileDto;
import com.vecnavelopers.dndbeyond.model.Character;
import com.vecnavelopers.dndbeyond.model.ClassSummary;
import com.vecnavelopers.dndbeyond.model.User;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import com.vecnavelopers.dndbeyond.repository.UserRepository;
import com.vecnavelopers.dndbeyond.service.ClassService;
import com.vecnavelopers.dndbeyond.service.CurrentUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private final ClassService classService;

    private final CurrentUserService currentUserService;

    public ProfileController(ClassService classService, CurrentUserService currentUserService, CharacterRepository characterRepository) {
        this.classService = classService;
        this.currentUserService = currentUserService;
        this.characterRepository = characterRepository;
    }

    private String getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated.");
        }
        return authentication.getName();
    }

    private String getAuthenticatedUserDisplayName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        String displayName = authentication.getName();
        return (displayName != null && !displayName.trim().isEmpty()) ? displayName : null;
    }

    @GetMapping("/")
    public String homePageRedirect() {
        try {
            String displayName = getAuthenticatedUserDisplayName();
            if (displayName == null || displayName.isEmpty()) {
                return "redirect:/profile/setup";
            }

            Optional<User> userOptional = userRepository.findUserByDisplayName(displayName);

            return userOptional.map(user -> "redirect:/profile/" + user.getId()).orElse("redirect:/profile/setup");

        } catch (IllegalStateException e) {
            // Handle unauthenticated access gracefully
            return "redirect:/login";
        }
    }


    @GetMapping("/profile/{userId}")
    public ModelAndView viewProfile(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user has a display name
        if (user.getDisplayName() == null || user.getDisplayName().isEmpty()) {
            // Redirect to the setup page if no display name is set
            return new ModelAndView("redirect:/profile/setup");
        }

        // If display name is set, return the profile page
        ModelAndView profilePage = new ModelAndView("profile-page");
        profilePage.addObject("user", user);

        return profilePage;
    }


    @GetMapping("/profile/setup")
    public String showProfileSetupPage () {
        String displayName = getAuthenticatedUserDisplayName();
        boolean userExists = userRepository.findUserByDisplayName(displayName).isPresent();
        if (userExists) {
            User existingUser = userRepository.findUserByDisplayName(displayName).orElseThrow();
            return "redirect:/profile/" + existingUser.getId();
        }
        return "profile-setup";
    }

    @PostMapping("/profile/setup")
    public String saveProfile (@RequestParam("displayName") String displayName,
                               @RequestParam("bio") String bio,
                               @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
                               Model model){
        try {
            String auth0Id = getAuthenticatedUserId();

            User user = userRepository.findByAuth0Id(auth0Id).orElse(new User());
            user.setAuth0Id(auth0Id);
            user.setDisplayName(displayName);
            user.setBio(bio);

            if (profilePicture != null && !profilePicture.isEmpty()) {
                String fileName = profilePicture.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/uploads").toAbsolutePath();
                Files.createDirectories(uploadPath); // Ensure the directory exists
                Files.copy(profilePicture.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                user.setProfilePicture(fileName);
            }

            userRepository.save(user);
            System.out.println("Profile updated successfully!");

            return "redirect:/profile/" + user.getId(); // Redirect to the user's profile page
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "An error occurred while updating your profile.");
            return "profile-setup"; // Return to the profile setup page with an error message
        }
    }

    @GetMapping("/profile/success")
    public String showSuccessPage () {
        return "profile-success";
    }


    @GetMapping("/profile/edit")
    public String showEditProfilePage (Model model){
        String auth0Id = getAuthenticatedUserId();

        User user = userRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);

        return "edit-profile";
    }

    @PostMapping("/profile/edit")
    public String saveEditedProfile (@RequestParam("displayName") String displayName,
                                     @RequestParam("bio") String bio,
                                     @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
                                     Model model){
        try {
            String auth0Id = getAuthenticatedUserId();

            User user = userRepository.findByAuth0Id(auth0Id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            user.setDisplayName(displayName);
            user.setBio(bio);

            if (profilePicture != null && !profilePicture.isEmpty()) {
                String fileName = profilePicture.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/uploads").toAbsolutePath();
                Files.createDirectories(uploadPath); // Ensure the directory exists
                Files.copy(profilePicture.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                user.setProfilePicture(fileName);
            }

            userRepository.save(user);
            return "redirect:/profile/" + user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "An error occurred while saving your profile.");
            return "edit-profile";
        }
    }

    @GetMapping("/loggedUser")
    @ResponseBody
    public UserProfileDto getLoggedUser() {
        String auth0Id = getAuthenticatedUserId();

        User user = userRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserProfileDto(user.getId(), user.getProfilePicture());
    }

    @GetMapping("/all-characters")
    public String getUserCharacters(Model model) {
        String auth0Id = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser = userRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new IllegalStateException("User not found."));

        List<Character> characters = characterRepository.findCharactersByUserId(currentUser.getId());

        model.addAttribute("characters", characters);
        model.addAttribute("user", currentUser); // Pass the user object to the model

        if (characters.isEmpty()) {
            model.addAttribute("message", "You have no characters yet.");
        }

        return "user-characters";
    }


    @GetMapping("/character/{id}")
    public String viewCharacter(@PathVariable Long id, Model model) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        model.addAttribute("character", character);
        return "character-sheet";
    }

    @GetMapping("/character/edit/{id}")
    public ModelAndView chooseClass(@PathVariable Long id) {
        ModelAndView classSelectionPage = new ModelAndView("class-selection");
        List<ClassSummary> classSummaryList = classService.getAllClasses();
        Long currentUserId = currentUserService.getCurrentUserId();
        classSelectionPage.addObject("classSummaryList", classSummaryList);
        classSelectionPage.addObject("userId", currentUserId);
        classSelectionPage.addObject("characterId", id);
        return classSelectionPage;
    }

    @GetMapping("/character/copy/{id}")
    public String copyCharacter(@PathVariable Long id) {
        Character original = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        Character copy = new Character();
        BeanUtils.copyProperties(original, copy, "id", "createdAt", "updatedAt");
        copy.setCharacterName(original.getCharacterName() + " (Copy)");
        characterRepository.save(copy);
        return "redirect:/all-characters";
    }

    @GetMapping("/character/delete/{id}")
    public String deleteCharacter(@PathVariable Long id) {
        characterRepository.deleteById(id);
        return "redirect:/all-characters";
    }

}