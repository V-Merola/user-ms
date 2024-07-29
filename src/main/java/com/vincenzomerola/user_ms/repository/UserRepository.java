package com.vincenzomerola.user_ms.repository;

import com.vincenzomerola.user_ms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Metodi di ricerca personalizzati possono essere aggiunti qui
    //Ad esempio:
    Optional<User> findByEmail(String email);
}
