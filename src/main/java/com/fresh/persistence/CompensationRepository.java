package com.fresh.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fresh.model.Compensation;

@Repository
public interface CompensationRepository extends JpaRepository<Compensation, Integer> {
	
	List<Compensation> findByCompensationId(Integer compensationId);
	
	@Query(value = "SELECT * FROM compensation WHERE voc_id = :vocId", nativeQuery = true)
	List<Compensation> retrieve(@Param("vocId") Integer vocId);
}
