package com.example.demo.SecurityConfig;

import com.example.demo.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {


        String auth = request.getHeader("Authorization");

        if (auth != null && auth.startsWith("Bearer ")) {
         //   System.out.println("After auth: " + SecurityContextHolder.getContext().getAuthentication());

            String token = auth.substring(7);

            if (jwtService.isValid(token)) {
                String email = jwtService.extractEmail(token);

                var authentication = new UsernamePasswordAuthenticationToken(
                        email, null, Collections.emptyList()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
