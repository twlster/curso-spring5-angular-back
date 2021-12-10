package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Client;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Region;
import com.bolsadeideas.springboot.backend.apirest.models.repositories.ClientsRepository;
import com.bolsadeideas.springboot.backend.apirest.models.repositories.RegionRepository;

@Service
@Transactional
public class ClientsServiceImplementation implements ClientsService{

	@Autowired
	private ClientsRepository repository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Override
	public List<Client> findAll() {
		return (List<Client>) repository.findAll();
	}
	
	@Override
	public Page<Client> findAll(PageRequest pageRequest) {
		return (Page<Client>) repository.findAll(pageRequest);
	}

	@Override
	public Client findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Client save(Client client) {
		return repository.save(client);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegions() {
		return regionRepository.findAll();
	}

}
