package com.example.customcalendar.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.customcalendar.R;
import com.example.customcalendar.calendar.model.CalendarItemModel;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    private final ArrayList<CalendarItemModel> daysOfMonthList;
    private ArrayList<String> specialDays;
    private final OnItemListener onItemListener;
    private final String todayDateString;
    private Context context;

    public CalendarAdapter(ArrayList<CalendarItemModel> daysOfMonth, ArrayList<String> specialDays, String today, OnItemListener onItemListener) {
        this.daysOfMonthList = daysOfMonth;
        this.onItemListener = onItemListener;
        this.specialDays = specialDays;
        this.todayDateString = today;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        // layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.cellDayNumberText.setText(daysOfMonthList.get(position).getDateDayNumber());
        if (daysOfMonthList.get(position).getDateDayNumber().isEmpty()) {
            holder.cellDayNumberText.setBackground(ContextCompat.getDrawable(context, R.drawable.item_calendar_background_gray));
            holder.lnLayoutDotsContainer.setVisibility(View.GONE);
        } else {
            // today background
            if (todayDateString.equals(daysOfMonthList.get(position).getDate().toString())) {
                holder.cellDayNumberText.setBackgroundColor(ContextCompat.getColor(context, R.color.light_blue));
                holder.cellDayNumberText.setTextColor(ContextCompat.getColor(context, R.color.white));
            } else {
                holder.cellDayNumberText.setBackground(ContextCompat.getDrawable(context, R.drawable.item_calendar_background_white));
            }

            // special day check
            // Özel güne göre noktalar gösterilecek.
            for (int i = 0; i <= specialDays.size()-1; i++) {
                if (daysOfMonthList.get(position).getDate().toString().equals(specialDays.get(i))){
                    holder.lnLayoutDotsContainer.setVisibility(View.VISIBLE);
                    break;
                }

                else holder.lnLayoutDotsContainer.setVisibility(View.GONE);
            }


        }
    }

    @Override
    public int getItemCount() {
        return daysOfMonthList.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText);
    }
}
