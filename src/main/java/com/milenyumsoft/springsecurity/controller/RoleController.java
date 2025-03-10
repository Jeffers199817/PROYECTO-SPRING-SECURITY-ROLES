package com.milenyumsoft.springsecurity.controller;

import com.milenyumsoft.springsecurity.modelo.Permission;
import com.milenyumsoft.springsecurity.modelo.Role;
import com.milenyumsoft.springsecurity.service.IPermissionService;
import com.milenyumsoft.springsecurity.service.IRoleService;
import com.milenyumsoft.springsecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private IRoleService rolService;

    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List> getAllRoles(){
        List roles = roleService.findAll();
        return ResponseEntity.ok(roles);

    }

    @GetMapping("/{id}")
    public ResponseEntity getRoleById(@PathVariable Long id){
        Optional role = roleService.findById(id);
        return (ResponseEntity) role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity createRole(@RequestBody Role role)
    {
        Set<Permission> permissionList = new HashSet<>();
        Permission readPermission;

        //Recuperr la Permission/ s por su Id
        for ( Permission per : role.getPermissionsList()){
            readPermission = (Permission) permissionService.findById(per.getId()).orElse(null);
            if(readPermission !=null){
                // si encuentor , uaro en la lista
                permissionList.add(readPermission);
            }
        }

        role.setPermissionsList(permissionList);
        Role newRole = roleService.save(role);
        return ResponseEntity.ok(newRole);
    }


}
