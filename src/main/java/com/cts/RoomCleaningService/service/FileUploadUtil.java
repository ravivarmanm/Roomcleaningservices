package com.cts.RoomCleaningService.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class FileUploadUtil {
    public static String saveFile(String fileCode,String fileName, MultipartFile multipartFile)
            throws IOException {
    	
        Path uploadPath = Paths.get("upload");
          
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
     
        try {
            
        	Path filePath = uploadPath.resolve(fileCode);
        
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        
        } catch (IOException ioe) {       
            throw new IOException("Could not save file: " + fileName, ioe);
        }
        
        return fileCode;
    }
}