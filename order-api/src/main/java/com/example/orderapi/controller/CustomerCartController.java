package com.example.orderapi.controller;

import com.example.orderapi.domain.product.AddProductCartForm;
import com.example.orderapi.domain.redis.Cart;
import com.example.orderapi.service.CartService;
import com.example.zerobasedomain.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/cart")
@RequiredArgsConstructor
public class CustomerCartController {

    // 임시 코드
    private final CartService cartService;
    private final JwtAuthenticationProvider provider;

    @PostMapping
    public ResponseEntity<Cart> addCart(
            @RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestBody AddProductCartForm form) {
        return ResponseEntity.ok(cartService.addCart(provider.getUserVo(token).getId(), form));
    }

    @GetMapping
    public ResponseEntity<Cart> showCart(
            @RequestHeader(name = "X-AUTH-TOKEN") String token) {
        return ResponseEntity.ok(cartService.getCart(provider.getUserVo(token).getId()));
    }
}
