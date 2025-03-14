package com.milenyumsoft.springsecurity.service;

import com.milenyumsoft.springsecurity.modelo.Permission;
import com.milenyumsoft.springsecurity.modelo.Role;
import com.milenyumsoft.springsecurity.repository.IPermissionRepository;
import com.milenyumsoft.springsecurity.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IPermissionRepository permissionRepository;
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll() ;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return  roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
                roleRepository.deleteById(id);
    }

    @Override
    public Role update(Role role) {
        System.out.println("Bine");
        return roleRepository.save(role);
    }
}
