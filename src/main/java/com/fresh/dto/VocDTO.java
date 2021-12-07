package com.fresh.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fresh.model.Compensation;
import com.fresh.model.Penalty;
import com.fresh.model.Voc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VocDTO {
	
	private int vocId;
	
	@NotBlank(message="��å ������ �����Դϴ�.")
	private String faultDescription;
	
	@Min(value=0, message="0�� 1���̿��� �Է����ּ���.")
	@Max(value=1, message="0�� 1���̿��� �Է����ּ���.")
	private int faultIdentifier;

	private List<Compensation> compensation = new ArrayList<>();
	private List<Penalty> penalty = new ArrayList<>();
	
	public VocDTO(final Voc entity) {
		this.vocId = entity.getVocId();
		this.faultDescription = entity.getFaultDescription();
		this.faultIdentifier = entity.getFaultIdentifier();
		this.compensation = entity.getCompensations();
		this.penalty = entity.getPenalties();
		
	}
	
	
	public static Voc toEntity(final VocDTO dto) {
		return Voc.builder()
				.vocId(dto.getVocId())
				.faultDescription(dto.getFaultDescription())
				.compensations(dto.getCompensation())
				.faultIdentifier(dto.getFaultIdentifier())
				.penalties(dto.getPenalty())
				.build();
	}

}
