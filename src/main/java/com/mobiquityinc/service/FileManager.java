package com.mobiquityinc.service;

import com.mobiquityinc.exception.APIException;

import java.util.List;

public interface FileManager {

    List<String> readFile(String pathToFile) throws APIException;

}
