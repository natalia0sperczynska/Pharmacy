package org.example.pharmacy.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.pharmacy.services.authService.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // filter1 -> filter2 -> filter3 ... filter chain if one is breaking we stop execution
        try{
            final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            final String jwt;

            if(authHeader==null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request,response);
                return;
            }

            jwt = authHeader.substring(7);
            final String username = jwtService.extractUsername(jwt);
            final String role = jwtService.extractRole(jwt).name();

            if(username!= null && !username.isEmpty() && SecurityContextHolder.getContext().getAuthentication()==null){
                if(jwtService.isTokenValid(jwt)){
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null, List.of(new SimpleGrantedAuthority(role)));
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                     securityContext.setAuthentication(authenticationToken);
                     SecurityContextHolder.setContext(securityContext);
                }
            }
            filterChain.doFilter(request,response);
        } catch (Exception e){
            filterChain.doFilter(request,response);
        }
    }
}
