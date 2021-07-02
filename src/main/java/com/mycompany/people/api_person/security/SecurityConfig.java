package com.mycompany.people.api_person.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

// Acknowledgement: This feature would not be possible without the
// guidance from https://howtodoinjava.com/spring-rest/custom-token-auth-example/
@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${application.api.tokenName}")
    private String authHeaderName; //= "X-AUTH-KEY";

    @Value("${application.api.tokenValue}")
    private String authHeaderValue; // = "abacus";

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        System.err.println(" [TRACE] Entered method. SecurityConfig.configure() ");
        System.err.printf(" [TRACE] SecuriyConfig.config() API-HEADER = %s ; API-KEY=%s \n"
                            , authHeaderName, authHeaderValue);

        TokenHeaderFilter filter = new TokenHeaderFilter(authHeaderName);

        filter.setAuthenticationManager(new AuthenticationManager()
        {
            @Override
            public Authentication authenticate(Authentication authentication)
                    throws AuthenticationException
            {
                String principal = (String) authentication.getPrincipal();

                if (!authHeaderValue.equals(principal))
                {
                    throw new BadCredentialsException("The API key was not found "
                            + "or not the expected value.");
                }
                authentication.setAuthenticated(true);
                return authentication;
            }
        });

        httpSecurity.
                antMatcher("/api/**")
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .addFilterBefore(new ExceptionTranslationFilter(
                                new Http403ForbiddenEntryPoint()),
                        filter.getClass()
                )
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

}