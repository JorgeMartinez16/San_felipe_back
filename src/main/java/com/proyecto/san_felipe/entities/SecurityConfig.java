//package com.proyecto.san_felipe.entities;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/cars/register").permitAll() // Permitir acceso sin autenticación a este endpoint
//                        .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
//                )
//                .formLogin(form -> form // Configurar el inicio de sesión basado en formularios
//                        .loginPage("/login") // URL personalizada para el formulario de inicio de sesión
//                        .permitAll() // Permitir acceso a la página de inicio de sesión
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Usar BCrypt para codificar contraseñas
//    }
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder
//                .inMemoryAuthentication()
//                .withUser("user").password(passwordEncoder().encode("password")).roles("USER"); // Usuario en memoria
//
//        return authenticationManagerBuilder.build();
//    }
//}
