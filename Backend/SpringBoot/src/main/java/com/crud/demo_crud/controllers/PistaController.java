package com.crud.demo_crud.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.demo_crud.model.Pista;
import com.crud.demo_crud.repository.PistaRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PistaController {

	@Autowired
	PistaRepository pistaRepository;

	//GET ALL
	@GetMapping("/pistas")
	public ResponseEntity<List<Pista>> getAllPistas(@RequestParam(required = false) String tipo) {
		try {
			List<Pista> pistas = new ArrayList<Pista>();

			if (tipo == null)
				pistaRepository.findAll().forEach(pistas::add);

			if (pistas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(pistas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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