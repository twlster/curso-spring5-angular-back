package com.bolsadeideas.springboot.backend.apirest.models.services;

import com.bolsadeideas.springboot.backend.apirest.models.entity.User;

public interface IUserService {

	public User findByUsername(String username);
}
