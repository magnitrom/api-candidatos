package com.candidates.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candidates.entities.Candidates;
import com.candidates.enums.STATE;
import com.candidates.models.Response;
import com.candidates.models.dto.CandidatesDto;
import com.candidates.services.CandidatesServices;
import com.candidates.utils.Constantes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * Controlador REST para gestionar las operaciones relacionadas con la entidad {@link Candidates}.
 * Este controlador expone los endpoints que necesitan JWT autenticación para realizar operaciones CRUD sobre los candidatos.
 * 
 * <p>Los métodos de este controlador permiten crear y leer registros de candidatos.</p>
 * 
 * @author Bryan Núñez
 */
@RestController
@RequestMapping("/api/auth/candidates")
@Tag(name = "Candidates Auth", description = "Servicios que requieren autenticaión JWT")
public class CandidatesAuthController {

  	@Autowired
	@Qualifier("candidateServices")
	CandidatesServices candidateServices; 
	
	  	
  	/**
  	 * Obtiene todos los candidatos registrados en el sistema
  	 * @return Retorna un objeto con el estado de la petición y una lista de objetos
  	 */
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="Obtiene todos los Candidatos", description = "Obtiene todos los candidatos registrados en el sistema.", security = @SecurityRequirement(name = Constantes.SECURITY_SCHEME_NAME))
	public ResponseEntity<Response<List<Candidates>>> getAllCandidate() {
		
		Response<List<Candidates>> resp = candidateServices.getAllCandidate();
		
		if(resp.getEstado().equals(STATE.NOT_FOUND))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
		else if(resp.getEstado().equals(STATE.ERROR))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
		else
			return ResponseEntity.ok(resp);
    }
	
	/**
	 * Realiza la busqueda del cantidato realizando un filtrado por su Id.
	 * @param idCandidate Id del registro a filtrar
	 * @return Retorna un objeto con el estado de la petición y el registro solicitado por el ID
	 */
	@GetMapping(path="/{idCandidate}", produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="Obtiene el candidato a través del ID", description =  "Realiza la busqueda del cantidato realizando un filtrado por su Id.", security = @SecurityRequirement(name = Constantes.SECURITY_SCHEME_NAME))
    public ResponseEntity<Response<Candidates>> getCandidateById(@PathVariable Long idCandidate) {
		Response<Candidates> resp = candidateServices.getCandidateById(idCandidate);
				
		if(resp.getEstado().equals(STATE.NOT_FOUND))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
		else
			return  ResponseEntity.ok(resp);
    }
	
	/**
	 * Realiza el proceso de Creación de Candidato
	 * @param candidate Objeto que contiene toda la información del Candidato a crear
	 * @return Retorna un objeto con el estado de la petición y el id del registro creado
	 */
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="Crea un Candidato", description = "Realiza el proceso de Creación de Candidato", security = @SecurityRequirement(name = Constantes.SECURITY_SCHEME_NAME))
    public ResponseEntity<Response<Long>>  createCandidate(@RequestBody CandidatesDto candidate) {
		Response<Long> resp = candidateServices.createCandidate(candidate); 
		
		if(resp.getEstado().equals(STATE.ERROR))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		else
			return  ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
	
	
	
}
