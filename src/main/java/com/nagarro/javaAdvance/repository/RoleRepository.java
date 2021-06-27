package com.nagarro.javaAdvance.repository;

import com.nagarro.javaAdvance.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
