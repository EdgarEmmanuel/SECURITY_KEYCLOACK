package core;


import org.keycloak.adapters.springsecurity.KeycloakSecurityConfig;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.authorization.KeycloakAuthorizationManager;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends KeycloakSecurityConfig {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/products").hasRole("user")
            .anyRequest().authenticated()
            .and().formLogin();
    }

    @Bean
    public KeycloakAuthenticationProvider keycloakAuthenticationProvider() {
        KeycloakAuthenticationProvider provider = new KeycloakAuthenticationProvider();
        provider.setGrantedAuthoritiesMapper(grantedAuthorities -> {
            // Optionally map Keycloak roles to Spring roles
            return grantedAuthorities.stream()
                    .map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
                    .toList();
        });
        return provider;
    }
}
