package com.crud.demo_crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crud.demo_crud.model.ReservationDetails;

public interface ReservationDetailsRepository extends JpaRepository<ReservationDetails, Long> {

    @Query(value = "SELECT * FROM reservations r WHERE r.user_id = :user_id AND r.state = :state ;", nativeQuery = true)
    List<ReservationDetails> findReservationsByUser(@Param("user_id") Long user_id,@Param("state") Long state);
}