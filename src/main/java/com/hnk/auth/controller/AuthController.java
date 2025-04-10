package com.hnk.auth.controller;

import com.hnk.auth.config.jwt.JwtUtils;
import com.hnk.auth.controller.payload.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        return ResponseEntity.ok(jwtUtils.generateToken(authentication));
    }

    @GetMapping("/hello")
    public String hello(){
        return "private";
    }

}
