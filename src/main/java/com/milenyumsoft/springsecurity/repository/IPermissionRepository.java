package com.milenyumsoft.springsecurity.repository;

import com.milenyumsoft.springsecurity.modelo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {
}
