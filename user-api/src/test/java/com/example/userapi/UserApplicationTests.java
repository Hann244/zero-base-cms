package com.example.userapi;

import com.example.userapi.config.FeignConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = FeignConfig.class)
class UserApplicationTests {

    @Test
    void contextLoads() {
    }

}
