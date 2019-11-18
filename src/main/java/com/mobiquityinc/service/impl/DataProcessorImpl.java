package com.mobiquityinc.service.impl;

import com.mobiquityinc.entity.DataFile;
import com.mobiquityinc.entity.Item;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.service.DataProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataProcessorImpl implements DataProcessor {


    @Override
    public DataFile processStringData(String stringLine) throws APIException {

        DataFile dataFile;
        final String INVALID_FILE_FORMAT = "Invalid file format";
        String itemsAsString;
        Double weight;

        Scanner data = new Scanner(stringLine).useDelimiter(" : ");

        if (data.hasNextDouble()) {
            weight = data.nextDouble();
        } else {
            data.close();
            throw new APIException(INVALID_FILE_FORMAT);
        }
        if (data.hasNext()) {
            itemsAsString = data.next();
        } else {
            data.close();
            throw new APIException(INVALID_FILE_FORMAT);
        }

        data.close();
        List<Item> itemList = readItemsFromString(itemsAsString);
        dataFile = new DataFile(weight, itemList);
        return dataFile;
    }

    private List<Item> readItemsFromString(String itemsLine) {

        List<Item> itemList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\(([^\\)]+)\\)");
        Matcher matcher = pattern.matcher(itemsLine);
        while (matcher.find()) {
            String itemAsString = matcher.group();
            Item item = stringToItem(itemAsString);
            itemList.add(item);

        }
        return itemList;
    }

    private Item stringToItem(String itemAsString) {
        Pattern patternParentheses = Pattern.compile("[\\(\\)€]");
        Matcher matcherParenteheses = patternParentheses.matcher(itemAsString);
        if (matcherParenteheses.find()) {
            String itemData = matcherParenteheses.replaceAll("");
            Scanner scanner = new Scanner(itemData).useDelimiter(",");
            Integer itemId = scanner.nextInt();
            Double weight = scanner.nextDouble();
            Integer price = scanner.nextInt();
            return new Item(itemId, weight, price);
        }
        return null;
    }
}
