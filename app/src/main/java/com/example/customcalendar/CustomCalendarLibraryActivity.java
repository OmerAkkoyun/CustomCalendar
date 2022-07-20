package com.example.customcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.example.customcalendar.R;
import com.example.customcalendar.calendar.model.SpecialDay;
import com.example.customcalendar.dummy.DummyData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomCalendarLibraryActivity extends AppCompatActivity {
    List<EventDay> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_calendar_library);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        // selected days
       calendarView.setSelectedDates(getSelectedDays());
       calendarView.setEvents(events);

        // highlighted days
       // calendarView.setHighlightedDays(getSelectedDays());



        // day click listener
        calendarView.setOnDayClickListener(eventDay -> {
            Calendar clickedDayCalendar = eventDay.getCalendar();
            Toast.makeText(getApplicationContext(), ""+eventDay.getCalendar().get(Calendar.DAY_OF_MONTH), Toast.LENGTH_SHORT).show();
            Log.d("CALENDAR:",""+ clickedDayCalendar);
        });

    }

    private List<Calendar> getSelectedDays() {
        List<Calendar> calendars = new ArrayList<>();
        ArrayList<SpecialDay> specialDays = DummyData.getTestSpecialDays();

        for (int i =0;i < specialDays.size();i++){
            String[] splittedDate =  specialDays.get(i).getSpecialDayName().split("-");
            Calendar calendar = DateUtils.getCalendar();
            calendar.set(Integer.parseInt(splittedDate[0]), Integer.parseInt(splittedDate[1])-1, Integer.parseInt(splittedDate[2]));
            calendars.add(calendar);

            if (specialDays.get(i).getSpecialGlobal() && specialDays.get(i).getSpecialEmployee()){
                events.add(new EventDay(calendar, R.drawable.dots));
            }else if (specialDays.get(i).getSpecialEmployee()){
                events.add(new EventDay(calendar, R.drawable.calendar_dot_red));
            }else{
                events.add(new EventDay(calendar, R.drawable.calendar_dot_blue));
            }
            // if only global day
        }

       // Calendar calendar = Calendar.getInstance();
        // calendar.set(2019, 7, 5);

      /*  for (int i = 1; i < 5; i++) {
            Calendar calendar = DateUtils.getCalendar();
            calendar.add(Calendar.DAY_OF_MONTH, i);
            calendars.add(calendar);

            // if only global day
            events.add(new EventDay(calendar, R.drawable.calendar_dot_blue));
        }
*/
        return calendars;
    }
}