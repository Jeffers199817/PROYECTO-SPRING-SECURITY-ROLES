package com.milenyumsoft.springsecurity.service;

import com.milenyumsoft.springsecurity.modelo.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    public List<Role> findAll();
    public Optional<Role> findById(Long id);
    public Role save(Role role);

    public void deleteById(Long id);
    public Role update(Role role);
}
