package com.candidates.enums;

/**
 * Enum que representa los posibles estados de una operación.
 * Se utiliza para indicar el resultado de operaciones en la aplicación.
 * @author Bryan Núñez
 */
public enum STATE {
		 /**
		 * Estado que indica que la operación finalizó satisfactoriamente
		 */
	    SUCCESS,
	    /**
		 * Estado que indica que la operación finalizó con un error
		 */
        ERROR,
       
        /**
		 * Estado que indica que la operación no encontró los recursos solicitados
		 */
        NOT_FOUND
}
