package com.template.app.infrastructure.persistence.repository;

import com.template.app.infrastructure.persistence.entities.RoleDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleDAO, Long> {

    Optional<RoleDAO> findByName(String name);
}
