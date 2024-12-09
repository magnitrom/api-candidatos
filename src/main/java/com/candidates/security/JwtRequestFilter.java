package com.candidates.security;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.candidates.utils.Constantes;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro de seguridad personalizado que intercepta las solicitudes HTTP para validar y autenticar tokens JWT.
 * 
 * <p>Este filtro se encarga de extraer el token JWT de la cabecera de la solicitud, validarlo y autenticar al usuario
 * en el contexto de seguridad de Spring si el token es válido.</p>
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader(Constantes.MSJ_JWT_HEADER);

		String username = null;
		String jwtToken = null;
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith(Constantes.MSJ_JWT_AUTH_TOKEN)) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				logger.warn(Constantes.MSJ_JWT_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Constantes.MSJ_JWT_ERROR);
			} catch (ExpiredJwtException e) {
				logger.warn(Constantes.MSJ_JWT_EXPIRED);

				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Constantes.MSJ_JWT_ERROR);
			}
		} else {
			logger.warn(Constantes.MSJ_JWT_ERROR);
		}

		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			
			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(jwtToken, userDetails))) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

}
