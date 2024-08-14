package com.vincenzomerola.user_ms.controller;

import com.vincenzomerola.user_ms.dto.UpdateUserRequest;
import com.vincenzomerola.user_ms.dto.UserDTO;
import com.vincenzomerola.user_ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*GET recupera i dati di un utente specifico*/

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public UserDTO getUserById(@PathVariable Long id){
        return userService.getUser(id);
    }

    /*PUT Aggiorna i dettagli di un utente*/

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest){
        return userService.updateUser(id, updateUserRequest);
    }


    /*DELETE Elimina un utente*/
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
