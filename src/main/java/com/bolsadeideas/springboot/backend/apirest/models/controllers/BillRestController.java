package com.bolsadeideas.springboot.backend.apirest.models.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Bill;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Product;
import com.bolsadeideas.springboot.backend.apirest.models.services.ClientsService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:4200", "*" }, methods = { RequestMethod.GET,
		RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class BillRestController {

	@Autowired
	private ClientsService service;

	@GetMapping(value = "/bills/products", produces = "application/json")
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getproducts() {
		try {
			List<Product> products = service.findAllProduct();

			if (products == null) {
				Map<String, String> response = new HashMap();
				response.put("mensaje", "Products not found.");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} catch (DataAccessException e) {
			Map<String, String> response = new HashMap();
			response.put("mensaje", "Error when looking for products with in BBDD");
			response.put("error", "Error: " + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/bills/{id}", produces = "application/json")
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getBills(@PathVariable("id") Long id) {
		try {
			Bill bill = service.findBillById(id);

			if (bill == null) {
				Map<String, String> response = new HashMap();
				response.put("mensaje", "Bill with id " + id + " not found.");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Bill>(bill, HttpStatus.OK);
		} catch (DataAccessException e) {
			Map<String, String> response = new HashMap();
			response.put("mensaje", "Error when looking for bill with id" + id + " in BBDD");
			response.put("error", "Error: " + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/bills", consumes = "application/json", produces = "application/json")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<?> createBill(@RequestBody @Valid Bill bill, BindingResult result) {
		if (result.hasErrors()) {
			Map<String, Object> response = new HashMap();
			response.put("errors",
					result.getFieldErrors().stream()
							.map(error -> "Field " + error.getField() + ": " + error.getDefaultMessage())
							.collect(Collectors.toList()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		try {
			return new ResponseEntity<Bill>(service.saveBill(bill), HttpStatus.CREATED);
		} catch (Exception e) {
			Map<String, String> response = new HashMap();
			response.put("mensaje", "Error creating new bill in BBDD");
			response.put("error", "Error: " + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/bills/{id}", consumes = "application/json", produces = "application/json")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<?> updateBill(@PathVariable("id") Long id, @RequestBody @Valid Bill bill,
			BindingResult result) {
		if (result.hasErrors()) {
			Map<String, Object> response = new HashMap();
			response.put("errors",
					result.getFieldErrors().stream()
							.map(error -> "Field " + error.getField() + ": " + error.getDefaultMessage())
							.collect(Collectors.toList()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {
			Bill actual = service.findBillById(id);

			if (actual == null) {
				Map<String, String> response = new HashMap();
				response.put("mensaje", "Can't update because bill with id " + id + " doesn't exist in DDBB.");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
			}

			actual.setClient(bill.getClient());
			actual.setCreatedAt(bill.getCreatedAt());
			actual.setDescription(bill.getDescription());
			actual.setId(bill.getId());
			actual.setItems(bill.getItems());
			actual.setObservation(bill.getObservation());
			return new ResponseEntity<Bill>(service.saveBill(actual), HttpStatus.CREATED);
		} catch (Exception e) {
			Map<String, String> response = new HashMap();
			response.put("mensaje", "Error updating bill with id " + id + ".");
			response.put("error", "Error: " + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(value = "/bills/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<?> deleteBill(@PathVariable("id") Long id) {
		try {
			Bill bill = service.findBillById(id);

			if (bill == null) {
				Map<String, String> response = new HashMap();
				response.put("mensaje", "Can't delete because bill with id " + id + " doesn't exist in DDBB.");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
			}

			service.deleteBill(id);

			return new ResponseEntity<Bill>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			Map<String, String> response = new HashMap();
			response.put("mensaje", "Error deleting bill with id " + id + ".");
			response.put("error", "Error: " + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
