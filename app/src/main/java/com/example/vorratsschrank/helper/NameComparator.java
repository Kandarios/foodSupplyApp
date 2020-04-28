package com.example.vorratsschrank.helper;

import com.example.vorratsschrank.basic.VorratsItem;

import java.util.Comparator;

public class NameComparator implements Comparator<VorratsItem> {

    @Override
    public int compare(VorratsItem item1, VorratsItem item2) {
        return item1.getName().compareTo(item2.getName());
    }

}
