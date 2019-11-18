package com.mobiquityinc.service.impl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.service.FileManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileManagerImpl implements FileManager {

    @Override
    public List<String> readFile(String pathToFile) throws APIException {

        try {
            Path path = Paths.get(pathToFile);
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        }catch(IOException ioe){
            throw new APIException("Couldn't read file "+pathToFile,ioe);
        }

    }
}
