package com.crud.demo_crud.repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.demo_crud.model.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {
	// List<Pista> findByPublished(boolean published);
	// List<Pista> findByTitleContaining(String title);
}
