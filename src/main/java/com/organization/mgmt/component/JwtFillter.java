package com.organization.mgmt.component;

import com.organization.mgmt.service.AdminUserDetailsService;
import com.organization.mgmt.service.EmployeeUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFillter extends OncePerRequestFilter {

    @Autowired
    private AdminUserDetailsService adminService;

    @Autowired
    private EmployeeUserDetailsService employeeService;

    @Autowired
    private JwtUtility jwtUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String username = jwtUtility.extractUserName(token);


            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails;
                try {
                    userDetails = adminService.loadUserByUsername(username);
                    System.out.println("user found");
                } catch (Exception e) {
                    userDetails = employeeService.loadUserByUsername(username);
                    System.out.println("user found");
                }
                if (jwtUtility.validateToken(token, userDetails)) {
                    System.out.println("Validated found");
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            }

        }

        filterChain.doFilter(request, response);
    }
}
