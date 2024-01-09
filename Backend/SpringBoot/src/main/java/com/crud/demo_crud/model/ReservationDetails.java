package com.crud.demo_crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
public class ReservationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pista_id")
    private Integer pista_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "date")
    private Date date;

    @Column(name = "state")
    private Integer state;
}