package com.example.userapi.controller;

import com.example.userapi.application.SignInApplication;
import com.example.userapi.domain.SignInForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signIn")
@RequiredArgsConstructor
public class SignInController {

    private final SignInApplication signInApplication;

    @PostMapping("/customer")
    public ResponseEntity<String> signInCustomer(@RequestBody SignInForm signInForm) {
        return ResponseEntity.ok(signInApplication.customerLoginToken(signInForm));
    }

    @PostMapping("/seller")
    public ResponseEntity<String> signInSeller(@RequestBody SignInForm signInForm) {
        return ResponseEntity.ok(signInApplication.sellerLoginToken(signInForm));
    }
}
