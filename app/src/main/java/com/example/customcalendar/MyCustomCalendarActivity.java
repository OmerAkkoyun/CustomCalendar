package com.example.customcalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customcalendar.calendar.adapter.CalendarAdapter;
import com.example.customcalendar.calendar.adapter.CalendarVerticalAdapter;
import com.example.customcalendar.calendar.model.CalendarItemModel;
import com.example.customcalendar.calendar.model.SpecialDay;
import com.example.customcalendar.databinding.ActivityMyCustomCalendarBinding;
import com.example.customcalendar.dummy.DummyData;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MyCustomCalendarActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private LocalDate selectedDate;
    private ArrayList<LocalDate> selectedDateList;
    private ActivityMyCustomCalendarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom_calendar);
        initWidgets();
        // this.getSupportActionBar().hide();
        selectedDate = LocalDate.now();

        setAdapterDates();
    }

    private void initWidgets() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_custom_calendar);
    }

    private void setAdapterDates() {
        selectedDateList = new ArrayList<>();
        selectedDateList = get1YearDates();

        CalendarVerticalAdapter calendarVerticalAdapter = new CalendarVerticalAdapter(this, selectedDateList, this);
        binding.rvVerticalCalendars.setAdapter(calendarVerticalAdapter);
        calendarVerticalAdapter.notifyDataSetChanged();
        binding.rvVerticalCalendars.scrollToPosition(13);
    }

   /* private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate, "MMMM yyyy"));
        ArrayList<CalendarItemModel> daysInMonth = getDaysFromDate(selectedDate);

        // test data
        ArrayList<SpecialDay> specialDays = DummyData.getTestSpecialDays();

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, specialDays, today.toString(), this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }*/

   /* private ArrayList<CalendarItemModel> getDaysFromDate(LocalDate date) {
        int emptyCount = 0;
        calendarItemModelList = new ArrayList<CalendarItemModel>();
        YearMonth yearMonth = YearMonth.from(date); // 2022-05 (yıl ve ay bilgisini )

        int totalDaysInMonth = yearMonth.lengthOfMonth();//secilen ay'ın icinde kac gun var

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1); // orn: 2022-05-01, ilk ay
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue(); // ilk haftanın gunleri,  ilk satırda kac gun gosterilecek hesaplamak ıcın

        for (int i = 1; i <= 42; i++) {  // 7 sütun 6 satır = 42
            if (i <= dayOfWeek || i > totalDaysInMonth + dayOfWeek) {
                calendarItemModelList.add(new CalendarItemModel(null, "")); // bu gunu bos ekle


                emptyCount +=1; // eger ilk 7 gun bos ise gizlemek icin, boslukları kaldırmak icin kontrol edecegız.
                if (emptyCount>6 && i <8) {
                    calendarItemModelList = new ArrayList<CalendarItemModel>();
                    Log.d("DAYYY","bos gun "+i +" toplam bos gun =" + emptyCount);
                    emptyCount = 0;
                }


            } else {
                String dayText = String.valueOf(i - dayOfWeek);
                if (dayText.length() < 2) dayText = "0" + dayText;
                String current = monthYearFromDate(selectedDate, "yyyy-MM") + "-" + dayText;
                calendarItemModelList.add(new CalendarItemModel(LocalDate.parse(current), String.valueOf(i - dayOfWeek))); // bu gunu ekle
                emptyCount = 0;
            }
        }





        return calendarItemModelList;
    }

*/


  /*  public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }*/

    /*   public void nextMonthAction(View view) {
           selectedDate = selectedDate.plusMonths(1);
           setMonthView();
       }

       public void getDateTRFormat(LocalDate date) {

       }

     */
    private String monthYearFromDate(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    @Override
    public void onItemClick(CalendarItemModel calendarItemModel) {
            String current = monthYearFromDate(calendarItemModel.getDate(), "yyyy-MM-dd") ;
            // String test = calendarItemModelList.get(position).getDate().toString();
            Toast.makeText(this, current, Toast.LENGTH_SHORT).show();
    }


    private ArrayList<LocalDate> get1YearDates(){
        selectedDateList.clear();
       for (int i = 13; i > 0; i--) { selectedDateList.add(selectedDate.minusMonths(i)); }
        for (int i = 0; i < 13; i++) { selectedDateList.add(selectedDate.plusMonths(i)); }
        return selectedDateList;
    }
}