package com.fresh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresh.model.Compensation;
import com.fresh.model.Penalty;
import com.fresh.model.Voc;
import com.fresh.persistence.VocRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VocService {
	
	@Autowired
	private VocRepository vocRepo;
	
	private void validate(final Voc entity) {
		if (entity == null) {
			throw new RuntimeException("entity cannot be null");
		}
		
	}
	
	public List<Voc> create(final Voc entity){
		
		validate(entity);
		
		Compensation compensation = new Compensation();
		compensation.setCompensationValue("");
		compensation.setVoc(entity);
		
		Penalty penalty = new Penalty();
		penalty.setVoc(entity);
		
		vocRepo.save(entity);
		log.info("entity ID : {} is saved " , entity.getVocId());
		return vocRepo.findByVocId(entity.getVocId());
	}
	
	public List<Voc> retrieve(){
		return vocRepo.findAll();
	}
	
}
