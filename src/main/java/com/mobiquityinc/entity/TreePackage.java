package com.mobiquityinc.entity;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class TreePackage {

    @NonNull
    private Item item;
    @Setter
    private List<TreePackage> treePackageList;

}
