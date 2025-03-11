package com.milenyumsoft.springsecurity.controller;

import com.milenyumsoft.springsecurity.modelo.Permission;
import com.milenyumsoft.springsecurity.modelo.Role;
import com.milenyumsoft.springsecurity.service.IPermissionService;
import com.milenyumsoft.springsecurity.service.IRoleService;
import com.milenyumsoft.springsecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
@PreAuthorize("denyAll()")
public class RoleController {

    @Autowired
    private IRoleService rolService;

    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private RoleService roleService;


    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id){
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role)
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


    @PatchMapping("/modificar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Role update(@RequestBody Role role){

       return roleService.update(role);
    }


}
