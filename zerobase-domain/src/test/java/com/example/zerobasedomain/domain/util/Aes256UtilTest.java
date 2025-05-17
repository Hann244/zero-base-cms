package com.example.zerobasedomain.domain.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Aes256UtilTest {

    @Test
    void encrypt() {
        String encrypt = Aes256Util.encrypt("Hello World");
        assertEquals("Hello World", Aes256Util.decrypt(encrypt));
    }

    @Test
    void decrypt() {
    }
}