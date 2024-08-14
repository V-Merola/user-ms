package com.vincenzomerola.user_ms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/public")
public class ApiTets {

	  @GetMapping("/test")
	    public ResponseEntity<String> testEndpoint() {
	        return ResponseEntity.ok("This is a test response ok");
	    }
}
