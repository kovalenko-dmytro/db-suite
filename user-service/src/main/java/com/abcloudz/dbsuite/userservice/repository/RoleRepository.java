package com.abcloudz.dbsuite.userservice.repository;

import com.abcloudz.dbsuite.userservice.model.Role;
import com.abcloudz.dbsuite.userservice.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(UserRole user);
}
