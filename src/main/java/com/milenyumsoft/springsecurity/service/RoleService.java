package com.milenyumsoft.springsecurity.service;

import com.milenyumsoft.springsecurity.modelo.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService{
    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Role update(Role role) {
        return null;
    }
}
