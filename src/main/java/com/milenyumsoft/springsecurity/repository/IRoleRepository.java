package com.milenyumsoft.springsecurity.repository;

import com.milenyumsoft.springsecurity.modelo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
