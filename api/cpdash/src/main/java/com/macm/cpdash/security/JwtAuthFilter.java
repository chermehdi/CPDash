package com.macm.cpdash.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtils jwtUtils;

	@Value("${jwt.header}")
	private String header;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String token = request.getHeader(header);
		String userName = jwtUtils.getEmailFromToken(token);

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails details = userDetailsService.loadUserByUsername(userName);

			if (jwtUtils.isValidToken(token, details)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(details, null,
						details.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}

		}

		chain.doFilter(request, response);
	}

}
