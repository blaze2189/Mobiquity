package com.mobiquityinc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class DataFile {

    private Double totalWeight;
    private List<Item> itemList;

}
