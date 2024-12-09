package com.condidates.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.candidates.entities.Candidates;
import com.candidates.enums.STATE;
import com.candidates.models.Response;
import com.candidates.models.dto.CandidatesDto;
import com.candidates.repository.CandidatesRepository;
import com.candidates.services.impl.CandidatesServicesImpl;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Clase principal de Test.
 * Donde se realizan las pruebas unitarias con Junit y Mockito de los servicios
 * @author Bryan Núñez
 */
class CandidatesServicesTests {
	
	@Mock
    private CandidatesRepository candidatesRepository;

    @InjectMocks
    private CandidatesServicesImpl candidatesServices;

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
	        when(candidatesRepository.findAll()).thenReturn(mockCandidates);
	        
		//Invocación de Método
		Response<List<Candidates>> response = candidatesServices.getAllCandidate();

		// Asserts
		assertEquals(STATE.SUCCESS, response.getEstado());
		verify(candidatesRepository, times(1)).findAll();
	}


	@Test
	void testGetCandidateById() {
		long idCandidate = 1;
		//Creación de Mock
		Candidates mockCandidates = new Candidates( idCandidate, "Juan Perez", "jperez@correo.com", "masculino", (long) 3500, new Date(), "jperez");
	        when(candidatesRepository.getCandidateById(idCandidate)).thenReturn(mockCandidates);
	        
		//Invocación de Método
		Response<Candidates> response = candidatesServices.getCandidateById((long) 1);

		// Assert
		assertEquals(STATE.SUCCESS, response.getEstado());
		verify(candidatesRepository, times(1)).getCandidateById(idCandidate);
	}
	
	@Test
    void testCreateCandidate() {
		
		//Creación de Mock        
        CandidatesDto candidatesDto = new CandidatesDto( (long) 1, "Juan Perez", "jperez@correo.com", "masculino", (long) 3500, new Date(), "jperez" );
        Candidates mockCandidates = new Candidates(candidatesDto);
        		
        when(candidatesRepository.save(any(Candidates.class))).thenReturn(mockCandidates);

        //Invocación de Método
        Response<Long> response = candidatesServices.createCandidate(candidatesDto);

        // Assert
        assertEquals(STATE.SUCCESS, response.getEstado());
        verify(candidatesRepository, times(1)).save(any(Candidates.class));
    }

	@Test
    void testUpdateCandidate() {
        //Creación del Mock
		long idCandidate = 1;
		Candidates oldCandidate = new Candidates( idCandidate, "Juan Perez", "jperez@correo.com", "masculino", (long) 3500, new Date(), "jperez");
		CandidatesDto updCandidateDto = new CandidatesDto( idCandidate, "Juan Perez", "jperez@correo-nuevo.com", "masculino", (long) 3500, new Date(), "jperez");
		updCandidateDto.setUserUpdate("prueba");
		Candidates updCandidate = new Candidates(updCandidateDto);
		when(candidatesRepository.getCandidateById(idCandidate)).thenReturn(oldCandidate);
        when(candidatesRepository.save(any(Candidates.class))).thenReturn(updCandidate);

        //Invocación de Método
        Response<String> response = candidatesServices.updateCandidate(updCandidateDto);

        // Assert
        assertEquals(STATE.SUCCESS, response.getEstado());
        verify(candidatesRepository, times(1)).save(any(Candidates.class));
    }

    @Test
    void testDeleteCandidate() {
		//Creación de Mock
    	long idCandidate = 1;
    	Candidates mockCandidates = new Candidates( idCandidate, "Juan Perez", "jperez@correo.com", "masculino", (long) 3500, new Date(), "jperez");
	    when(candidatesRepository.getCandidateById(idCandidate)).thenReturn(mockCandidates);
        doNothing().when(candidatesRepository).deleteById(idCandidate);

        //Invocación de Método
        Response<String> response = candidatesServices.deleteCandidate(idCandidate);

        // Assert
        assertEquals(STATE.SUCCESS, response.getEstado());
        verify(candidatesRepository, times(1)).deleteById(idCandidate);
    }
}
