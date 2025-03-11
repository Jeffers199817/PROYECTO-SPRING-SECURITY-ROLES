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

import java.util.*;

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
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Set<Permission> permissionList = new HashSet<>();
        Permission readPermission;

        //Recuperr la Permission/ s por su Id
        for (Permission per : role.getPermissionsList()) {
            readPermission = (Permission) permissionService.findById(per.getId()).orElse(null);
            if (readPermission != null) {
                // si encuentor , uaro en la lista
                permissionList.add(readPermission);
            }
        }

        role.setPermissionsList(permissionList);
        Role newRole = roleService.save(role);
        return ResponseEntity.ok(newRole);
    }


    @PatchMapping("/modificar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {

        Role rol = roleService.findById(id).orElse(null);

        if (rol == null) {
            return ResponseEntity.notFound().build(); // Return 404 if role not found
        }
        rol.setRole(role.getRole());

        Set<Permission> listaPermission = new HashSet<>();

        for (Permission per : role.getPermissionsList()) {

                Optional<Permission> permissionOpt= permissionService.findById(per.getId());
               permissionOpt.ifPresent(listaPermission::add);
            }

        // Set the updated permissions list to the role
        rol.setPermissionsList(listaPermission);

        // Save the updated role
        roleService.update(rol);


        return ResponseEntity.ok(rol);


    }
}