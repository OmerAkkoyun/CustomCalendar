package com.example.customcalendar.calendar.model;

import java.time.LocalDate;

public class CalendarItemModel {

    private LocalDate Date;
    private String DateDayNumber;

    public CalendarItemModel(LocalDate date, String dateDayNumber) {
        Date = date;
        DateDayNumber = dateDayNumber;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public String getDateDayNumber() {
        return DateDayNumber;
    }

    public void setDateDayNumber(String dateDayNumber) {
        DateDayNumber = dateDayNumber;
    }
}
