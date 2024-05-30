package com.template.app.infrastructure.persistence.repository;

import com.template.app.infrastructure.persistence.entities.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long> {

    // Query to find all active users
    @Query("SELECT u FROM UserDAO u WHERE u.isActive = true")
    List<UserDAO> findAllActive();

    // Query to find an active user by ID
    @Query("SELECT u FROM UserDAO u WHERE u.id = :id AND u.isActive = true")
    Optional<UserDAO> findActiveById(@Param("id") long id);

    // Query to find an active user by reference
    @Query("SELECT u FROM UserDAO u WHERE u.reference = :reference AND u.isActive = true")
    Optional<UserDAO> findActiveByReference(@Param("reference") String reference);

    // Query to find an active user by email with roles
    @Query("SELECT u FROM UserDAO u LEFT JOIN FETCH u.roles WHERE u.email = :email AND u.isActive = true")
    Optional<UserDAO> findActiveByEmail(@Param("email") String email);

    boolean existsByEmail(String email);

}
