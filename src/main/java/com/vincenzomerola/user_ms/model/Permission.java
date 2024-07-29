
package com.vincenzomerola.user_ms.model;

/*
* Definisce i permessi assegnabili agli utenti. Ogni permesso ha un
* nome e puo essere collegato ad uno o piu ruoli per creare un sistema di
* autorizzazione flessibile e dettagliato.
* Questa classe permette di aggiungere, rimuovere e gestire i permessi
* nel sistema.
* La classe Permission puo essere collegata alla Enum 'Role' per
* definire quali permessi sono associati a ciascun ruolo.
* I permessi vengono utilizzati nei controlli di sicurezza per
* verificare se un utente ha il diritto di compiere una certa azione.
*/


import com.vincenzomerola.user_ms.RoleEnum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(unique = true, nullable = false)
    private String name;

    @Size(max = 255)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "role_permissions" ,
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public Permission(){}

    public Permission(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(max = 100) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 100) String name) {
        this.name = name;
    }

    public @Size(max = 255) String getDescriprion() {
        return description;
    }

    public void setDescriprion(@Size(max = 255) String descriprion) {
        this.description = descriprion;
    }


    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    //Un permesso puo essere associato a uno o piu ruoli

    //Un ruolo puo avere uno o piu permessi associati


    /*
    * I ruoli e i permessi possono essere associati tramite una tabella
    * di associazione nel database, che mantiene le relazioni tra
    * ruoli e permessi.
    */
}
