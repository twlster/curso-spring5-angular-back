package com.bolsadeideas.springboot.backend.apirest.models.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Client;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Region;
import com.bolsadeideas.springboot.backend.apirest.models.services.ClientsService;
import com.bolsadeideas.springboot.backend.apirest.models.services.UploadFileServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:4200" }, methods = { RequestMethod.GET,
		RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class Controller {

	private static final int SIZE = 4;

	@Autowired
	private ClientsService service;
	
	@Autowired
	private UploadFileServiceImp uploadFileService;

	@RequestMapping(value = "/clients", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getClients() {
		try {
			List<Client> client = service.findAll();

			if (client == null) {
				Map<String, String> response = new HashMap();
				response.put("mensaje", "No clients found.");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<List<Client>>(client, HttpStatus.OK);
		} catch (DataAccessException e) {
			Map<String, String> response = new HashMap();
			response.put("mensaje", "Error when looking for clients in DDBB");
			response.put("error", "Error: " + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/clients/page/{page}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getClients(@PathVariable Integer page) {
		try {
			Page<Client> client = service.findAll(PageRequest.of(page, SIZE));

			if (client == null) {
				Map<String, String> response = new HashMap();
				response.put("mensaje", "No clients found.");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Page<Client>>(client, HttpStatus.OK);
		} catch (DataAccessException e) {
			Map<String, String> response = new HashMap();
			response.put("mensaje", "Error when looking for clients in DDBB");
			response.put("error", "Error: " + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/clients/{id}", method = RequestMethod.GET, produces = "application/json")
	//@Secured({"ROLE_USER","ROLE_ADMIN"})
	public ResponseEntity<?> getClient(@PathVariable("id") Long id) {
		try {
			Client client = service.findById(id);

			if (client == null) {
				Map<String, String> response = new HashMap();
				response.put("mensaje", "Client with id " + id + " not found.");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Client>(client, HttpStatus.OK);
		} catch (DataAccessException e) {
			Map<String, String> response = new HashMap();
			response.put("mensaje", "Error when looking for client with id" + id + " in BBDD");
			response.put("error", "Error: " + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/clients", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> createClient(@RequestBody @Valid Client client, BindingResult result) {
		if (result.hasErrors()) {
			Map<String, Object> response = new HashMap();
			response.put("errors",
					result.getFieldErrors().stream()
							.map(error -> "Field " + error.getField() + ": " + error.getDefaultMessage())
							.collect(Collectors.toList()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		try {
			return new ResponseEntity<Client>(service.save(client), HttpStatus.CREATED);
		} catch (Exception e) {
			Map<String, String> response = new HashMap();
			response.put("mensaje", "Error creating new client in BBDD");
			response.put("error", "Error: " + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> updateClient(@PathVariable("id") Long id, @RequestBody @Valid Client client,
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
			Client actual = service.findById(id);

			if (actual == null) {
				Map<String, String> response = new HashMap();
				response.put("mensaje", "Can't update because client with id " + id + " doesn't exist in DDBB.");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
			}

			actual.setBirthDate(client.getBirthDate());
			actual.setEmail(client.getEmail());
			actual.setLastName(client.getLastName());
			actual.setName(client.getName());
			actual.setRegion(client.getRegion());
			return new ResponseEntity<Client>(service.save(actual), HttpStatus.CREATED);
		} catch (Exception e) {
			Map<String, String> response = new HashMap();
			response.put("mensaje", "Error updating client with id " + id + ".");
			response.put("error", "Error: " + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
		try {
			Client client = service.findById(id);

			if (client == null) {
				Map<String, String> response = new HashMap();
				response.put("mensaje", "Can't delete because client with id " + id + " doesn't exist in DDBB.");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
			}

			uploadFileService.delete(client.getPicture());
			
			service.delete(id);

			return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			Map<String, String> response = new HashMap();
			response.put("mensaje", "Error deleting client with id " + id + ".");
			response.put("error", "Error: " + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/clients/upload")
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap();
		Client client = service.findById(id);

		if (!file.isEmpty()) {
			String originalName = "";
			try {
				originalName = uploadFileService.copy(file);
			} catch (IOException e) {
				response.put("mensaje", "Error uplading picture for client with id " + id + ".");
				response.put("error", "Error: " + e.getLocalizedMessage());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			uploadFileService.delete(client.getPicture());
			
			client.setPicture(originalName);
			service.save(client);
			response.put("client", client);
			response.put("mensaje", "Picture " + originalName + " uploaded successfully");
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/clients/upload/img/{pictureName:.+}")
	public ResponseEntity<Resource> getFoto(@PathVariable String pictureName){
		
		Resource resource = null;
		try {
			resource = uploadFileService.load(pictureName);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"");
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		
	}

	@RequestMapping(value = "/clients/regions", method = RequestMethod.GET, produces = "application/json")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> getRegions() {
		try {
			List<Region> regions = service.findAllRegions();

			if (regions == null) {
				Map<String, String> response = new HashMap();
				response.put("mensaje", "No region found.");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<List<Region>>(regions, HttpStatus.OK);
		} catch (DataAccessException e) {
			Map<String, String> response = new HashMap();
			response.put("mensaje", "Error when looking for regions in DDBB");
			response.put("error", "Error: " + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
