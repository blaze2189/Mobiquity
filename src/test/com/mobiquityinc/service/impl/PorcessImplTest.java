package com.mobiquityinc.service.impl;

import com.mobiquityinc.entity.Item;
import com.mobiquityinc.entity.Package;
import com.mobiquityinc.service.Process;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PorcessImplTest {

    private Process process = new ProcessImpl();

    @Test
    public void testValidateItems() {
        Double weight = 20.00;
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, 12.01, 101));
        itemList.add(new Item(2, 50.65, 99));
        itemList.add(new Item(3, 20.0, 50));
        itemList.add(new Item(4, 19.8, 100));
        List<Item> newItemList = process.validateItems(weight, itemList);
        assertEquals(itemList.size() - 1, newItemList.size());
    }

    @Test
    public void testCalculate() {
        Double totalWeight = 81.0;
        List<Item> itemList = new ArrayList<>();
        List<Integer> listInteger = Collections.singletonList(4);
        Integer price = 76;
        Double weight = 72.30;
        Package expected = new Package(listInteger, weight, price);
        itemList.add(new Item(1, 53.38, 45));
        itemList.add(new Item(2, 88.62, 98));
        itemList.add(new Item(3, 78.48, 3));
        itemList.add(new Item(4, 72.30, 76));
        itemList.add(new Item(5, 30.18, 9));
        itemList.add(new Item(6, 46.34, 48));
        Package actual = process.computeBestPackage(totalWeight, itemList);
        assertEquals(expected, actual);

    }

    @Test
    public void testCalculateNotOfficial() {
        Double weight = 81.0;
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, 53.38, 44));
        itemList.add(new Item(2, 88.62, 98));
        itemList.add(new Item(3, 78.48, 3));
        itemList.add(new Item(4, 72.30, 76));
        itemList.add(new Item(5, 30.18, 9));
        itemList.add(new Item(6, 46.34, 48));
        itemList.add(new Item(7, 20.34, 48));
        itemList.add(new Item(8, 9.34, 48));
        process.computeBestPackage(weight, itemList);
    }

    @Test
    public void testCalculateThird() {

        double d = 14.55D;
        double d2 = 60.02D;
        List<Integer> listInteger = Arrays.asList(2, 7);
        Integer price = 148;
        Double weight = 74.57;
        Package expected = new Package(listInteger, weight, price);
        Double totalWeight = 75.0;
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, 85.31, 29));
        itemList.add(new Item(2, 14.55, 74));
        itemList.add(new Item(3, 3.98, 16));
        itemList.add(new Item(4, 26.24, 55));
        itemList.add(new Item(5, 63.69, 52));
        itemList.add(new Item(6, 76.25, 75));
        itemList.add(new Item(7, 60.02, 74));
        itemList.add(new Item(8, 93.18, 35));
        itemList.add(new Item(9, 89.95, 78));
        Package actual = process.computeBestPackage(totalWeight, itemList);
        assertEquals(expected, actual);
    }
}