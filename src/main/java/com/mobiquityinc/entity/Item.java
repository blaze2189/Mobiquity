package com.mobiquityinc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Item implements Comparable {

    private Integer id;
    private Double weight;
    private Integer price;

    @Override
    public int compareTo(Object o) {
        if(o instanceof Item){
            Item item=(Item)o;
            return id<item.getId()?-1:1;
        }
        return 0;
    }
}