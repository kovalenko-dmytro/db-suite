package com.gmail.apachdima.dbsuite.userservice.repository;

import com.gmail.apachdima.dbsuite.userservice.model.Role;
import com.gmail.apachdima.dbsuite.userservice.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByRole(UserRole user);
}
