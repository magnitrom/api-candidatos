package com.candidates.services;

import java.util.List;

import com.candidates.entities.Candidates;
import com.candidates.models.Response;
import com.candidates.models.dto.CandidatesDto;

/**
 * Interfaz que define los servicios de operaciones relacionadas con la entidad {@link Candidates}.
 * Esta interfaz proporciona métodos para manejar las operaciones de creación, lectura, actualización y eliminación de candidatos.
 * @author Bryan Núñez
 */
public interface CandidatesServices {

	/**
  	 * Obtiene todos los candidatos registrados en el sistema
  	 * @return Retorna un objeto con el estado de la petición y una lista de objetos
  	 */
	public Response<List<Candidates>> getAllCandidate();
	
	/**
	 * Realiza la busqueda del cantidato realizando un filtrado por su Id.
	 * @param idCandidate Id del registro a filtrar
	 * @return Retorna un objeto con el estado de la petición y el registro solicitado por el ID
	 */
	public Response<Candidates> getCandidateById(Long idCandidate);
	
	/**
	 * Realiza el proceso de Creación de Candidato
	 * @param candidate Objeto que contiene toda la información del Candidato a crear
	 * @return Retorna un objeto con el estado de la petición y el id del registro creado
	 */
	public Response<Long>  createCandidate(CandidatesDto candidate);

	/**
	 * Realiza la actualización del registro de un Candidato
	 * @param candidate Objeto que contiene toda la información del candidato a actualizar
	 * @return Retorna un objeto con el estado de la petición 
	 */	
	public Response<String> updateCandidate(CandidatesDto candidate);

	/**
	 * Realiza el proceso de eliminación del registro de un Candidato
	 * @param idCandidate Id del registro a eliminar
	 * @return Retorna un objeto con el estado de la petición 
	 */
	public Response<String> deleteCandidate(Long idCandidate);
}
