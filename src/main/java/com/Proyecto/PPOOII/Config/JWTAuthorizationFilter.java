package com.Proyecto.PPOOII.Config;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.Proyecto.PPOOII.Config.Model.Constans.*;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    // ✅ MÉTODO CORREGIDO (evita NullPointerException)
    private Claims setSigningKey(HttpServletRequest request) {

        String header = request.getHeader(HEADER_AUTHORIZACION_KEY);

        // 🔴 Validar que exista header
        if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)) {
            return null;
        }

        String jwtToken = header.replace(TOKEN_BEARER_PREFIX, "");

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SUPER_SECRET_KEY))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    // ✅ AUTENTICACIÓN
    private void setAuthentication(Claims claims) {

        List<String> authorities = (List<String>) claims.get("authorities");

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        claims.getSubject(),
                        null,
                        authorities.stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList())
                );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    // ✅ VALIDACIÓN BÁSICA DEL TOKEN
    private boolean isJWTValid(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER_AUTHORIZACION_KEY);
        return authenticationHeader != null && authenticationHeader.startsWith(TOKEN_BEARER_PREFIX);
    }

    // ✅ FILTRO PRINCIPAL CORREGIDO
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain)
                                
        throws ServletException, IOException {
            // 🔴 PERMITIR LOGIN SIN JWT
        if (request.getServletPath().equals("/authenticate")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            if (isJWTValid(request)) {

                Claims claims = setSigningKey(request);

                if (claims != null && claims.get("authorities") != null) {
                    setAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }

            } else {
                SecurityContextHolder.clearContext();
            }

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }
}