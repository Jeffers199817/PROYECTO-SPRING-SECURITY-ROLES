package com.milenyumsoft.springsecurity.service;

import com.milenyumsoft.springsecurity.modelo.Role;
import com.milenyumsoft.springsecurity.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleRepository roleRepository;
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

        Role rol = roleRepository.getById(role.getId());

        rol.setRole(role.getRole());
        rol.setPermissionsList(role.getPermissionsList());


        return  roleRepository.save(rol);
    }
}
