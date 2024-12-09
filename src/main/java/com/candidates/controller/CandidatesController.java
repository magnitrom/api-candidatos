package com.candidates.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.candidates.entities.Candidates;
import com.candidates.enums.STATE;
import com.candidates.models.JwtResponse;
import com.candidates.models.Response;
import com.candidates.models.dto.CandidatesDto;
import com.candidates.security.JwtTokenUtil;
import com.candidates.security.JwtUserDetailsService;
import com.candidates.services.CandidatesServices;
import com.candidates.utils.Constantes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para gestionar las operaciones relacionadas con la entidad
 * {@link Candidates}. Este controlador expone los endpoints para realizar
 * operaciones CRUD sobre los candidatos.
 * 
 * <p>
 * Los métodos de este controlador permiten actualizar y eliminar
 * registros de candidatos.
 * </p>
 * 
 * @author Bryan Núñez
 */
@RestController
@RequestMapping("/api/candidates")
@Tag(name = "Candidates", description = "Servicios que no requieren autenticaión")
public class CandidatesController {

	@Autowired
	@Qualifier("candidateServices")
	CandidatesServices candidateServices;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	/**
	 * Realiza el inicio de sesión para obtención del token
	 * @param authenticationRequest Objeto qe contiene el usuario y la contraseña
	 * @return Retorna un objeto con el token obtenido a partir del usuario y contraseña.
	 */
	@PostMapping(path = "/login")
	@Operation(tags = "Login", summary="Realiza el inicio de sesión", description = "Realiza el inicio de sesión para obtención del token")
	public ResponseEntity<Object> login(@RequestParam String username, @RequestParam  String password) {
	
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			final String token = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new JwtResponse(token));
		}
		catch(BadCredentialsException ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constantes.BAD_CREDENTIALS);
		} 
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	
	}

	/**
	 * Realiza la actualización del registro de un Candidato
	 * @param candidate Objeto que contiene toda la información del candidato a actualizar
	 * @return Retorna un objeto con el estado de la petición 
	 */
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Candidates", summary="Actualiza un Candidato", description = "Realiza la actualización del registro de un Candidato")
    public ResponseEntity<Response<String>>  updateCandidate(@RequestBody CandidatesDto candidate) {
		Response<String> resp = candidateServices.updateCandidate(candidate);

		if(resp.getEstado().equals(STATE.NOT_FOUND))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
		else if(resp.getEstado().equals(STATE.ERROR))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		else
			return  ResponseEntity.ok(resp);
		
    }

	/**
	 * Realiza el proceso de eliminación del registro de un Candidato
	 * @param idCandidate Id del registro a eliminar
	 * @return Retorna un objeto con el estado de la petición 
	 */
	@DeleteMapping(path="/{idCandidate}", produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(tags = "Candidates", summary="Elimina un Candidato", description = "Realiza el proceso de eliminación del registro de un Candidato")
    public ResponseEntity<Response<String>> deleteCandidate(@PathVariable Long idCandidate) {
		Response<String> resp = candidateServices.deleteCandidate(idCandidate);
		
		if(resp.getEstado().equals(STATE.NOT_FOUND))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
		else if(resp.getEstado().equals(STATE.ERROR))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		else
			return  ResponseEntity.ok(resp);
		
    }

}
