package com.fresh.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "VOC")
public class Voc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vocId;
	
	// ��å ����� : ��ۻ� 1, ��� 2
	private int faultIdentifier;
		
	// ��å ����
	private String faultDescription;
	
	@OneToMany(mappedBy = "voc")
	@JsonIgnore
	private List<Penalty> penalties = new ArrayList<>(); 
	
	// ��� ���� 
	@OneToMany(mappedBy = "voc")
	@JsonIgnore
	private List<Compensation> compensations = new ArrayList<>();

	public void setCompensations(Compensation entity) {
		
	}

		


}
