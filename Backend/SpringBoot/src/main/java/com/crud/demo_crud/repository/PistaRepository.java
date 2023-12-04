package com.crud.demo_crud.repository;

import java.util.List;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crud.demo_crud.model.Pista;

public interface PistaRepository extends JpaRepository<Pista, Long> {

    //SPORTS FILTER
	@Query(value = "SELECT pp.* FROM pista pp WHERE pp.id IN ( SELECT p.pista_id FROM pistas_sports p WHERE p.sport_id IN ( SELECT s.id FROM sport s WHERE s.sport_name = :sports ) ) LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Pista> findSportsinPistas(@Param("sports") String sports, @Param("limit") Integer limit, @Param("offset") Integer offset);

    //ALL PISTAS WITH PAGINATION
    @Query(value = "SELECT * FROM pista LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Pista> listPistasWithoutFilters(@Param("limit") Integer limit, @Param("offset") Integer offset);


    
    //COUNTS PISTAS

    //COUNT ALL PISTAS
    @Query(value = "SELECT COUNT(*) FROM pista", nativeQuery = true)
    Integer findAllPistas();
}