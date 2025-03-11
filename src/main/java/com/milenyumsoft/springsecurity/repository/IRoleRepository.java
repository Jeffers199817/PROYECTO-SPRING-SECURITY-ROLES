package com.milenyumsoft.springsecurity.repository;

import com.milenyumsoft.springsecurity.modelo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
