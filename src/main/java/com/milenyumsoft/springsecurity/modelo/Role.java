package com.milenyumsoft.springsecurity.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    //Usamos Set poruqe no pernite repetidos
    //List permite repetidos
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="roles_permissions", joinColumns =  @JoinColumn(name="role_id"),
     inverseJoinColumns = @JoinColumn(name="permission_id"))
    private Set<Permission> permissionsList = new HashSet<>();
}
