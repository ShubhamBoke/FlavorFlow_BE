package com.example.FlavorFlow.Repository;

import com.example.FlavorFlow.Enum.Roles;
import com.example.FlavorFlow.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
