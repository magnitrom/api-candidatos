package com.candidates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.candidates.entities.Candidates;

/**
 * Interfaz de repositorio para la entidad {@link Candidates}
 * Extiende de {@link JpaRepository} para proporcionar operaciones CRUD
 * y otras funcionalidades de persistencia de datos.
 * 
  * <p>Esta interfaz se usa para realizar operaciones de acceso a la base de datos
 * relacionadas con la entidad {@link Candidates}.</p>
 * 
 * @author Bryan Núñez
 */
public interface CandidatesRepository extends JpaRepository<Candidates, Long>{

	/**
	 * Metodo que retorna un registro a partir del id enviado
	 * @param idCandidate Id del candidato a recuperar
	 * @return retorna un objeto del registro con la información del candidato
	 */
    @Query(value = "SELECT * FROM candidates WHERE id = ?1 ", nativeQuery = true)
	public Candidates getCandidateById(@Param("idCandidate") Long idCandidate);

}