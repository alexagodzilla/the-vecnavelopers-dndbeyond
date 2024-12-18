package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.dto.CharacterDetailsDto;
import com.vecnavelopers.dndbeyond.model.Character;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CharacterDetailsController {
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterDetailsController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping("/characterDetails/{id}")
    public ModelAndView getCharacterDetails(@PathVariable Long id) {
        Character character = characterRepository.findById(id)
                .orElse(new Character());
        ModelAndView modelAndView = new ModelAndView( "character-details");
        modelAndView.addObject("characterId", id);
        modelAndView.addObject("characterName", character.getCharacterName());
        modelAndView.addObject("characterPicUrl", character.getCharacterPicUrl());
        modelAndView.addObject("characterDescription", character.getCharacterDescription());
        return modelAndView;

    }

    @PatchMapping("/characterDetails/{id}")
    public ResponseEntity saveCharacterDetails(@RequestBody CharacterDetailsDto dto, @PathVariable Long id){
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        character.setCharacterName(dto.getName());
        character.setCharacterPicUrl(dto.getPicUrl());
        character.setCharacterDescription(dto.getDescriprion());
        characterRepository.save(character);
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/all-characters");
        return ResponseEntity.ok(response);
    }
}
