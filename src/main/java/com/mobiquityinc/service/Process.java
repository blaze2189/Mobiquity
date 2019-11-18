package com.mobiquityinc.service;

import com.mobiquityinc.entity.Item;
import com.mobiquityinc.entity.Package;
import com.mobiquityinc.exception.APIException;

import java.util.List;

public interface Process {

    List<Item> validateItems(Double weight, List<Item> itemList);

    Package computeBestPackage(Double weight, List<Item> itemList);
}
