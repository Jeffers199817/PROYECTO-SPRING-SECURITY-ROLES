package com.milenyumsoft.springsecurity.service;

import com.milenyumsoft.springsecurity.modelo.Permission;
import com.milenyumsoft.springsecurity.repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService{

    @Autowired
    private IPermissionRepository permissionRepo;


    @Override
    public List<Permission> findAll() {


        return permissionRepo.findAll();
    }


    @Override
    public Optional<Permission> findById(long id) {

        return permissionRepo.findById(id);
    }

    @Override
    public Permission save(Permission permission) {

        return permissionRepo.save(permission);
    }

    @Override
    public void deleteById(Long id) {
            permissionRepo.deleteById(id);
    }

    @Override
    public Permission update(Permission permission) {

        return permissionRepo.save(permission);
    }
}
