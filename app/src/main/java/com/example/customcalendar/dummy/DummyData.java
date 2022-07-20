package com.example.customcalendar.dummy;

import com.example.customcalendar.calendar.model.SpecialDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ömer AKKOYUN on 25.05.2022.
 */
public  class DummyData {

   public static ArrayList<SpecialDay> getTestSpecialDays(){
        // test dataÒ
        ArrayList<SpecialDay> specialDays = new ArrayList<>();
        specialDays.add(new SpecialDay("2022-04-13", true, false, ""));
        specialDays.add(new SpecialDay("2022-05-03", false, true, ""));
        specialDays.add(new SpecialDay("2022-05-08", true, true, ""));
        specialDays.add(new SpecialDay("2022-05-05", true, true, ""));
        specialDays.add(new SpecialDay("2022-05-14", false, true, ""));
        specialDays.add(new SpecialDay("2022-05-18", true, true, ""));
        specialDays.add(new SpecialDay("2022-05-21", true, false, ""));
        specialDays.add(new SpecialDay("2022-05-23", true, true, ""));
        specialDays.add(new SpecialDay("2022-05-26", true, false, ""));
        specialDays.add(new SpecialDay("2022-06-21", true, false, ""));
        specialDays.add(new SpecialDay("2022-06-26", true, true, ""));
        specialDays.add(new SpecialDay("2022-06-06", false, true, ""));
        specialDays.add(new SpecialDay("2022-08-11", false, true, ""));
        specialDays.add(new SpecialDay("2022-07-18", true, true, ""));
        specialDays.add(new SpecialDay("2022-07-08", false, true, ""));
        return  specialDays;
    }

}
