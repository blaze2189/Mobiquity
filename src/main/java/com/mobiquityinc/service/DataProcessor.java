package com.mobiquityinc.service;

import com.mobiquityinc.entity.DataFile;
import com.mobiquityinc.exception.APIException;

public interface DataProcessor {

    DataFile processStringData(String stringLine) throws APIException;

}
