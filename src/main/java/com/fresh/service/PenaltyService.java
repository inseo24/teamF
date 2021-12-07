package com.fresh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresh.model.Penalty;
import com.fresh.model.Voc;
import com.fresh.persistence.PenaltyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
public class PenaltyService {

	
	@Autowired
	private PenaltyRepository penalRepo;
	
	
	private void validate(final Penalty entity) {
		if (entity == null) {
			throw new RuntimeException("entity cannot be null");
		}
	}
	
	public List<Penalty> create(final Penalty entity, Voc vocEntity){
		
		validate(entity);
		
		entity.setVoc(vocEntity);
		entity.setDelChk(entity.getDelChk());
		entity.setIssued(entity.getIssued());
		entity.setPenaltyDescription(entity.getPenaltyDescription());
		
		penalRepo.save(entity);
		
		return penalRepo.findByPenaltyId(entity.getPenaltyId());
		
	}
	
	
	public List<Penalty> update(final Penalty entity, Integer delChk){
		
		validate(entity);
		Penalty penalEntity = penalRepo.getById(entity.getPenaltyId());
		
		penalEntity.setDelChk(entity.getDelChk());
		penalEntity.setIssued(entity.getIssued());
		penalEntity.setPenaltyDescription(entity.getPenaltyDescription());
		
		penalRepo.save(penalEntity);
		
		return penalRepo.findByPenaltyId(penalEntity.getPenaltyId());
		
	}
	
	public List<Penalty> retrieve() {
		return penalRepo.findAll();
	}

}
