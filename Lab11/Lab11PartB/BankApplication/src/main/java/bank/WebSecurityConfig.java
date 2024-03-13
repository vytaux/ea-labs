package bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final DataSource dataSource;
    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws
            Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(noOpPasswordEncoder());
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/accounts/{accountNumber}/deposit").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/accounts/{accountNumber}/depositEuros").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/accounts/{accountNumber}/transfer").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/accounts/{accountNumber}/withdraw").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/accounts/{accountNumber}/withdrawEuros").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/accounts").hasRole("USER")
                        .requestMatchers("/accounts/{accountNumber}").hasRole("ADMIN")
                        .requestMatchers("/accounts").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @SuppressWarnings("deprecation")
    @Bean
    public static PasswordEncoder noOpPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}