package com.proyecto.san_felipe.security;

import com.proyecto.san_felipe.Repository.UserRepository;
import com.proyecto.san_felipe.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthService authService;

    private static final String SECRET_KEY = "secretsecretsecretsecretsecretsecret";

    private static final Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(authService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public class JwtTokenFilter extends OncePerRequestFilter {

        private final AuthService authService;

        public JwtTokenFilter(AuthService authService) {
            this.authService = authService;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws ServletException, IOException {
            String authorizationHeader = request.getHeader("Authorization");

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);
                System.out.println("Token recibido doFilterInternal: " + token);

                try {
                    String username = Jwts.parser()
                            .setSigningKey(secretKey)
                            .parseClaimsJws(token)
                            .getBody()
                            .getSubject();

                    System.out.println("Usuario en el token: " + username);

                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        User user = authService.getUserByUsername(username); // Método a implementar
                        if (user != null) {
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // Imprimir el stack trace para depuración
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            }

            chain.doFilter(request, response);
        }
    }

    @Service
    public static class AuthService {

        @Autowired
        private UserRepository userRepository;

        private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        public String register(String username, String password) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole("ADMIN");
            userRepository.save(user);
            return "User registered successfully";
        }

        public String login(String username, String password) throws Exception {
            Optional<User> userOpt = userRepository.findByUsername(username);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                if (passwordEncoder.matches(password, user.getPassword())) {
                    return generateToken(user);
                } else {
                    throw new Exception("Invalid credentials");
                }
            } else {
                throw new Exception("User not found");
            }
        }

        public User getUserByUsername(String username) {
            return userRepository.findByUsername(username).orElse(null);
        }

        private String generateToken(User user) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", user.getRole());

            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(user.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                    .signWith(secretKey)
                    .compact();
        }
    }
}