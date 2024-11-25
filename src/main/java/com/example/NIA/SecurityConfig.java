package com.example.NIA;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;

import java.util.List;
import java.util.Objects;
@Configuration
public class SecurityConfig {

    @Bean // userLoggedInProvider metodu, Spring konteyneri tarafından bir bean olarak yönetilsin.
    public AvailabilityProvider userLoggedInProvider() {
        return () -> //Kullanıcının güvenlik bağlamını (SecurityContext) kontrol eder ve doğrulama bilgisi olup olmadığını kontrol eder.
                Objects.nonNull(SecurityContextHolder.getContext().getAuthentication()) // Eğer SecurityContext'te Authentication objesi mevcutsa
                        && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() // Kullanıcı doğrulandıysa
                        ? Availability.available()  // Kullanıcı giriş yapmışsa, bu komutun çalışmasına izin verir.
                        : Availability.unavailable("You are not logged in!"); // Kullanıcı giriş yapmamışsa, komut çalışmaz ve "You are not logged in!" mesajı gösterilir.
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { // şifreleme algoritmasıdır.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin =
                User.builder()
                        .username("admin")
                        .password("1234")
                        .passwordEncoder(x -> passwordEncoder().encode(x))
                        .roles("USER")
                        .build();
        // Bu kullanıcıyı bir InMemoryUserDetailsManager'a ekliyoruz. Bu, bellekteki kullanıcı bilgilerini yöneten bir bileşendir.
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        // DaoAuthenticationProvider, kullanıcı doğrulama işlemini sağlayan bir Spring Security bileşenidir.
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        // ProviderManager, doğrulama sağlayıcılarını yöneten bir bileşendir. Burada DaoAuthenticationProvider'ı ekle.
        return new ProviderManager(List.of(authenticationProvider)); // authenticationManager, doğrulama işlemlerini yönetecek olan AuthenticationManager'ı döndür.
    }
}