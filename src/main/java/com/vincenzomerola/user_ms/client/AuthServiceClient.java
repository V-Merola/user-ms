package com.vincenzomerola.user_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/*per la comunicazione con il microservizio di autenticazione*/
@FeignClient(name = "auth-service")
public interface AuthServiceClient {

    /*
    *Invia una richiesta POST all'endpoint /auth/validate
    * del microservizio Auth per validare un token JWT.
    * Restituisce un ResponseEntity<Boleean> che indica se il
    * token è valido o meno
    */
    @PostMapping("/auth/validate")
    ResponseEntity<Boolean> validateToken(@RequestBody String token);

    /*
    * Invia una richiesta POST all'endpoint /auth/getUserRole
    * del microservizio Auth per ottenere il ruolo dell'utente
    * basato sul token JWT.
    * Restituisce ResponseEntity<String> contenente il ruolo dell'utente
    */
    @PostMapping("/auth/getUserRole")
    ResponseEntity<String> getUserRole(@RequestParam("token") String token);
}
