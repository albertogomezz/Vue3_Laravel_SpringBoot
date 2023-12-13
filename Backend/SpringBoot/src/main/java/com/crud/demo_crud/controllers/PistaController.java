package com.crud.demo_crud.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.demo_crud.model.Pista;
import com.crud.demo_crud.model.PistaQueryParam;
import com.crud.demo_crud.repository.PistaRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class PistaController {

	@Autowired
	PistaRepository pistaRepository;

	@GetMapping("/pistas")
	public ResponseEntity <List<Pista>> getAll(@ModelAttribute PistaQueryParam pistaQueryParam) {
		try {
			//ATRIBUTES
			List<Pista> pistas = new ArrayList<Pista>();
			Integer limit = pistaQueryParam.getLimit();
			Integer offset = ( pistaQueryParam.getPage() - 1) * limit;


			//ONLY SPORTS FILTER
			if (pistaQueryParam.getSports().length > 0 && pistaQueryParam.getReservation() == 2 && pistaQueryParam.getOrder() == 2 ) {    //DONE
				pistaRepository.findSportsinPistas(pistaQueryParam.getSports(), pistaQueryParam.getLimit(), offset).forEach(pistas::add);
			}//ONLY ORDER BY PRICE FILTER
			else if (pistaQueryParam.getOrder() != 2 && pistaQueryParam.getReservation() == 2 && pistaQueryParam.getSports().length <= 0){ //DONE
				//ASC
				if (pistaQueryParam.getOrder() == 0){
					pistaRepository.OrderByASCinPistas(pistaQueryParam.getLimit(), offset).forEach(pistas::add);
				}else{ //DESC
					pistaRepository.OrderByDESCinPistas(pistaQueryParam.getLimit(), offset).forEach(pistas::add);

				}
			} //ONLY RESERVATION FILTER																													
			else if (pistaQueryParam.getReservation() < 2 && pistaQueryParam.getOrder() == 2 && pistaQueryParam.getSports().length <= 0){	  //DONE
				if(pistaQueryParam.getReservation() == 0){ //NON RESERVED
					pistaRepository.findNoNReservedPista(pistaQueryParam.getLimit(), offset).forEach(pistas::add);
				}else{ //RESERVED
					pistaRepository.findReservedPista(pistaQueryParam.getLimit(), offset).forEach(pistas::add);
				}	
			}
			//SPORTS AND ORDER PRICE FILTER
			else if(pistaQueryParam.getSports().length > 0  && pistaQueryParam.getOrder() != 2  && pistaQueryParam.getReservation() == 2){	//DONE
				if (pistaQueryParam.getOrder() == 0){ 
					pistaRepository.findSportOrderedASCinPista(pistaQueryParam.getSports(), pistaQueryParam.getLimit(), offset).forEach(pistas::add);
					// OrderByASCinPistas
				}else{
					pistaRepository.findSportOrderedDESCinPista(pistaQueryParam.getSports(),  pistaQueryParam.getLimit(), offset).forEach(pistas::add);
					// OrderByDESCinPistas
				}
			} //SPORTS AND RESERVATION FILTER 
			else if(pistaQueryParam.getSports().length > 0  && pistaQueryParam.getOrder() == 2  && pistaQueryParam.getReservation() < 2){ //DONE
				if(pistaQueryParam.getReservation() == 0){ //NON RESERVED
					pistaRepository.findSportNoNReservedPista(pistaQueryParam.getSports(), pistaQueryParam.getLimit(), offset).forEach(pistas::add);
				}else{ //RESERVED
					pistaRepository.findSportReservedPista(pistaQueryParam.getSports(), pistaQueryParam.getLimit(), offset).forEach(pistas::add);
				}	
			}
			else if (pistaQueryParam.getSports().length <= 0 && pistaQueryParam.getOrder() < 2 && pistaQueryParam.getReservation() < 2) {
				if (pistaQueryParam.getOrder() == 0 && pistaQueryParam.getReservation() == 0) { // Order: ASC, Reservation: Non-reserved
					pistaRepository.findOrderedASCNoNReservedPista(pistaQueryParam.getLimit(), offset).forEach(pistas::add);
				} else if (pistaQueryParam.getOrder() == 0 && pistaQueryParam.getReservation() == 1) { // Order: ASC, Reservation: Reserved
					pistaRepository.findOrderedASCReservedPista(pistaQueryParam.getLimit(), offset).forEach(pistas::add);
				} else if (pistaQueryParam.getOrder() == 1 && pistaQueryParam.getReservation() == 0) { // Order: DESC, Reservation: Non-reserved
					pistaRepository.findOrderedDESCNoNReservedPista(pistaQueryParam.getLimit(), offset).forEach(pistas::add);
				} else if (pistaQueryParam.getOrder() == 1 && pistaQueryParam.getReservation() == 1) { // Order: DESC, Reservation: Reserved
					pistaRepository.findOrderedDESCReservedPista(pistaQueryParam.getLimit(), offset).forEach(pistas::add);
				}
			} else if (pistaQueryParam.getSports().length > 0 && pistaQueryParam.getOrder() < 2 && pistaQueryParam.getReservation() < 2) {
				if (pistaQueryParam.getOrder() == 0 && pistaQueryParam.getReservation() == 0) { // Order: ASC, Reservation: Non-reserved
					pistaRepository.findSportOrderedASCNoNReservedPista(pistaQueryParam.getSports(), pistaQueryParam.getLimit(), offset).forEach(pistas::add);
				} else if (pistaQueryParam.getOrder() == 0 && pistaQueryParam.getReservation() == 1) { // Order: ASC, Reservation: Reserved
					pistaRepository.findSportOrderedASCReservedPista(pistaQueryParam.getSports(), pistaQueryParam.getLimit(), offset).forEach(pistas::add);
				} else if (pistaQueryParam.getOrder() == 1 && pistaQueryParam.getReservation() == 0) { // Order: DESC, Reservation: Non-reserved
					pistaRepository.findSportOrderedDESCNoNReservedPista(pistaQueryParam.getSports(), pistaQueryParam.getLimit(), offset).forEach(pistas::add);
				} else if (pistaQueryParam.getOrder() == 1 && pistaQueryParam.getReservation() == 1) { // Order: DESC, Reservation: Reserved
					pistaRepository.findSportOrderedDESCReservedPista(pistaQueryParam.getSports(), pistaQueryParam.getLimit(), offset).forEach(pistas::add);
				}
			}
			//NO FILTERS ordered by price 
			else{ 
				pistaRepository.listPistasWithoutFilters(pistaQueryParam.getLimit(), offset).forEach(pistas::add);

			}
			return new ResponseEntity<>(pistas, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////77

	//GET ONE
	@GetMapping("/pistas/{id}")
	public ResponseEntity<Pista> getPistaById(@PathVariable("id") long id) {
		Optional<Pista> pistaData = pistaRepository.findById(id);

		if (pistaData.isPresent()) {
			return new ResponseEntity<>(pistaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////77

	//GET ALL PAGINATE
	@GetMapping("/pistasPaginate")
	public ResponseEntity<Integer> getAllPitasPagination(@ModelAttribute PistaQueryParam pistaQueryParam) {
		
		try {
			//COUNT SOLO SPORTS
			Integer total = 0;
			if (pistaQueryParam.getSports().length > 0 && pistaQueryParam.getReservation() == 2 && pistaQueryParam.getOrder() == 2 ) {    //DONE
				total = pistaRepository.countSportsinPistas(pistaQueryParam.getSports());
			} 
			// COUNT ORDER BY
			else if (pistaQueryParam.getOrder() != 2 && pistaQueryParam.getReservation() == 2 && pistaQueryParam.getSports().length <= 0){ //DONE
					total = pistaRepository.countByDESCinPistas();
			} 
			//COUNT RESERVED
			else if (pistaQueryParam.getReservation() < 2 && pistaQueryParam.getOrder() == 2 && pistaQueryParam.getSports().length <= 0){	  //DONE
				if(pistaQueryParam.getReservation() == 0){ //NON RESERVED
				total =	pistaRepository.countNoNReservedPista();
				}else{ //RESERVED
					total = pistaRepository.countReservedPista();
				}
			} //COUNT SPORTS + ORDER BY 
			else if(pistaQueryParam.getSports().length > 0 && pistaQueryParam.getReservation() == 2 && pistaQueryParam.getOrder() < 2) {
				if (pistaQueryParam.getOrder() == 0){ 
					total = pistaRepository.countSportOrderedASCinPista(pistaQueryParam.getSports());
					// OrderByASCinPistas
				}else{
					total = pistaRepository.countSportOrderedDESCinPista(pistaQueryParam.getSports());
					// OrderByDESCinPistas
				}
			} //COUNT SPORTS + RESERVATION
			else if(pistaQueryParam.getSports().length > 0  && pistaQueryParam.getOrder() == 2  && pistaQueryParam.getReservation() < 2){ //DONE
				if(pistaQueryParam.getReservation() == 0){ //NON RESERVED
					total = pistaRepository.countSportNoNReservedPista(pistaQueryParam.getSports());
				}else{ //RESERVED
					total = pistaRepository.countSportReservedPista(pistaQueryParam.getSports());
				}	
			}//ORDER + RESERVATION
			else if (pistaQueryParam.getSports().length <= 0 && pistaQueryParam.getOrder() < 2 && pistaQueryParam.getReservation() < 2) {
				if (pistaQueryParam.getOrder() == 0 && pistaQueryParam.getReservation() == 0) { // Order: ASC, Reservation: Non-reserved
					total = pistaRepository.countOrderedASCNoNReservedPista();
				} else if (pistaQueryParam.getOrder() == 0 && pistaQueryParam.getReservation() == 1) { // Order: ASC, Reservation: Reserved
					total = pistaRepository.countOrderedASCReservedPista();
				} else if (pistaQueryParam.getOrder() == 1 && pistaQueryParam.getReservation() == 0) { // Order: DESC, Reservation: Non-reserved
					total = pistaRepository.countOrderedDESCNoNReservedPista();
				} else if (pistaQueryParam.getOrder() == 1 && pistaQueryParam.getReservation() == 1) { // Order: DESC, Reservation: Reserved
					total = pistaRepository.countOrderedDESCReservedPista();
				}
			}//SPORTS + ORDER + RESERVATION
			else if (pistaQueryParam.getSports().length > 0 && pistaQueryParam.getOrder() < 2 && pistaQueryParam.getReservation() < 2) {
				if (pistaQueryParam.getOrder() == 0 && pistaQueryParam.getReservation() == 0) { // Order: ASC, Reservation: Non-reserved
					total = pistaRepository.countSportOrderedASCNoNReservedPista(pistaQueryParam.getSports());
				} else if (pistaQueryParam.getOrder() == 0 && pistaQueryParam.getReservation() == 1) { // Order: ASC, Reservation: Reserved
					total = pistaRepository.countSportOrderedASCReservedPista(pistaQueryParam.getSports());
				} else if (pistaQueryParam.getOrder() == 1 && pistaQueryParam.getReservation() == 0) { // Order: DESC, Reservation: Non-reserved
					total = pistaRepository.countSportOrderedDESCNoNReservedPista(pistaQueryParam.getSports());
				} else if (pistaQueryParam.getOrder() == 1 && pistaQueryParam.getReservation() == 1) { // Order: DESC, Reservation: Reserved
					total = pistaRepository.countSportOrderedDESCReservedPista(pistaQueryParam.getSports());
				}
			}
			else {
				total = pistaRepository.countAllPistas();
			}

			return new ResponseEntity<>(total, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}