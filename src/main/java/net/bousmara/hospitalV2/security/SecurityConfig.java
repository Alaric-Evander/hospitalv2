package net.bousmara.hospitalV2.security;


import lombok.AllArgsConstructor;
import net.bousmara.hospitalV2.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

    /** @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return null;
            }
        };
    } **/

    private UserDetailsServiceImpl userDetailsService;

    //@Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    /**@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder().encode("123")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder().encode("123")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder().encode("123")).roles("USER", "ADMIN").build()
        );
    }**/

   /** @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        return args -> {
            if (!jdbcUserDetailsManager.userExists("user1")) {
                jdbcUserDetailsManager.createUser(User.withUsername("user1").password(passwordEncoder().encode("123")).roles("USER").build());
            }
            if (!jdbcUserDetailsManager.userExists("user2")) {
                jdbcUserDetailsManager.createUser(User.withUsername("user2").password(passwordEncoder().encode("123")).roles("USER").build());
            }
            if (!jdbcUserDetailsManager.userExists("admin")) {
            jdbcUserDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder().encode("123")).roles("USER","ADMIN").build());
        }
        };
    }**/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/user/index")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/notAuthorized")
                )

                .userDetailsService(userDetailsService)
                .rememberMe(Customizer.withDefaults());

        return http.build();
    }


}