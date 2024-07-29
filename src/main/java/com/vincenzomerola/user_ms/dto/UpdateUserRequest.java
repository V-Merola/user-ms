package com.vincenzomerola.user_ms.dto;

/*
* Classe responsabile dell'aggiornamento delle informazioni Utente
* Raccoglie i dati necessari per aggiornare le informazioni degli
* utenti nel microservizio user.
* L'aggiornamento delle informazioni sensibili come la password è
* delegato esclusivamente al microservizio Auth.
 */
public class UpdateUserRequest {

    private String fullName;
    private String email;


    public UpdateUserRequest(){}
    public UpdateUserRequest(String fullName, String email){
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
