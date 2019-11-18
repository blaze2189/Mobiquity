package com.mobiquityinc.service;

import com.mobiquityinc.entity.Item;
import com.mobiquityinc.entity.Package;
import com.mobiquityinc.exception.APIException;

import java.util.List;

public interface Process {

    Package computeBestPackage(Double weight, List<Item> itemList) throws APIException;
}
