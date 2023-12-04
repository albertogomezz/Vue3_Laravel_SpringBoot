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
	//GET ALL

	//GET WITH FILTERS
	@GetMapping("/pistas")
		public ResponseEntity<List<Pista>> getAll(@ModelAttribute PistaQueryParam pistaQueryParam) {
		try {
			//ATRIBUTES
			List<Pista> pistas = new ArrayList<Pista>();
			Integer limit = pistaQueryParam.getLimit();
			Integer page = pistaQueryParam.getPage();
			String[] sports_array = pistaQueryParam.getSports();
			String sports = "";
			for (String str : sports_array) {
				sports += str;
			}
			Integer offset = (page - 1) * limit;



			// 	SPORTS FILTER
			if (sports_array.length > 0 ) {
				pistaRepository.findSportsinPistas(sports,limit,offset).forEach(pistas::add);
			// 	NO FILTERS
			} else {
				pistaRepository.listPistasWithoutFilters(limit,offset).forEach(pistas::add);
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
			// pistaQueryParam.setName_pista(pistaQueryParam.getName_pista() + '%');
			Integer total = 0;
			// Only capacity
			// if (mesaQueryParam.getCategories().length == 0 && mesaQueryParam.getCapacity() > 0) {
			// 	total = mesaRepository.findByCapacityPaginate(mesaQueryParam.getCapacity(),
			// 			mesaQueryParam.getName_mesa());
			// }
			// Only categories
			// else if (mesaQueryParam.getCategories().length > 0 && mesaQueryParam.getCapacity() == 0) {
			// 	total = mesaRepository.findCategoriesOnMesaPaginate(mesaQueryParam.getCategories(),
			// 			mesaQueryParam.getName_mesa());
			// }
			// Categories with capacity
			// else if (mesaQueryParam.getCategories().length > 0 && mesaQueryParam.getCapacity() > 0) {
			// 	total = mesaRepository.findByCapacityAndCategoriesPaginate(mesaQueryParam.getCapacity(),
			// 			mesaQueryParam.getCategories(),
			// 			mesaQueryParam.getName_mesa());
			// }
			// else {
				total = pistaRepository.findAllPistas();
			// }

			return new ResponseEntity<>(total, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}