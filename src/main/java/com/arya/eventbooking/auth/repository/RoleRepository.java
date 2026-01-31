package com.arya.eventbooking.auth.repository;

import com.arya.eventbooking.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Retrieves a role by its name.
     *
     * @param name the role name
     * @return an optional of role
     */
    Optional<Role> findByName(String name);
}
