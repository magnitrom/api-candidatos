package com.candidates.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.candidates.utils.Constantes;
import com.candidates.utils.Encryption;

/**
 * Servicio que implementa la interfaz {@link UserDetailsService} para cargar detalles de usuario desde una fuente interna simulando como si estuviera de una fuente externa.
 * Esta clase es utilizada para la autenticación de usuarios mediante JWT.
 * 
 * @author Bryan Núñez
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Value("${spring.security.user.name}")
	private String user;
	
	@Value("${spring.security.user.password}")
	private String pass;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (user.equals(username)) {
			return new User(user, Encryption.passwordEncrypt(pass),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException( String.format(Constantes.USER_NOT_FOUND, username));
		}
	}
}
