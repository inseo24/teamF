package com.fresh.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.dto.PenaltyDTO;
import com.fresh.dto.ResponseDTO;
import com.fresh.model.Penalty;
import com.fresh.service.PenaltyService;

@RestController
@RequestMapping("penalty")
public class PenaltyController {

	@Autowired
	public PenaltyService service;
	
	@PutMapping("/{delChk}")
	public ResponseEntity<?> create(@RequestBody PenaltyDTO dto, @PathVariable Integer delChk) {
		
		try {
			Penalty entity = PenaltyDTO.toEntity(dto);
			List<Penalty> entities = service.update(entity, delChk);
			List<PenaltyDTO> dtos = entities.stream().map(PenaltyDTO::new).collect(Collectors.toList());
			ResponseDTO<PenaltyDTO> response = ResponseDTO.<PenaltyDTO>builder().data(dtos).build();
			return ResponseEntity.ok().body(response);
		
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<PenaltyDTO> response = ResponseDTO.<PenaltyDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	
	@GetMapping
	public ResponseEntity<?> retrieve() {
		
		List<Penalty> entities = service.retrieve();
		List<PenaltyDTO> dtos = entities.stream().map(PenaltyDTO::new).collect(Collectors.toList());
		ResponseDTO<PenaltyDTO> response = ResponseDTO.<PenaltyDTO>builder().data(dtos).build();
		return ResponseEntity.ok().body(response);
	}

}
