package com.example.springsinginexample.productodefinale.repository;

import com.example.springsinginexample.productodefinale.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}
