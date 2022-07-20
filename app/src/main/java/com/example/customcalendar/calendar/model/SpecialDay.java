package com.example.customcalendar.calendar.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Ömer AKKOYUN on 20.05.2022.
 */
public class SpecialDay {
    private String SpecialDayName;
    private Boolean isSpecialGlobal;
    private Boolean isSpecialEmployee;
    private String backgroundColorCode;

    public SpecialDay(String specialDayName, Boolean isSpecialGlobal, Boolean isSpecialEmployee, String backgroundColor) {
        SpecialDayName = specialDayName;
        this.isSpecialGlobal = isSpecialGlobal;
        this.isSpecialEmployee = isSpecialEmployee;
        this.backgroundColorCode = backgroundColor;
    }

    public String getSpecialDayName() {
        return SpecialDayName;
    }

    public Boolean getSpecialGlobal() {
        return isSpecialGlobal;
    }

    public Boolean getSpecialEmployee() {
        return isSpecialEmployee;
    }

    public String  getBackgroundColor() {
        return backgroundColorCode;
    }
}
