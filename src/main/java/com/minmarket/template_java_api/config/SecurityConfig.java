package com.minmarket.template_java_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Value("${app.security.ignored}")
    private String[] authWhiteList;

    //El siguiente método filter chain hace:
    //Se habilitan las cabeceras CORS (Cross-Origin Resource Sharing), lo que permite que la aplicación reciba peticiones desde otros dominios.
    //La autenticación básica HTTP y la protección CSRF (Cross-Site Request Forgery) se deshabilitan.
    //Las cabeceras de seguridad "X-Frame-Options" se establecen en "sameOrigin" para prevenir ataques de clickjacking.
    //La gestión de sesiones se establece como "STATELESS", lo que significa que no se mantendrán estados de sesión en el lado del servidor.
    //Se establecen reglas de autorización para las solicitudes HTTP. Las solicitudes a las URL en la lista blanca "authWhiteList" se permiten sin autenticación. Cualquier otra solicitud debe estar autenticada.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(authWhiteList).permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .anyRequest().authenticated();

        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
        config.setAllowCredentials(false);
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "X-Frame-Options"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }


}