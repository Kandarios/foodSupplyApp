package com.example.vorratsschrank.basic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.orm.SugarRecord;

public class VorratsItem extends SugarRecord {


    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getAblaufDatum() { return ablaufDatum; }

    public void setAblaufDatum(String ablaufDatum) {
        this.ablaufDatum = ablaufDatum;
    }

    String name;
    String ablaufDatum;

    public VorratsItem() {

    }

    public VorratsItem(String title, String ablaufDatum) {
        this.name = title;
        this.ablaufDatum = ablaufDatum;
    }

}
