package com.candidates.utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase utiliaria para encriptar la password
 * 
 * @author Bryan Núñez
 */
public class Encryption {
	
	Encryption(){
		//Constructor
	}
	
	/**
	 * Metodo que realiza el proceso de encriptación de la contraseña retornando el valor encriptado
	 * @param cadenaEncriptar Valor a Encriptar
	 * @return Valor Encriptado
	 */
	public static String passwordEncrypt(String cadenaEncriptar) {
		// Instancia del PasswordEncoder
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                
        return passwordEncoder.encode(cadenaEncriptar);
    }

}
