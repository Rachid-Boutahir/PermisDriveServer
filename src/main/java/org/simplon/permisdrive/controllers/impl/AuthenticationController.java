package org.simplon.permisdrive.controllers.impl;


import lombok.RequiredArgsConstructor;
import org.simplon.permisdrive.dtos.requests.AuthenticationRequest;
import org.simplon.permisdrive.dtos.requests.RegisterRequest;
import org.simplon.permisdrive.dtos.responses.AuthenticationResponse;
import org.simplon.permisdrive.services.impl.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authentification")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}