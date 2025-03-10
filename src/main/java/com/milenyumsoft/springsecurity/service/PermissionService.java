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
    public List findAll() {


        return List.of();
    }

    @Override
    public Optional findById(long id) {
        return Optional.empty();
    }

    @Override
    public Permission save(Permission permission) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Permission update(Permission permission) {
        return null;
    }
}
