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

import com.crud.demo_crud.model.PistaQueryParam;
import com.crud.demo_crud.model.Sport;
import com.crud.demo_crud.repository.SportRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class SportController {

	@Autowired
	SportRepository sportRepository;

	//GET ALL
	@GetMapping("/sports")
	public ResponseEntity<List<Sport>> getAllSports(@RequestParam(required = false) String tipo) {
		try {
			List<Sport> sports = new ArrayList<Sport>();

			if (tipo == null)
				sportRepository.findAll().forEach(sports::add);

			if (sports.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(sports, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//GET ONE
	@GetMapping("/sports/{id}")
	public ResponseEntity<Sport> getSportById(@PathVariable("id") long id) {
		Optional<Sport> sportData = sportRepository.findById(id);

		if (sportData.isPresent()) {
			return new ResponseEntity<>(sportData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

@GetMapping("/sportInfinite")
	public ResponseEntity<List<Sport>> getAllSports(@ModelAttribute PistaQueryParam pistaQueryParam) {
		try {
			List<Sport> sports = new ArrayList<Sport>();
			Integer limit = pistaQueryParam.getPage() * pistaQueryParam.getLimit();
			if (limit != null){
				sportRepository.findSportScroll(limit).forEach(sports::add);
			if (sports.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<>(sports, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}