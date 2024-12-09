package com.candidates.security;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.candidates.utils.Constantes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Clase que implementa {@link AuthenticationEntryPoint} para manejar el acceso no autorizado.
 * Esta clase es utilizada para gestionar la respuesta cuando un usuario intenta acceder a recursos protegidos sin
 * una autenticación válida.
 * 
 * <p>Cuando se detecta una solicitud no autenticada, se envía una respuesta con el código de estado HTTP 401 (Unauthorized)
 * y un mensaje de error.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, Constantes.MSJ_FORBIDDEN);
	}
}
