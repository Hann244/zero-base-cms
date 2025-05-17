package com.example.userapi.controller;

import com.example.userapi.domain.customer.ChangeBalanceForm;
import com.example.userapi.domain.customer.CustomerDto;
import com.example.userapi.domain.model.Customer;
import com.example.userapi.exception.CustomException;
import com.example.userapi.service.customer.CustomerBalanceService;
import com.example.userapi.service.customer.CustomerService;
import com.example.zerobasedomain.domain.common.UserVo;
import com.example.zerobasedomain.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.userapi.exception.ErrorCode.NOT_FOUND_USER;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final JwtAuthenticationProvider provider;
    private final CustomerService customerService;
    private final CustomerBalanceService customerBalanceService;

    @GetMapping("/getInfo")
    public ResponseEntity<CustomerDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        UserVo vo = provider.getUserVo(token);
        Customer c = customerService.findByIdAndEmail(vo.getId(), vo.getEmail()).orElseThrow(
                () -> new CustomException(NOT_FOUND_USER));


        return ResponseEntity.ok(CustomerDto.from(c));
    }

    @PostMapping("/balance")
    public ResponseEntity<Integer> changeBalance(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                        @RequestBody ChangeBalanceForm changeBalanceForm) {

        UserVo vo = provider.getUserVo(token);
        return ResponseEntity.ok(customerBalanceService.changeBalance(vo.getId(), changeBalanceForm).getCurrentMoney());
    }

}
