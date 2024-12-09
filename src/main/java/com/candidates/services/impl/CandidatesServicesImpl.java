package com.candidates.services.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candidates.entities.Candidates;
import com.candidates.enums.STATE;
import com.candidates.models.Response;
import com.candidates.models.dto.CandidatesDto;
import com.candidates.repository.CandidatesRepository;
import com.candidates.services.CandidatesServices;
import com.candidates.utils.Constantes;

@Service("candidateServices")
public class CandidatesServicesImpl implements CandidatesServices {

	private static final Logger logger = LoggerFactory.getLogger(CandidatesServicesImpl.class);

	@Autowired
	private CandidatesRepository candidatesDao;

	@Override
	public Response<List<Candidates>> getAllCandidate() {
		try {
			List<Candidates> listCandidates = candidatesDao.findAll();
			if (!listCandidates.isEmpty())
				return new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO, listCandidates);

			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);
		} catch (Exception ex) {
			String msjError = String.format(Constantes.MSJ_ERROR, ex.getCause().getMessage());
			logger.error(msjError, ex);
			return new Response<>(STATE.ERROR, msjError);
		}

	}

	@Override
	public Response<Candidates> getCandidateById(Long idCandidates) {
		try {
			Candidates candidato = candidatesDao.getCandidateById(idCandidates);

			if (candidato != null)
				return new Response<>(STATE.SUCCESS, Constantes.MSJ_EXITOSO, candidato);

			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_SIN_INFO);
			
		} catch (Exception ex) {
			String msjError = String.format(Constantes.MSJ_ERROR, ex.getCause().getMessage());
			logger.error(msjError, ex);
			return new Response<>(STATE.ERROR, msjError);
		}

	}

	@Override
	public Response<Long> createCandidate(CandidatesDto candidate) {
		try {
			Candidates newCandidate = new Candidates(candidate);
			newCandidate.setId((long) 0);
			newCandidate.setCreationDate(new Date());
			
			newCandidate = candidatesDao.save(newCandidate);
			
			return new Response<Long>(STATE.SUCCESS, String.format(Constantes.MSJ_CREATE, newCandidate.getId()), newCandidate.getId());
			
		} catch (Exception ex) {
			String msjError = String.format(Constantes.MSJ_ERROR, ex.getCause().getCause().getMessage());
			logger.error(msjError, ex);
			return new Response<>(STATE.ERROR, msjError);
		}
	}

	@Override
	public Response<String> updateCandidate(CandidatesDto candidate) {
		try {
			Candidates updateCandidate = new Candidates(candidate);

			if (updateCandidate.getId() != null) {
				Response<Candidates> response = getCandidateById(updateCandidate.getId());

				if (response.getEstado().equals(STATE.SUCCESS))
				{
					Candidates bckCandidate = response.getDatos();
					
					if(updateCandidate.getUserUpdate() == null || updateCandidate.getUserUpdate().isEmpty())
						return new Response<>(STATE.ERROR, Constantes.MSJ_UPDATE_NOT_USER);

					updateCandidate.setUserCreate(bckCandidate.getUserCreate());
					updateCandidate.setCreationDate(bckCandidate.getCreationDate());
					updateCandidate.setUpdateDate(new Date());
					
					candidatesDao.save(updateCandidate);
					return new Response<>(STATE.SUCCESS, String.format(Constantes.MSJ_UPDATE, updateCandidate.getId()));
				}

			}
			
			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_UPDATE_NOT_FOUND);

		} catch (Exception ex) {
			String msjError = String.format(Constantes.MSJ_ERROR, ex.getCause().getCause().getMessage());
			logger.error(msjError, ex);
			return new Response<>(STATE.ERROR, msjError);
		}
	}

	@Override
	public Response<String> deleteCandidate(Long idCandidates) {
		try {
			Response<Candidates> response = getCandidateById(idCandidates);
			
			if (response.getEstado().equals(STATE.SUCCESS))
			{
				candidatesDao.deleteById(idCandidates);
				return new Response<>(STATE.SUCCESS, Constantes.MSJ_DELETE);
			}

			return new Response<>(STATE.NOT_FOUND, Constantes.MSJ_DELETE_NOT_FOUND);

		} catch (Exception ex) {
			String msjError = String.format(Constantes.MSJ_ERROR, ex.getCause().getCause().getMessage());
			logger.error(msjError, ex);
			return new Response<>(STATE.ERROR, msjError);
		}
	}

}
