package com.vincenzomerola.user_ms.RoleEnum;

import com.vincenzomerola.user_ms.RoleEnum.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/*
 *  è una classe helper che ha il compito di convertire i ruoli
 * definiti come enum (Role) in oggetti che implementano l'interfaccia
 * GrantedAuthority. Questo è necessario perché Spring Security
 * richiede che i ruoli siano rappresentati come GrantedAuthority
 * per gestire correttamente l'autenticazione e l'autorizzazione.
 *
 * Utilizza lo stream per iterare sulla lista dei ruoli.
 *
 * roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
 * Converte ogni ruolo (Role) in un oggetto SimpleGrantedAuthority.
 * Il prefisso "ROLE_" è aggiunto per seguire la convenzione di
 * Spring Security.
 *
*/
public class RoleConverter {

    public static List<GrantedAuthority> convertRolesToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }
}