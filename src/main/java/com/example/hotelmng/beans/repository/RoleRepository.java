package com.example.hotelmng.beans.repository;

import com.example.hotelmng.beans.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByName(String roleName);
}
