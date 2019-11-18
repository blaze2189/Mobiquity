package com.mobiquityinc.service.impl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.service.DataProcessor;
import org.junit.Test;

public class DataProcessorImplTest {

    private DataProcessor dataProcessor = new DataProcessorImpl();

    @Test
    public void testProcessStringData() throws APIException {
        String stringLine ="81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        dataProcessor.processStringData(stringLine);
    }
}