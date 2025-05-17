package com.example.userapi.service;

import com.example.userapi.domain.SignUpForm;
import com.example.userapi.domain.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SignUpCustomerServiceTest {

    @Autowired
    private SignUpCustomerService signUpCustomerService;

    @Test
    void signUp() {
        SignUpForm form = SignUpForm.builder()
                .name("name")
                .birth(LocalDate.now())
                .email("abcd@gamil.com")
                .password("1")
                .phone("01011112222")
                .build();
        Customer c = signUpCustomerService.signUp(form);
        assertNotNull(c.getId());
        assertNotNull(c.getCreatedAt());

    }

}