package com.mobiquityinc.packer;

import com.mobiquityinc.entity.DataFile;
import com.mobiquityinc.entity.Package;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.service.DataProcessor;
import com.mobiquityinc.service.FileManager;
import com.mobiquityinc.service.Process;
import com.mobiquityinc.service.impl.DataProcessorImpl;
import com.mobiquityinc.service.impl.FileManagerImpl;
import com.mobiquityinc.service.impl.ProcessImpl;

import java.util.Iterator;
import java.util.List;

public class Packer {

    private static FileManager fileManager = new FileManagerImpl();
    private static DataProcessor dataProcessor = new DataProcessorImpl();
    private static Process process = new ProcessImpl();

    private Packer() {
    }

    public static String pack(String filePath) throws APIException {
        StringBuilder result = new StringBuilder();
        List<String> listItems = fileManager.readFile(filePath);
        Iterator<String> itemsIterators = listItems.iterator();

        while (itemsIterators.hasNext()) {
            String row = itemsIterators.next();
            String items = buildResult(row);
            result.append(items);
            if (itemsIterators.hasNext()) {
                result.append("\n");
            }
        }

        return result.toString();
    }

    private static String buildResult(String row)throws APIException{
        DataFile dataFile = dataProcessor.processStringData(row);
        Package packageResult = process.computeBestPackage(dataFile.getTotalWeight(), dataFile.getItemList());
        StringBuilder itemsBuilder = new StringBuilder();
        if (packageResult.getItemId() != null) {
            packageResult.getItemId().forEach(itemId -> itemsBuilder.append(itemId).append(" "));
        } else {
            itemsBuilder.append("-");
        }
        return itemsBuilder.toString().trim();
    }


}
