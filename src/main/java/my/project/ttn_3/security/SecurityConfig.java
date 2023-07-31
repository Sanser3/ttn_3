package my.project.ttn_3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/customers/**", "/api/orders/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/api/ttns/**").hasAnyRole("USER","ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
//                .csrf(Customizer.withDefaults())
                .csrf(csrf -> csrf
                .ignoringRequestMatchers(new AntPathRequestMatcher("/api/**"))
                )
        ;
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        // Разработчик обеспечивает кодирование паролей перед сохранением в памяти
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("userpass")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("adminpass")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}