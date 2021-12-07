package com.fresh.model;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Penalty")
public class Penalty {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int penaltyId;
	
	// ��� �̸�
	@NotNull
	private String name;
	
	// û�� ���
	private String penaltyCost;
	
	// �г�Ƽ ����
	private String penaltyDescription;
	
	// ��� Ȯ�� ���� : ���� 0, �� 1
	private int delChk;
	
	// �������� ���� : ���� 0, �� 1
	private int issued;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "voc_id")
	@JsonIgnore
	private Voc voc; 

	

}
