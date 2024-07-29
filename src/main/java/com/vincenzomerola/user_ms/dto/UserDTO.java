package com.vincenzomerola.user_ms.dto;


import com.vincenzomerola.user_ms.RoleEnum.Role;

/*
*Data Transfer Object per trasferire i dati degli utenti tra i vari
* strati dell'applicazione, mantenendo integrità dei dati e riducendo
* le dipendenze tra le classi.
* UserDTO rappresenta una vista semplificata del modello User,
* contenente solo i campi necessari per le operazioni di imput/output.
*
* Definisce i campi che rappresentano le informazioni utente da esporre
* attraverso API.
*
*/
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private Role role;

    public UserDTO(){}

    public UserDTO(Long id, String fullName, String email, Role role){
        this.id = id;
        this.email = email;
        this.role = role;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
