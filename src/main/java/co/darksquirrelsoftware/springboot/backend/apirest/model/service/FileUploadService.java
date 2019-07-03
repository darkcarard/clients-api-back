package co.darksquirrelsoftware.springboot.backend.apirest.model.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	
	public Resource uploadFile(String fileName) throws MalformedURLException;
	
	public String copyFile(MultipartFile file) throws IOException;
	
	public boolean deleteFile(String fileName) throws IOException;
	
	public Path getPath(String fileName, String path);
}
