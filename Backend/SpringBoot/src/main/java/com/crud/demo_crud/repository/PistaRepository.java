package com.crud.demo_crud.repository;

import java.util.List;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crud.demo_crud.model.Pista;

public interface PistaRepository extends JpaRepository<Pista, Long> {

   /////////////////////////////// ALL ////////////////////////////////////////

   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id GROUP BY pp.pista_id ORDER BY s.price DESC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> listPistasWithoutFilters(@Param("limit") Integer limit, @Param("offset") Integer offset);
    //COUNT ALL PISTAS
   @Query(value = "SELECT COUNT(*) FROM pista", nativeQuery = true)
   Integer countAllPistas();

   
   ///////////////////////////////SPORT FILTER////////////////////////////////////////

	@Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name IN :sports ORDER BY s.price DESC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> findSportsinPistas(@Param("sports") String[] sports, @Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT SPORTS FILTER
   @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name IN :sports ORDER BY s.price DESC ;", nativeQuery = true)
   Integer countSportsinPistas(@Param("sports") String[] sports);



   /////////////////////////////// ORDER BY PRICE ////////////////////////////////////////
   ///////////////////DESC
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id GROUP BY pp.pista_id ORDER BY s.price DESC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> OrderByDESCinPistas(@Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT FOR BOTH
   @Query(value = "SELECT COUNT(*) FROM ( SELECT DISTINCT s.id FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON s.id = p.sport_id ORDER BY s.price ASC ) AS deportes;", nativeQuery = true)
   Integer countByDESCinPistas();

   /////////////////ASC
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id GROUP BY pp.pista_id ORDER BY s.price ASC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> OrderByASCinPistas(@Param("limit") Integer limit, @Param("offset") Integer offset);




   /////////////////////////////// RESERVATION ////////////////////////////////////////
   // Only reservation YES
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS TRUE GROUP BY pp.pista_id ORDER BY s.price DESC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> findReservedPista(@Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT
   @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS TRUE ", nativeQuery = true)
   Integer countReservedPista();


   // Only reservation NO
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS FALSE GROUP BY pp.pista_id ORDER BY s.price DESC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> findNoNReservedPista(@Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT
   @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS FALSE ;", nativeQuery = true)
   Integer countNoNReservedPista();




   /////////////////////////////// SPORT + ORDER BY PRICE ////////////////////////////////////////
   // ORDER AND SPORT ASC
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name IN :sports GROUP BY pp.pista_id ORDER BY s.price ASC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> findSportOrderedASCinPista(@Param("sports") String[] sports, @Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT
   @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name IN :sports ORDER BY s.price ASC", nativeQuery = true)
   Integer countSportOrderedASCinPista(@Param("sports") String[] sports);

   // ORDER AND SPORT DESC
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name IN :sports GROUP BY pp.pista_id ORDER BY s.price DESC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> findSportOrderedDESCinPista(@Param("sports") String[] sports, @Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT
      @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name IN :sports ORDER BY s.price DESC;", nativeQuery = true)
   Integer countSportOrderedDESCinPista(@Param("sports") String[] sports);





   /////////////////////////////// SPORT + RESERVATION ////////////////////////////////////////
   //SPORT + RESERVATION TRUE
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name IN :sports AND pp.is_reserved IS TRUE LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> findSportReservedPista(@Param("sports") String[] sports,  @Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT
   @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name IN :sports AND pp.is_reserved IS TRUE ;", nativeQuery = true)
   Integer countSportReservedPista(@Param("sports") String[] sports);

   //SPORT + RESERVATION FALSE
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name IN :sports AND pp.is_reserved IS FALSE LIMIT :limit OFFSET :offset ;", nativeQuery = true)
      List<Pista> findSportNoNReservedPista(@Param("sports") String[] sports,  @Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT
   @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE s.sport_name IN :sports AND pp.is_reserved IS FALSE ;", nativeQuery = true)
      Integer countSportNoNReservedPista(@Param("sports") String[] sports);




   ///////////////////////////////  RESERVATION + ORDER BY PRICE ////////////////////////////////////////
   //RESERVATION TRUE + ORDER ASC
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS TRUE GROUP BY pp.pista_id ORDER BY s.price ASC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
      List<Pista> findOrderedASCReservedPista(@Param("limit") Integer limit, @Param("offset") Integer offset);
      //COUNT
      @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS TRUE ORDER BY s.price ASC ;", nativeQuery = true)
      Integer countOrderedASCReservedPista();

   //RESERVATION TRUE + ORDER DESC
      @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS TRUE GROUP BY pp.pista_id ORDER BY s.price DESC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
      List<Pista> findOrderedDESCReservedPista(  @Param("limit") Integer limit, @Param("offset") Integer offset);
      //COUNT
      @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS TRUE ORDER BY s.price DESC ;", nativeQuery = true)
      Integer countOrderedDESCReservedPista();

   //RESERVATION FALSE + ORDER ASC
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS FALSE GROUP BY pp.pista_id ORDER BY s.price ASC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> findOrderedASCNoNReservedPista(  @Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT
   @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS FALSE ORDER BY s.price ASC ;", nativeQuery = true)
   Integer countOrderedASCNoNReservedPista();


   //RESERVATION TRUE + ORDER DESC
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS FALSE GROUP BY pp.pista_id ORDER BY s.price DESC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> findOrderedDESCNoNReservedPista(@Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT
    @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON p.sport_id = s.id WHERE pp.is_reserved IS FALSE ORDER BY s.price DESC ;", nativeQuery = true)
   Integer countOrderedDESCNoNReservedPista();




   /////////////////////////////// SPORT + RESERVATION + ORDER BY PRICE ////////////////////////////////////////

   //SPORT + RESERVATION TRUE + ORDER ASC
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON s.id = p.sport_id WHERE s.sport_name IN :sports AND pp.is_reserved IS TRUE GROUP BY pp.pista_id ORDER BY s.price ASC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> findSportOrderedASCReservedPista(@Param("sports") String[] sports, @Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT
   @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON s.id = p.sport_id WHERE s.sport_name IN :sports AND pp.is_reserved IS TRUE ORDER BY s.price ASC ;", nativeQuery = true)
   Integer countSportOrderedASCReservedPista(@Param("sports") String[] sports);
   
   //SPORT + RESERVATION TRUE + ORDER DESC
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON s.id = p.sport_id WHERE s.sport_name IN :sports AND pp.is_reserved IS TRUE GROUP BY pp.pista_id ORDER BY s.price DESC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> findSportOrderedDESCReservedPista(@Param("sports") String[] sports, @Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT
   @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON s.id = p.sport_id WHERE s.sport_name IN :sports AND pp.is_reserved IS TRUE ORDER BY s.price DESC ;", nativeQuery = true)
   Integer countSportOrderedDESCReservedPista(@Param("sports") String[] sports);


   //SPORT + RESERVATION FALSE + ORDER ASC
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON s.id = p.sport_id WHERE s.sport_name IN :sports AND pp.is_reserved IS FALSE GROUP BY pp.pista_id ORDER BY s.price ASC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> findSportOrderedASCNoNReservedPista(@Param("sports") String[] sports, @Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT
   @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON s.id = p.sport_id WHERE s.sport_name IN :sports AND pp.is_reserved IS FALSE  ORDER BY s.price ASC ;", nativeQuery = true)
   Integer countSportOrderedASCNoNReservedPista(@Param("sports") String[] sports);


   //SPORT + RESERVATION FALSE + ORDER DESC
   @Query(value = "SELECT pp.*, s.price FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON s.id = p.sport_id WHERE s.sport_name IN :sports AND pp.is_reserved IS FALSE GROUP BY pp.pista_id ORDER BY s.price DESC LIMIT :limit OFFSET :offset ;", nativeQuery = true)
   List<Pista> findSportOrderedDESCNoNReservedPista(@Param("sports") String[] sports, @Param("limit") Integer limit, @Param("offset") Integer offset);
   //COUNT
   @Query(value = "SELECT COUNT(*) FROM pista pp LEFT JOIN pistas_sports p ON pp.id = p.pista_id LEFT JOIN sport s ON s.id = p.sport_id WHERE s.sport_name IN :sports AND pp.is_reserved IS FALSE ORDER BY s.price DESC ;", nativeQuery = true)
   Integer countSportOrderedDESCNoNReservedPista(@Param("sports") String[] sports);

   

   @Query(value = "SELECT * FROM pista WHERE id = :pista_id ;", nativeQuery = true)
   Pista FindOne(@Param("pista_id") Long pista_id);
}
