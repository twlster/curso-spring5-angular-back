package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest.models.controllers.Controller;

@Service
public class UploadFileServiceImp implements UploadFileService{
	
	private final Logger log = LoggerFactory.getLogger(Controller.class);
	
	private final static String PICTURES_DIRECTORY = "pictures";

	@Override
	public Resource load(String fileName) throws MalformedURLException {
		log.info("Getting picture: "+ fileName);
		
		Path picturePath = getPath(fileName);
		
		Resource resource = new UrlResource(picturePath.toUri());
				
		if(!resource.exists() && !resource.isReadable()) {
			picturePath = Paths.get("src/main/resources/static/images").resolve("notuser.png").toAbsolutePath();

			resource = new UrlResource(picturePath.toUri());
			
			log.error("Error loading the picture for name "+fileName);
		}
		
		return resource;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		String originalName = UUID.randomUUID()+"_"+file.getOriginalFilename().replaceAll(" ", "");
		Path fileRoute = getPath(originalName);
		log.info("Uploading picture: "+ fileRoute.toString());
		Files.copy(file.getInputStream(), fileRoute);
		return originalName;
	}

	@Override
	public boolean delete(String fileName) {
		if(fileName != null && fileName.length() > 0) {
			Path oldFileRoute = Paths.get("pictures").resolve(fileName).toAbsolutePath();
			if(oldFileRoute.toFile().exists() && oldFileRoute.toFile().canRead()) {
				oldFileRoute.toFile().delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path getPath(String fileName) {
		return Paths.get(PICTURES_DIRECTORY).resolve(fileName).toAbsolutePath();
	}

}
