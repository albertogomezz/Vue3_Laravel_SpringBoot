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
import org.springframework.web.bind.annotation.RequestParam;
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
	//GET ALL

	//GET WITH FILTERS
	@GetMapping("/pistas")
	public ResponseEntity<List<Pista>> getAll(@ModelAttribute PistaQueryParam pistaQueryParam) {
		try {
			List<Pista> pistas = new ArrayList<Pista>();
			//SPORTS FILTER
			if (pistaQueryParam.getSports().length > 0 ) {
				pistaRepository.findSportsinPistas(pistaQueryParam.getSports()).forEach(pistas::add);
			//NO FILTERS
			} else{ 
				pistaRepository.findAll().forEach(pistas::add);
			}
			return new ResponseEntity<>(pistas, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

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
}