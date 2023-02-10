package be.technifutur.Benjartine.config;

import be.technifutur.Benjartine.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {

    http.csrf().disable();

    http.httpBasic().disable();
        http.addFilterBefore( jwtFilter, UsernamePasswordAuthenticationFilter.class );
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests( (authorize) -> {
            authorize
                    .requestMatchers("/sandwich/all").anonymous()
                    .requestMatchers("/sandwich/{id:[0-9]+}").anonymous()
                    .requestMatchers("/sandwich/add").hasRole("ADMIN")
                    .requestMatchers("/sandwich/update/{id:[0-9]+}").hasRole("ADMIN")
                    .requestMatchers("/sandwich/delete/{id:[0-9]+}").hasRole("ADMIN")
                    .requestMatchers("/sandwich/diet/{dietName}").anonymous()
                    .requestMatchers("/basket/add/{id:[0-9]+}").authenticated()
                    .requestMatchers("/basket/remove/{id:[0-9]+}").authenticated()
                    .requestMatchers("/basket").authenticated()
                    .requestMatchers("/basket/clear").authenticated()
                    .requestMatchers("/order/confirm").authenticated()
                    .requestMatchers("/order").authenticated()
                    .requestMatchers("/order/all").hasRole("ADMIN")
                    .requestMatchers("/order/status/{id:[0-9]+}").hasRole("ADMIN")
                    .anyRequest().permitAll();
        });

// .requestMatchers("/plane/all").anonymous()
//                .requestMatchers("/plane/add").authenticated()
//                .requestMatchers("/plane/{id:[0-9]+}/?pdate").hasRole("ADMIN")//.hasAuthority("ROLE_ADMIN")

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
