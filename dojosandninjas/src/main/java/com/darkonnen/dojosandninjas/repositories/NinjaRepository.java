package com.darkonnen.dojosandninjas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.dojosandninjas.models.Ninja;

@Repository 
public interface NinjaRepository extends CrudRepository<Ninja, Long> {
	List<Ninja> findAll();
	
	// Exposición de métodos CRUD
}