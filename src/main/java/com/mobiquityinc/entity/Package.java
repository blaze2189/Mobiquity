package com.mobiquityinc.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Package implements Comparable, Cloneable {

    List<Integer> itemId;
    Double weight;
    Integer price;

    @Override
    public int compareTo(Object o) {
        if (o instanceof Package) {
            Package p = (Package) o;
            return this.getPrice() > p.getPrice() ? 1 : -1;
        }
        return 0;
    }

    @Override
    public Object clone() throws
            CloneNotSupportedException {
        //return super.clone();
        Package clone = new Package();
        clone.setWeight(this.getWeight());
        clone.setPrice(this.getPrice());
        List<Integer> cloneItems = new ArrayList<>();
        this.getItemId().forEach(cloneItems::add);
        clone.setItemId(cloneItems);
        return clone;
    }
}
