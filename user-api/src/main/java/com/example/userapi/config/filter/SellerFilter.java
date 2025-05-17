package com.example.userapi.config.filter;

import com.example.userapi.service.customer.CustomerService;
import com.example.userapi.service.seller.SellerService;
import com.example.zerobasedomain.domain.common.UserVo;
import com.example.zerobasedomain.domain.config.JwtAuthenticationProvider;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@WebFilter(urlPatterns = "/seller/*")
@RequiredArgsConstructor
public class SellerFilter implements Filter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final SellerService sellerService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader("X-AUTH-TOKEN");
        if (!jwtAuthenticationProvider.validateToken(token)) {
            throw new ServletException("Invalid Access");
        }
        UserVo vo = jwtAuthenticationProvider.getUserVo(token);
        sellerService.findByIdAndEmail(vo.getId(), vo.getEmail()).orElseThrow(
                () -> new ServletException("Invalid Access")
        );
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
