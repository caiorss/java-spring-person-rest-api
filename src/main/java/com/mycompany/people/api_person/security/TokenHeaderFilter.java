package com.mycompany.people.api_person.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

// Acknowledgement: This feature would not be possible without the
// guidance from https://howtodoinjava.com/spring-rest/custom-token-auth-example/
//
public class TokenHeaderFilter extends AbstractPreAuthenticatedProcessingFilter
{

    private String authHeaderName;

    public TokenHeaderFilter(String authHeaderName) {
        this.authHeaderName = authHeaderName;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(authHeaderName);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }
}

