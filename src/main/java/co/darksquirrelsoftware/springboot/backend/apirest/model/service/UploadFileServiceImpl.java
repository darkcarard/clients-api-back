package co.darksquirrelsoftware.springboot.backend.apirest.model.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements FileUploadService {

	private static final String PATH_UPLOADED_FILES = "uploads";
	private static final String PATH_STATIC_RESOURCES = "src/main/resources/static/";
	private static final String PATH_STATIC_RESOURCES_IMAGES = PATH_STATIC_RESOURCES + "images/";
	private static final String DEFAULT_USER_IMAGE_FILE_NAME = "tux_default_user.png";
	
	@Override
	public Resource uploadFile(String fileName) throws MalformedURLException {
		
		Resource resource =  new UrlResource(getPath(fileName, PATH_UPLOADED_FILES).toUri());
		
		if (!resource.exists() || !resource.isReadable()) {			
			resource = new UrlResource(getPath(DEFAULT_USER_IMAGE_FILE_NAME, PATH_STATIC_RESOURCES_IMAGES).toUri());				
		}
		
		return resource;
	}

	@Override
	public String copyFile(MultipartFile file) throws IOException {
		String fileName = "";

		if (!file.isEmpty()) {
			fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
			Path filePath = Paths.get(PATH_UPLOADED_FILES).resolve(fileName).toAbsolutePath();
			
			Files.copy(file.getInputStream(), filePath);
		}

		return fileName;
	}

	@Override
	public boolean deleteFile(String fileName) throws IOException {
		boolean result = false;
		Path oldFilePath = null;
		
		if (fileName != null) {
			oldFilePath = getPath(fileName, PATH_UPLOADED_FILES);
			result = Files.deleteIfExists(oldFilePath);
		} 
		return result;
	}

	@Override
	public Path getPath(String fileName, String path) {
		return Paths.get(path).resolve(fileName).toAbsolutePath();
	}

}
