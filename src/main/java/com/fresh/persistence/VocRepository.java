package com.fresh.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fresh.model.Voc;

@Repository
public interface VocRepository extends JpaRepository<Voc, Integer> {

	List<Voc> findByVocId(Integer vocId);

}
