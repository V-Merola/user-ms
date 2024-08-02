package com.vincenzomerola.user_ms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    // Assicurati di non trattare Role come una relazione JPA se è solo un enum
    @ElementCollection(targetClass = com.vincenzomerola.user_ms.RoleEnum.Role.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "permission_roles")
    @Column(name = "role")
    private Set<com.vincenzomerola.user_ms.RoleEnum.Role> roles;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<com.vincenzomerola.user_ms.RoleEnum.Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<com.vincenzomerola.user_ms.RoleEnum.Role> roles) {
        this.roles = roles;
    }
}
