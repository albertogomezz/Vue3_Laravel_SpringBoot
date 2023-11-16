package com.crud.demo_crud.repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.demo_crud.model.Pista;

public interface PistaRepository extends JpaRepository<Pista, Long> {
	// List<Pista> findByPublished(boolean published);
	// List<Pista> findByTitleContaining(String title);
}
