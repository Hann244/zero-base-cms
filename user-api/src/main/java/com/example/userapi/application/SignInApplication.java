package com.example.userapi.application;

import com.example.userapi.domain.model.Seller;
import com.example.userapi.service.seller.SellerService;
import com.example.zerobasedomain.domain.config.JwtAuthenticationProvider;
import com.example.userapi.domain.SignInForm;
import com.example.userapi.domain.model.Customer;
import com.example.userapi.exception.CustomException;
import com.example.userapi.service.customer.CustomerService;
import com.example.zerobasedomain.domain.common.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.userapi.exception.ErrorCode.LOGIN_CHECK_FAIL;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final SellerService sellerService;
    private final JwtAuthenticationProvider provider;

    public String customerLoginToken(SignInForm form) {
        // 1. 로그인 가능 여부
        Customer c = customerService.findValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));
        // 2. 토큰을 발행하고

        // 3. 토큰을 response한다.
        return provider.createToken(c.getEmail(), c.getId(), UserType.CUSTOMER);
    }

    public String sellerLoginToken(SignInForm form) {
        Seller s = sellerService.findValidSeller(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));

        return provider.createToken(s.getEmail(), s.getId(), UserType.SELLER);
    }
}
