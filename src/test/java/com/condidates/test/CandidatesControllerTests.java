package com.condidates.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.candidates.controller.CandidatesAuthController;
import com.candidates.controller.CandidatesController;
import com.candidates.entities.Candidates;
import com.candidates.enums.STATE;
import com.candidates.models.Response;
import com.candidates.models.dto.CandidatesDto;
import com.candidates.services.impl.CandidatesServicesImpl;
import com.candidates.utils.Constantes;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Clase principal de Test.
 * Donde se realizan las pruebas unitarias con Junit y Mockito al controlador
 * @author Bryan Núñez
 */
class CandidatesControllerTests{	

	@Mock
    private CandidatesServicesImpl candidatesServices;   


    @InjectMocks
    private CandidatesController candidatesController;
    
    @InjectMocks
    private CandidatesAuthController candidatesAuthController;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
	void testGetAllCandidate() {
		//Creación de Mock
		List<Candidates> mockCandidates = Arrays.asList(
				new Candidates( (long) 1, "Juan Perez", "jperez@correo.com", "masculino", (long) 3500, new Date(), "jperez"), 
				new Candidates( (long) 2, "Pablo Marmol", "pmarmol@correo.com", "masculino", (long) 3500, new Date(), "jperez"));
		Response<List<Candidates>> response = new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO, mockCandidates);
		when(candidatesServices.getAllCandidate()).thenReturn(response);
		
		//Invocación de Método
	    ResponseEntity<Response<List<Candidates>>> responseEntity = candidatesAuthController.getAllCandidate();
   
		// Asserts
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());
        verify(candidatesServices).getAllCandidate();
	}

	@Test
	void testGetAllCandidate_NotFound() {
		//Creación de Mock
		Response<List<Candidates>> response = new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);
		when(candidatesServices.getAllCandidate()).thenReturn(response);
		
		//Invocación de Método
	    ResponseEntity<Response<List<Candidates>>> responseEntity = candidatesAuthController.getAllCandidate();
   
		// Asserts
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());
        verify(candidatesServices).getAllCandidate();
	}


	@Test
	void testGetCandidateById() {
		long idCandidate = 1;
		//Creación de Mock
		Candidates mockCandidates = new Candidates( idCandidate, "Juan Perez", "jperez@correo.com", "masculino", (long) 3500, new Date(), "jperez");
		Response<Candidates> response = new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO, mockCandidates);

		when(candidatesServices.getCandidateById(idCandidate)).thenReturn(response);
	        
		//Invocación de Método
		ResponseEntity<Response<Candidates>> responseEntity  = candidatesAuthController.getCandidateById(idCandidate);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());
        verify(candidatesServices).getCandidateById(idCandidate);
	}


	@Test
	void testGetCandidateById_NotFound() {
		long idCandidate = 1;
		//Creación de Mock
		Response<Candidates> response = new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);

		when(candidatesServices.getCandidateById(idCandidate)).thenReturn(response);
	        
		//Invocación de Método
		ResponseEntity<Response<Candidates>> responseEntity  = candidatesAuthController.getCandidateById(idCandidate);

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());
        verify(candidatesServices).getCandidateById(idCandidate);
	}
	
	@Test
    void testCreateCandidate() {
		
		//Creación de Mock       
		long idCandidate = 1; 
        CandidatesDto candidatesDto = new CandidatesDto(idCandidate, "Juan Perez", "jperez@correo.com", "masculino", (long) 3500, new Date(), "jperez" );
        Response<Long> response = new Response<>(STATE.SUCCESS, String.format(Constantes.MSJ_CREATE, idCandidate));        
        when(candidatesServices.createCandidate(any(CandidatesDto.class))).thenReturn(response);

        //Invocación de Método
        ResponseEntity<Response<Long>> responseEntity  = candidatesAuthController.createCandidate(candidatesDto);
        
	    // Assert
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());
        verify(candidatesServices).createCandidate(any(CandidatesDto.class));
    }

	@Test
    void testUpdateCandidate() {
        //Creación del Mock
		long idCandidate = 1;
		CandidatesDto updCandidateDto = new CandidatesDto( idCandidate, "Juan Perez", "jperez@correo-nuevo.com", "masculino", (long) 3500, new Date(), "jperez");
		updCandidateDto.setUserUpdate("prueba");
		Response<String> response = new Response<>(STATE.SUCCESS, String.format(Constantes.MSJ_UPDATE, idCandidate));
		when(candidatesServices.updateCandidate(any(CandidatesDto.class))).thenReturn(response);

        //Invocación de Método
		ResponseEntity<Response<String>> responseEntity  = candidatesController.updateCandidate(updCandidateDto);
        
	    // Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());
        verify(candidatesServices).updateCandidate(any(CandidatesDto.class));
    }

    @Test
    void testDeleteCandidate() {
		//Creación de Mock
    	long idCandidate = 1;
    	Response<String> response = new Response<>(STATE.SUCCESS, Constantes.MSJ_DELETE); 
    	when(candidatesServices.deleteCandidate(idCandidate)).thenReturn(response);
       
        //Invocación de Método
    	ResponseEntity<Response<String>> responseEntity  = candidatesController.deleteCandidate(idCandidate);
        
	    // Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());
        verify(candidatesServices).deleteCandidate(any(Long.class));
    }
}
