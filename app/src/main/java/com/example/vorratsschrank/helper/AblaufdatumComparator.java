package com.example.vorratsschrank.helper;

import com.example.vorratsschrank.basic.VorratsItem;

import java.util.Comparator;

public class AblaufdatumComparator implements Comparator<VorratsItem> {


    /**the value {@code 0} if the argument string is equal to
     *          this string; a value less than {@code 0} if this string
     *          is lexicographically less than the string argument; and a
     *          value greater than {@code 0} if this string is
     *          lexicographically greater than the string argument.
    */

    @Override
    public int compare(VorratsItem item1, VorratsItem item2) {
        String datum1 = item1.getAblaufDatum();
        String datum2 = item2.getAblaufDatum();

        Integer jahr1, jahr2, monat1, monat2, tag1, tag2;

        jahr1 = Integer.valueOf(datum1.split("\\.")[2]);
        jahr2 = Integer.valueOf(datum2.split("\\.")[2]);
        monat1 = Integer.valueOf(datum1.split("\\.")[1]);
        monat2 = Integer.valueOf(datum2.split("\\.")[1]);
        tag1 = Integer.valueOf(datum1.split("\\.")[0]);
        tag2 = Integer.valueOf(datum2.split("\\.")[0]);

        if (jahr1.compareTo(jahr2)  != 0) {
            return jahr1.compareTo(jahr2);
        } else if (monat1.compareTo(monat2)  != 0) {
            return monat1.compareTo(monat2);
        } else {
            return tag1.compareTo(tag2);
        }
    }

}
