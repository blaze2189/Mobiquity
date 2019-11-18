package com.mobiquityinc.service.impl;

import com.mobiquityinc.entity.Item;
import com.mobiquityinc.entity.Package;
import com.mobiquityinc.entity.TreePackage;
import com.mobiquityinc.service.Process;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProcessImpl implements Process {


    @Override
    public List<Item> validateItems(Double weight, List<Item> itemList) {
        return itemList.stream().filter(item -> item.getWeight() <= weight).sorted().collect(Collectors.toList());
    }

    @Override
    public Package computeBestPackage(Double weight, List<Item> itemList) {

        List<Item> validSortedItemList = validateItems(weight, itemList);

        return validSortedItemList.size() > 0 ? computeItems(validSortedItemList, weight) : new Package();


    }

    private Package computeItems(List<Item> itemList, Double weight) {
        List<TreePackage> treePackageList = new ArrayList<>();
        //init the tree
        itemList.forEach(item -> {
            TreePackage treePackage = new TreePackage(item);
            treePackageList.add(treePackage);
        });

        //create tree
        treePackageList.forEach(treePackage ->
                addItemsToPackage(treePackage, itemList, weight - treePackage.getItem().getWeight())
        );

        List<Package> listPackage = new ArrayList<>();
        treePackageList.forEach(treePackage ->
                treePackageToList(listPackage, new Package(new ArrayList<>(), 0.0, 0), treePackage));

        return findOptimalPackage(listPackage);
    }

    private List<Package> treePackageToList(List<Package> listPackage, Package pakage, TreePackage treePackage) {

        Package localPackage = cloneAndUpdatePackage(pakage, treePackage);

        if (treePackage.getTreePackageList() != null) {

            treePackage.getTreePackageList().forEach(treePackage1 ->
                    treePackageToList(listPackage, localPackage, treePackage1)
            );

        } else {
            listPackage.add(localPackage);
        }
        return listPackage;
    }

    @SneakyThrows
    private Package cloneAndUpdatePackage(Package pakage, TreePackage treePackage) {
        Package localPackage = (Package) pakage.clone();

        if (localPackage.getItemId() == null) {
            localPackage.setItemId(new ArrayList<>());
        }
        localPackage.getItemId().add(treePackage.getItem().getId());
        localPackage.setPrice(localPackage.getPrice() + treePackage.getItem().getPrice());
        localPackage.setWeight(localPackage.getWeight() + treePackage.getItem().getWeight());
        return localPackage;
    }


    private void addItemsToPackage(TreePackage treePackage, List<Item> itemList, Double weight) {
        List<Item> validItems = itemList.stream().filter(item -> item.getId() > treePackage.getItem().getId() &&
                item.getWeight() < weight).
                collect(Collectors.toList());

        if (validItems != null && !validItems.isEmpty()) {
            if (treePackage.getTreePackageList() == null) {
                List<TreePackage> listTreePackage = new ArrayList<>();
                treePackage.setTreePackageList(listTreePackage);
            }
        }

        if (validItems != null && validItems.size() > 1) {
            validItems.forEach(item -> addBranch(treePackage, item, validItems, weight - item.getWeight()));
        } else if (validItems != null && validItems.size() == 1) {
            addLeaf(treePackage, validItems.get(0));
        }
    }

    private void addBranch(TreePackage rootTreePackage, Item item, List<Item> items, Double weight) {
        TreePackage branchTreePackage = new TreePackage(item);
        addItemsToPackage(branchTreePackage, items, weight);
        rootTreePackage.getTreePackageList().add(branchTreePackage);
    }

    private void addLeaf(TreePackage rootTreePackage, Item item) {
        TreePackage treePackage = new TreePackage(item);
        rootTreePackage.getTreePackageList().add(treePackage);
    }

    private Package findOptimalPackage(List<Package> packageList) {

        Integer maxPrice = packageList.stream().max(Package::compareTo).get().getPrice();

        if (packageList.stream().filter(pakage -> Objects.equals(pakage.getPrice(), maxPrice)).count() > 1) {
            return packageList.stream().filter(pakage -> pakage.getPrice() < maxPrice).max(Package::compareTo).get();
        } else {
            return packageList.stream().filter(pakage -> Objects.equals(pakage.getPrice(), maxPrice)).findFirst().get();
        }
    }

}
