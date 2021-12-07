package com.fresh.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fresh.model.Penalty;

@Repository
public interface PenaltyRepository extends JpaRepository<Penalty, Integer> {
	
	List<Penalty> findByPenaltyId(Integer penaltyId);
}
