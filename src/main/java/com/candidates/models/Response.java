package com.candidates.models;

import com.candidates.enums.STATE;

/**
 * Modelo de respuesta genérico para representar una respuesta de la API.
 * 
 * @param <T> Tipo de datos que la respuesta puede contener.
 * @author Bryan Núñez
 */
public class Response<T> {
    public Response() {
		//Constructor
	}
	
	private STATE estado;
	private String mensaje;
	private T datos;

	public Response(STATE estado, String mensaje, T datos) {
		this.estado = estado;
		this.mensaje = mensaje;
		this.datos = datos;
	}

	public Response(STATE estado, String mensaje) {
		this.estado = estado;
		this.mensaje = mensaje;
	}	
	
	public Response(STATE estado,  T datos) {
		this.estado = estado;
		this.datos = datos;
	}	

	public STATE getEstado() {
		return estado;
	}
	public void setEstado(STATE estado) {
		this.estado = estado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public T getDatos() {
		return datos;
	}
	public void setDatos(T datos) {
		this.datos = datos;
	}
}
