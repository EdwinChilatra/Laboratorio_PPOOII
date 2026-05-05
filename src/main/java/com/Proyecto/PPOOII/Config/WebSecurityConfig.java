package com.Proyecto.PPOOII.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Proyecto.PPOOII.Config.Model.Constans;

@EnableWebSecurity
@Configuration
class WebSecurityConfig {

     @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
          
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authz -> authz
                // 🔓 Swagger (OBLIGATORIO)
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                ).permitAll()

                // 🔓 Endpoint de login
                .requestMatchers(Constans.LOGIN_URL).permitAll()

                // 🔓 Permitir preflight (CORS)
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // 🔒 Todo lo demás protegido
                .anyRequest().authenticated()
                )
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
        }

}
