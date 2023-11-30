package com.crud.demo_crud.repository;

import java.util.List;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crud.demo_crud.model.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {
	// List<Pista> findByPublished(boolean published);
	// List<Pista> findByTitleContaining(String title);


	@Query(value = "SELECT * FROM sport m ORDER BY m.price DESC LIMIT :limit", nativeQuery = true)
	List<Sport> findSportScroll(@Param("limit") Integer limit);
}
