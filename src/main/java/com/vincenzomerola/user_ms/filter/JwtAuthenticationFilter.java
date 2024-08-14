package com.vincenzomerola.user_ms.filter;


import com.vincenzomerola.user_ms.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*Questa classe intercetta le richieste HTTP, valida i token
* e autentica l'utente nel contesto di sicurezza di Spring.
*
* Estende OncePerRequestFilter per garantire che venga eseguito una
* volta per ogni richiesta
*
* Recupera il token Jwt dall'header di autorizzazione
*
* Valida il token attraverso JwtService
*
* Se il token è valido crea un UsernamePasswordAuthenticationToken
* e lo imposta nel contesto di sicurezza di Spring
*/
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

   // @Autowired
  //  private UserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

      //  String username = null;
        String jwt = null;
        String userId = null;
        
        
        
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
          // username = jwtService.getUsernameFromToken(jwt);
            userId = jwtService.getUserIdFromToken(jwt);
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        //    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        	if (jwtService.validateToken(jwt)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userId, null, jwtService.getGrantedAuthoritiesFromToken(jwt));
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}






