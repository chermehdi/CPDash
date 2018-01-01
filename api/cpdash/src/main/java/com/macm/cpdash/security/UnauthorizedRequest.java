package com.macm.cpdash.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * redirect all unauthorized requests with a 403 status code
 */
@Component
public class UnauthorizedRequest implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthorized");
    }
}
