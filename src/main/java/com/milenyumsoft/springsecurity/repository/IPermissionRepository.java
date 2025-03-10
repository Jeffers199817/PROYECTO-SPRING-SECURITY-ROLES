package com.milenyumsoft.springsecurity.repository;

import com.milenyumsoft.springsecurity.modelo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionRepository extends JpaRepository<Permission, Long> {
}
