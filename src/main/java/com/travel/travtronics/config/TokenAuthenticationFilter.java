package com.travel.travtronics.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter)
			throws ServletException, IOException {

		String token = request.getHeader("Authorization");
		boolean isSwagger = request.getRequestURI().contains("swagger-ui")
				|| (request.getHeader("referer") != null && request.getHeader("referer").contains("swagger-ui"));

		if (!isSwagger) {
			if (token == null || token.length() == 0) {
				throw new SecurityException(
						"The header Authorization is empty. Should be something like: Bearer 'TOKEN'");
			}
		}
		filter.doFilter(request, response);

	}

}
