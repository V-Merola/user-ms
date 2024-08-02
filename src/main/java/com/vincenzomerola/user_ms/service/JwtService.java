package com.vincenzomerola.user_ms.service;
import com.vincenzomerola.user_ms.RoleEnum.Role;
import com.vincenzomerola.user_ms.RoleEnum.RoleConverter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
/*
 * JwtService valida i token JWT ed estrae le informazioni necessarie
 * dai token JWT e dai token OAuth2.
 *
 * La secret-key è contenuta nel file application.properties
 * Utilizza la libreria jjwt per decodificare il token ed ottenere i claims
 * Verifica la firma del token e controlla se è scaduto
 * Estrae il nome utente dai claims ottenuti dal token
 */
@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    /*Restituisce la chiave di firma JWT*/
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /*Estrae i claims dal token*/
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /*Valida i token*/
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*Ottiene il nome utente dai claims del token JWT*/

    public String getUsernameFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    /*Converte i ruoli estratti dai claims in valori enum 'Role'*/

    public List<Role> getRolesFromToken(String token) {
        Claims claims = extractClaims(token);
        List<String> rolesAsString = claims.get("roles", List.class);
        return rolesAsString.stream()
                .map(role -> Role.valueOf(role.toUpperCase()))
                .collect(Collectors.toList());
    }

    /*Utilizza RoleConverter per convertire i ruoli in GrantedAuthority.
    */
    public List<GrantedAuthority> getGrantedAuthoritiesFromToken(String token) {
        List<Role> roles = getRolesFromToken(token);
        return RoleConverter.convertRolesToGrantedAuthorities(roles);
    }

    /*Ottiene la data di scadenza del token JWT*/

    public Date getExpirationDateFromToken(String token) {
        return extractClaims(token).getExpiration();
    }

    /*Ottiene l'id utente dai claims del token JWT*/

    public String getUserIdFromToken(String token) {
        Claims claims = extractClaims(token);
        return claims.get("userId", String.class);
    }
}
