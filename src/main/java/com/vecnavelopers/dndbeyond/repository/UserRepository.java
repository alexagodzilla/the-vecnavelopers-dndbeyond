package com.vecnavelopers.dndbeyond.repository;

import com.vecnavelopers.dndbeyond.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findUserByDisplayName(String displayName);

    Optional<User> findByAuth0Id(String userId);

    Optional<Object> findUserByUsername(String username);
}