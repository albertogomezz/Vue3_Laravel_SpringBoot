package com.crud.demo_crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.crud.demo_crud.model.User;
import com.crud.demo_crud.model.Pista;
import com.crud.demo_crud.model.Reservation;
import com.crud.demo_crud.repository.UserRepository;
import com.crud.demo_crud.repository.PistaRepository;
import com.crud.demo_crud.repository.ReservationRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    private ReservationRepository ReservationRepository;

    @Autowired
    private PistaRepository PistaRepository;

    @Autowired
    private UserRepository UserRepository;

    //CREATE RESERVATION
    @PostMapping("/reservation/{pista_id}")
    public ResponseEntity<Reservation> CreateReservation(@RequestBody Reservation reservation, @PathVariable(required = true) Long pista_id) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            User user = UserRepository.findByUsername(userDetails.getUsername()).get();
            
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            Pista pista = PistaRepository.findById(pista_id).get();
            

            if (pista == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Integer pistaAvailable = ReservationRepository.pistaAvailable(pista_id, reservation.getDate());

            if (pistaAvailable > 0) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            reservation.setState(0);
            reservation.setPista(pista);
            reservation.setUser(user);
            ReservationRepository.save(reservation);
            return new ResponseEntity<>(reservation, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //UPDATE RESERVATION
    @PutMapping("/reservation/{id_reserv}")
    public ResponseEntity<Reservation> UpdateReservation(@RequestBody Reservation reservation_,
            @PathVariable(required = true) Long id_reserv) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            User user = UserRepository.findByUsername(userDetails.getUsername()).get();
            Reservation reservation = ReservationRepository.findById(id_reserv).get();

            if (user.getId() != reservation.getUser().getId()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            if (reservation.getState() != 0) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            Integer pistaAvailable = ReservationRepository.pistaAvailable(reservation.getPista().getId(),
                    reservation_.getDate());

            if (pistaAvailable > 0) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            reservation.setDate(reservation_.getDate());
            ReservationRepository.save(reservation);
            return new ResponseEntity<>(reservation, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



     //CANCEL RESERVATION
    @PutMapping("/reservation_cancel/{id_reserv}")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable(required = true) Long id_reserv) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            User user = UserRepository.findByUsername(userDetails.getUsername()).get();
            Reservation reservation = ReservationRepository.findById(id_reserv).get();

            if (user.getId() != reservation.getUser().getId()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            if (reservation.getState() == 2) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            reservation.setState(2);
            ReservationRepository.save(reservation);

            return new ResponseEntity<>(reservation, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<?> DeleteReservation(@PathVariable(required = true) Long id) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            User user = UserRepository.findByUsername(userDetails.getUsername()).get();
            Reservation reservation = ReservationRepository.findById(id).get();

            if (user.getId() != reservation.getUser().getId()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            if (reservation.getState()  != 0) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            ReservationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}