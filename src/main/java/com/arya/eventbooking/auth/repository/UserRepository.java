package com.arya.eventbooking.auth.repository;

import com.arya.eventbooking.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address
     * @return an optional of user
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user exists by their email address.
     *
     * @param email the email address
     * @return true if a user exists with the given email address, false otherwise
     */
    boolean existsByEmail(String email);
}
