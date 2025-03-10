package com.milenyumsoft.springsecurity.service;

import com.milenyumsoft.springsecurity.modelo.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    public List findAll();
    public Optional findById(long id);
    public Permission save(Permission permission);
    public void deleteById(Long id);
    public Permission update(Permission permission);
}
