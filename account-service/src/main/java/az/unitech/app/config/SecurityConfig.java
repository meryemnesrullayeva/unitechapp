package az.unitech.app.config;

import az.unitech.app.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

        private final JwtFilter jwtFilter;

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
                return web -> web.ignoring().requestMatchers("/swagger-ui/**",
                                "/v1/api-docs/**");
        }

        /**
         * Configures the security filter chain to be used for the application.
         *
         * @param http the HttpSecurity instance to be used for configuring the security
         *             chain.
         * @return the SecurityFilterChain instance for the configured HttpSecurity
         *         object.
         */
        @Bean
        protected SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
                http = http.httpBasic().disable()
                                .csrf().disable();

                http = http.sessionManagement()
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and();

                http = http.cors().and()
                                .exceptionHandling()
                                .authenticationEntryPoint(
                                                (request, response, ex) -> response.sendError(
                                                                HttpServletResponse.SC_UNAUTHORIZED,
                                                                ex.getMessage()))
                                .and();

                http
                                .authorizeHttpRequests()
                                .requestMatchers("/api-docs/swagger-config", "/swagger-ui.html", "/swagger-ui/**",
                                                "/api-docs/**")
                                .permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        /**
         * Provides a password encoder for use with the application.
         *
         * @return a BCryptPasswordEncoder instance.
         */
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
