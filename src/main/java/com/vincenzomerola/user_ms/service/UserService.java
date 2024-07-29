package com.vincenzomerola.user_ms.service;
import com.vincenzomerola.user_ms.dto.UpdateUserRequest;
import com.vincenzomerola.user_ms.dto.UserDTO;
import com.vincenzomerola.user_ms.model.User;

import java.util.Optional;

/*
* Definizione dei metodi per la gestione degli utenti (Ricerca,
* aggiornamento ed eliminazione)
*/
public interface UserService {
    UserDTO getUser(Long id);
    UserDTO updateUser(Long id, UpdateUserRequest request);
    void deleteUser(Long id);
}
