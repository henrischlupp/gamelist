package com.gamelist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


 //                       .requestMatchers("/", "/register", "/forgotpassword", "/resetpassword", "/css/**", "/api/**", "/h2console/**").permitAll()
                        

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

     @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                authorize -> authorize
                .requestMatchers("/register").permitAll()//pitää päästä rekisteröitymään
                .requestMatchers("/css/**").permitAll()//css näkyy
                .requestMatchers("/api/**").hasAuthority("ADMIN")//apilla päääsee
                //.requestMatchers("/h2-console/").permitAll() // for h2console
                .requestMatchers("/forgotpassword").permitAll()
                .requestMatchers("/resetpassword/").permitAll()
                .requestMatchers("/resetpassword").permitAll()
                .anyRequest().authenticated())
    
                .formLogin(formlogin -> 
                    formlogin.loginPage("/login")
                    .defaultSuccessUrl("/home", true)
                    .permitAll())
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf.disable()); // not for production, just for development
        return http.build();
    }

}