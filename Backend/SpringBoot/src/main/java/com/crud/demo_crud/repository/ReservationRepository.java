package com.crud.demo_crud.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crud.demo_crud.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "SELECT COUNT(*) FROM reservations r WHERE r.pista_id = :pista_id AND r.date = :date AND r.state NOT LIKE 2 ;", nativeQuery = true)
    Integer pistaAvailable(@Param("pista_id") Long pista_id, @Param("date") Date date);

    @Query(value = "SELECT * FROM reservations WHERE pista_id = :pista_id AND user_id = :user_id ;", nativeQuery = true)
    List<Reservation> showReservationsOfPista(@Param("user_id") Long user_id, @Param("pista_id") Long pista_id);

    @Query(value = "SELECT * FROM reservations r WHERE r.user_id = :user_id ;", nativeQuery = true)
    List<Reservation> findReservationsByUser(@Param("user_id") Long user_id);

    // @Query(value = "UPDATE reservations SET state = 2 WHERE id = :pista_id  AND date = :date AND user_id = :user_id ;", nativeQuery = true)
    // List<Reservation> cancelReservationsOfPista( @Param("pista_id") Long pista_id,  @Param("date") Date date, @Param("user_id") Long user_id);

}