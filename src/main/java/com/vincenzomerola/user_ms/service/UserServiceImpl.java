package com.vincenzomerola.user_ms.service;

/*
 * Il metodo updateUser della classe UserServiceImpl
 * utilizzerà questa classe UpdateUserRequest per aggiornare i
 *  dettagli dell'utente nel database.
 *
 *Ottenere l'utente dal repository:
 *Utilizzare l'ID utente per cercare l'utente esistente nel repository.
 *Se l'utente non viene trovato, lanciare un'eccezione
 *UserNotFoundException.
 *Aggiornare i dettagli dell'utente:
 * Utilizzare i dati forniti nella richiesta UpdateUserRequest
 * per aggiornare i campi rilevanti dell'utente.
 * Salvare l'utente aggiornato: Salvare l'utente aggiornato nel repository.
 * Restituire i dettagli aggiornati dell'utente:
 * Convertire l'entità User in UserDTO e restituire
 *  l'oggetto UserDTO aggiornato.
 */

import com.vincenzomerola.user_ms.client.AuthServiceClient;
import com.vincenzomerola.user_ms.dto.UpdateUserRequest;
import com.vincenzomerola.user_ms.dto.UserDTO;
import com.vincenzomerola.user_ms.exception.UserNotFoundException;
import com.vincenzomerola.user_ms.model.User;
import com.vincenzomerola.user_ms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthServiceClient authServiceClient;

    /*
     *
     */
    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found by id " + id));
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    /*
    // Pensare di implementare un costruttore in UserDto che accetti un User
        @Override
        public UserDTO getUser2(Long id) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
            return new UserDTO(user);
        }
    */

    /*
    * Il metodo updateUser riceve un id dell'utente da aggiornare
    * ed un oggetto UpdateUserRequest contenente i nuovi dati
    * (es. fullName, email)
    */
    @Override
    public UserDTO updateUser(Long id, UpdateUserRequest request) {
        /*
        * recupero dell'utente:
        * Utilizza l'Id per cercare l'utente nel database.
        * Se non viene trovato lancia l'eccezione UserNotFoundException
        */

        /*
        * I dati vengono aggiornati con valori presenti nell'oggetto
        * UpdateUserRequest (fullName ed email)
        */
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found by id " + id
                ));

        if (request.getEmail() != null)
            user.setEmail(request.getEmail());
        if (request.getFullName() != null)
            user.setFullName(request.getFullName());

        /*Utilizzo di UserRepository per salvare l'utente*/
        userRepository.save(user);

        /*Conversione dell'oggetto User in DTO per il trasferimento
        * dei dati tra Servizio e Controller
        */
        UserDTO userDto = new UserDTO();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFullName(user.getFullName());
        userDto.setRole(user.getRole());

        return userDto;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found by id" + id));
        userRepository.delete(user);
    }

}
