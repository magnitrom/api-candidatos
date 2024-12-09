package com.candidates.utils;

/**
 * Clase que contiene todos los mensajes constantes utilizadas en la API.
 * @author Bryan Núñez
 */
public final class Constantes {

    private Constantes(){
        //Constructor
    }

    public static final String MSJ_ERROR = "No se pudo realizar la tarea ERR: %s ";
    public static final String MSJ_SIN_INFO = "No se encontró información ";
    public static final String MSJ_EXITOSO = "Se realizó la tarea con Éxito ";    
    public static final String MSJ_CREATE = "Se creo el candidato con Éxito: %s ";
    public static final String MSJ_UPDATE = "Se actualizó el candidato con Éxito ";
    public static final String MSJ_UPDATE_NOT_FOUND = "No se encontró el candidato a actualizar.";
    public static final String MSJ_UPDATE_NOT_USER = "El campo UserUpdate es necesario para la actualización.";
    public static final String MSJ_DELETE = "Se eliminó el candidato con Éxito ";
    public static final String MSJ_DELETE_NOT_FOUND = "No se encontró el candidato a eliminar.";
    public static final String SECURITY_SCHEME_NAME = "Bearer Authentication";
    public static final String API_TITLE = "API - Candidatos";
    public static final String API_DESCRIPTION = "API encargada de realizar las operaciones CRUD a los Candidatos";
    public static final String API_VERSION = "1.0";
    public static final String CONTACT_NAME = "Bryan Núñez";
    public static final String CONTACT_EMAIL = "bryan_nupi@hotmail.com";
    public static final String CONTACT_URL = null; 
    public static final String BEARER_FORMAT = "JWT";
    public static final String SCHEME = "bearer";
    public static final String BAD_CREDENTIALS = "Credenciales Invalidas";
    public static final String USER_NOT_FOUND = "Usuario no encontrado: %s ";
    public static final String MSJ_FORBIDDEN = "No tiene permiso para acceder";
    public static final String MSJ_JWT_EXPIRED = "El token ya expiró.";
    public static final String MSJ_JWT_ERROR = "No se puede obtener el token JWT.";
    public static final String MSJ_JWT_HEADER = "Authorization";
    public static final String MSJ_JWT_AUTH_TOKEN = "Bearer ";

}
