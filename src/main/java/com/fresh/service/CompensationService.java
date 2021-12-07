package com.fresh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresh.dto.PenaltyDTO;
import com.fresh.model.Compensation;
import com.fresh.model.Penalty;
import com.fresh.model.Voc;
import com.fresh.persistence.CompensationRepository;
import com.fresh.persistence.VocRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompensationService {

	@Autowired
	private CompensationRepository comRepo;
	
	@Autowired
	public VocRepository vocRepo;
	
	@Autowired
	public PenaltyService penaltyservice;
	
	
	private void validate(final Compensation entity) {
		if (entity == null) {
			throw new RuntimeException("entity cannot be null");
		}
		
	}
	
	public List<Compensation> create(final Compensation entity, Integer vocId, String name){
		
		validate(entity);
		
		Voc vocEntity = vocRepo.getById(vocId);
		
		log.info("voc Entity: " + vocEntity);
		
		vocEntity.setVocId(vocId);
		vocEntity.setCompensations(entity);
		
		entity.setVoc(vocEntity);
		entity.setCompensationValue(entity.getCompensationValue());
		 
		if (vocEntity.getFaultIdentifier() == 1) {
			PenaltyDTO dto = new PenaltyDTO();
			dto.setDelChk(0);
			dto.setIssued(0);
			dto.setName(name);
			dto.setPenaltyCost(entity.getCompensationValue());
			dto.setVoc(vocEntity);
			// null, 0, name, entity.getCompensationValue().toString(), 0, vocEntit
			Penalty penaltyEntity = PenaltyDTO.toEntity(dto);
			
			List<Penalty> entities = penaltyservice.create(penaltyEntity, vocEntity);
	
			vocEntity.setPenalties(entities);
		} else {
			
		}
		
		comRepo.save(entity);
		log.info("entity ID : {} is saved " , entity.getCompensationId());
		
		return comRepo.findByCompensationId(entity.getCompensationId());
		
	}
	
	
	public List<Compensation> retrieve() {
		return comRepo.findAll();
	}
}
