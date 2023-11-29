package com.crud.demo_crud.repository;

import java.util.List;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crud.demo_crud.model.Pista;

public interface PistaRepository extends JpaRepository<Pista, Long> {
	@Query(value = "SELECT pp.* FROM pista pp WHERE pp.id IN ( SELECT p.pista_id FROM pistas_sports p WHERE p.sport_id IN ( SELECT s.id FROM sport s WHERE s.sport_name IN :sports ) );", nativeQuery = true)
    List<Pista> findSportsinPistas(@Param("sports") String[] sports);





    // SELECT pp.* FROM pista pp WHERE pp.id IN ( SELECT p.pista_id FROM pistas_sports p WHERE p.sport_id IN ( SELECT s.id FROM sport s) );
}