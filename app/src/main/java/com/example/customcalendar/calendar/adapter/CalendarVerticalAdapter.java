package com.example.customcalendar.calendar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customcalendar.R;
import com.example.customcalendar.calendar.model.CalendarItemModel;
import com.example.customcalendar.calendar.model.SpecialDay;
import com.example.customcalendar.databinding.ItemCalendarViewBinding;
import com.example.customcalendar.dummy.DummyData;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ömer AKKOYUN on 19.07.2022.
 */

public class CalendarVerticalAdapter extends
        RecyclerView.Adapter<CalendarVerticalAdapter.ViewHolder> {

    private static final String TAG = CalendarVerticalAdapter.class.getSimpleName();

    private Context context;
    private ArrayList<LocalDate> list;
    private  CalendarAdapter.OnItemListener onItemListener;

    public CalendarVerticalAdapter(Context context,
                                   ArrayList<LocalDate> list,
                                   CalendarAdapter.OnItemListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemCalendarViewBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_calendar_view, parent, false);

        return new ViewHolder(binding);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCalendarViewBinding binding;

        public ViewHolder(ItemCalendarViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final LocalDate model,
                         final CalendarAdapter.OnItemListener listener) {

            binding.tvMonthName.setText(monthYearFromDate(model, "MMMM yyyy"));
            ArrayList<CalendarItemModel> daysInMonth = getDaysFromDate(model);

            // test data
            ArrayList<SpecialDay> specialDays = DummyData.getTestSpecialDays();

            String today = LocalDate.now().toString();
            CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, specialDays, today, listener);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(itemView.getContext(), 7);
            binding.calendarRecyclerView.setLayoutManager(layoutManager);
            binding.calendarRecyclerView.setAdapter(calendarAdapter);

        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LocalDate item = list.get(position);

        //Todo: Setup viewholder for item
        holder.bind(item, onItemListener);
    }


    private static String monthYearFromDate(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    private static ArrayList<CalendarItemModel> getDaysFromDate(LocalDate date) {
        int emptyCount = 0;
        ArrayList<CalendarItemModel>  calendarItemModelList = new ArrayList<CalendarItemModel>();
        YearMonth yearMonth = YearMonth.from(date); // 2022-05 (yıl ve ay bilgisini )

        int totalDaysInMonth = yearMonth.lengthOfMonth();//secilen ay'ın icinde kac gun var

        LocalDate firstOfMonth = date.withDayOfMonth(1); // orn: 2022-05-01, ilk ay
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
                String current = monthYearFromDate(date, "yyyy-MM") + "-" + dayText;
                calendarItemModelList.add(new CalendarItemModel(LocalDate.parse(current), String.valueOf(i - dayOfWeek))); // bu gunu ekle
                emptyCount = 0;
            }
        }

        return calendarItemModelList;
    }

}