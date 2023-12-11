package com.crud.demo_crud.repository;

import java.util.List;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crud.demo_crud.model.Pista;

public interface PistaRepository extends JpaRepository<Pista, Long> {
	@Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name IN :sports ;",  nativeQuery = true)
   List<Pista> findSportsinPistas(@Param("sports") String[] sports);


     // Only order DESC
     //Mas caro a mas barato
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id ORDER BY s.price DESC;", nativeQuery = true)
   List<Pista> OrderByDESCinPistas();

   // Only order ASC
   //Mas barato a mas caro
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id ORDER BY s.price ASC;", nativeQuery = true)
   List<Pista> OrderByASCinPistas();

   // Only reservation NO
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS TRUE GROUP BY pp.pista_id ORDER BY s.price DESC;", nativeQuery = true)
   List<Pista> findReservedPista();

   // Only reservation YES
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS FALSE GROUP BY pp.pista_id ORDER BY s.price DESC;", nativeQuery = true)
   List<Pista> findNoNReservedPista();

   // ORDER AND SPORT ASC
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name IN :sports ORDER BY s.price ASC;", nativeQuery = true)
   List<Pista> findSportOrderedASCinPista(@Param("sports") String[] sports);

   // ORDER AND SPORT DESC
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name IN :sports ORDER BY s.price DESC;", nativeQuery = true)
   List<Pista> findSportOrderedDESCinPista(@Param("sports") String[] sports);


   //SPORT + RESERVATION TRUE
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name = 'Tenis' AND pp.is_reserved IS TRUE;", nativeQuery = true)
   List<Pista> findSportReservedPista(@Param("sports") String[] sports);

   //SPORT + RESERVATION FALSE
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name = 'Tenis' AND pp.is_reserved IS FALSE;", nativeQuery = true)
      List<Pista> findSportNoNReservedPista(@Param("sports") String[] sports);

   //RESERVATION TRUE + ORDER ASC
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS TRUE GROUP BY pp.pista_id ORDER BY s.price ASC;", nativeQuery = true)
      List<Pista> findOrderedASCReservedPista();

   //RESERVATION TRUE + ORDER DESC
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS TRUE GROUP BY pp.pista_id ORDER BY s.price DESC;", nativeQuery = true)
      List<Pista> findOrderedDESCReservedPista();
      
   //RESERVATION FALSE + ORDER ASC
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS FALSE GROUP BY pp.pista_id ORDER BY s.price ASC;", nativeQuery = true)
      List<Pista> findOrderedASCNoNReservedPista();

        //RESERVATION TRUE + ORDER DESC
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS FALSE GROUP BY pp.pista_id ORDER BY s.price DESC;", nativeQuery = true)
      List<Pista> findOrderedDESCNoNReservedPista();

      //SPORT + RESERVATION TRUE + ORDER ASC
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON s.id = p.sport_id WHERE s.sport_name IN :sports AND pp.is_reserved IS TRUE GROUP BY pp.pista_id ORDER BY s.price ASC;", nativeQuery = true)
      List<Pista> findSportOrderedASCReservedPista(@Param("sports") String[] sports);

      //SPORT + RESERVATION TRUE + ORDER DESC
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON s.id = p.sport_id WHERE s.sport_name IN :sports AND pp.is_reserved IS TRUE GROUP BY pp.pista_id ORDER BY s.price DESC;", nativeQuery = true)
      List<Pista> findSportOrderedDESCReservedPista(@Param("sports") String[] sports);

      //SPORT + RESERVATION FALSE + ORDER ASC
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON s.id = p.sport_id WHERE s.sport_name IN :sports AND pp.is_reserved IS FALSE GROUP BY pp.pista_id ORDER BY s.price ASC;", nativeQuery = true)
      List<Pista> findSportOrderedASCNoNReservedPista(@Param("sports") String[] sports);

      //SPORT + RESERVATION FALSE + ORDER DESC
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON s.id = p.sport_id WHERE s.sport_name IN :sports AND pp.is_reserved IS FALSE GROUP BY pp.pista_id ORDER BY s.price DESC;", nativeQuery = true)
      List<Pista> findSportOrderedDESCNoNReservedPista(@Param("sports") String[] sports);
   }