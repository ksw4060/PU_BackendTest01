package example2.demo2.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/api/v1/members", "/members").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("api/v1/members/{id}").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
        );

        http
                .formLogin((auth) -> auth.loginPage("/members/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );

        http
                .csrf((auth) -> auth.disable());

        return http.build();
    }
}
