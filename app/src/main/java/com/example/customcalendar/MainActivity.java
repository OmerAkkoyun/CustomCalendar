package com.example.customcalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customcalendar.calendar.CalendarAdapter;
import com.example.customcalendar.calendar.model.CalendarItemModel;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private LocalDate today;
    private ArrayList<CalendarItemModel> calendarItemModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        selectedDate = LocalDate.now();
        today = LocalDate.now();
        setMonthView();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        calendarRecyclerView.setHasFixedSize(true);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate, "MMMM yyyy"));
        ArrayList<CalendarItemModel> daysInMonth = daysInMonthArray(selectedDate);

        // test data
        ArrayList<String> specialDays = new ArrayList<>();
        specialDays.add("2022-05-03");
        specialDays.add("2022-05-08");
        specialDays.add("2022-05-14");
        specialDays.add("2022-05-18");
        specialDays.add("2022-05-20");
        specialDays.add("2022-05-21");
        specialDays.add("2022-05-23");
        specialDays.add("2022-05-26");
        specialDays.add("2022-06-21");
        specialDays.add("2022-06-26");

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, specialDays, today.toString(),this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<CalendarItemModel> daysInMonthArray(LocalDate date) {
        calendarItemModelList = new ArrayList<CalendarItemModel>();
        YearMonth yearMonth = YearMonth.from(date);

        int totalDaysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > totalDaysInMonth + dayOfWeek) {
                calendarItemModelList.add(new CalendarItemModel(null,""));
            } else {
                String dayText = String.valueOf(i - dayOfWeek);
                if (dayText.length()<2) dayText = "0"+dayText;
                String current =   monthYearFromDate(selectedDate, "yyyy-MM")+ "-" +dayText;

                calendarItemModelList.add(new CalendarItemModel(LocalDate.parse(current),String.valueOf(i - dayOfWeek)));
            }
        }
        return calendarItemModelList;
    }


    private String monthYearFromDate(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    public void getDateTRFormat(LocalDate date){

    }

    @Override
    public void onItemClick(int position, String dayText) {
        if (!dayText.equals("")) {
            if (dayText.length()<2) dayText = "0"+dayText;
            String current =   monthYearFromDate(selectedDate, "yyyy-MM")+ "-" + dayText;
            Toast.makeText(this, current, Toast.LENGTH_LONG).show();
        }
    }
}