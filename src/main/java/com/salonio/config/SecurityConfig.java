//package com.salonio.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; // For @PreAuthorize
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity // Enables Spring Security's web security features
//@EnableMethodSecurity // Enables method-level security annotations like @PreAuthorize
//class SecurityConfig {
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless REST APIs
//                .cors(Customizer.withDefaults()) // Enable CORS (configure source bean below)
//                .authorizeHttpRequests(authorize -> authorize
//                        // Allow public access to Swagger UI and API docs
//                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
//                        // Allow public access to specific booking endpoints if needed (e.g., getting basic info)
//                        // You might change these to .authenticated() later based on your business rules
//                        .requestMatchers("/booking/staff/**").permitAll() // Example: staff availability might be public
//                        .requestMatchers("/booking/**").authenticated() // All other /booking endpoints require authentication
//                        .anyRequest().authenticated() // All other requests require authentication
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // REST API is stateless
//                )
//                .httpBasic(Customizer.withDefaults()); // Enable Basic Authentication for quick testing
//
//        return http.build();
//    }
//
//    // --- Basic In-Memory User Authentication (for quick testing) ---
//    // In a real application, you'd replace this with database authentication
//    // or JWT token validation.
//    @Bean
//    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("adminpass")) // Encode the password!
//                .roles("ADMIN") // Assign a role
//                .build();
//
//        UserDetails client = User.builder()
//                .username("client")
//                .password(passwordEncoder.encode("clientpass"))
//                .roles("CLIENT")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, client);
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Strong password encoder
//    }
//
//    // --- CORS Configuration ---
//    // Allows requests from specified origins. Adjust for your frontend application's URL.
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://your-frontend-domain.com")); // Replace with your frontend's actual origins
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("*")); // Allow all headers
//        configuration.setAllowCredentials(true); // Allow sending cookies/auth headers
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration); // Apply to all paths
//        return source;
//    }
//}
