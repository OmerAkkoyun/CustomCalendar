package com.example.customcalendar.calendar.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.customcalendar.R;
import com.example.customcalendar.calendar.model.CalendarItemModel;
import com.example.customcalendar.calendar.model.SpecialDay;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {
    private final ArrayList<CalendarItemModel> daysOfMonthList;
    private final ArrayList<SpecialDay> specialDays;
    private final OnItemListener onItemListener;
    private final String todayDateString;
    private Context context;
    private final ArrayList<CalendarViewHolder> holderList = new ArrayList<>();
    private Boolean isMultiSelectionActive = false;

    public CalendarAdapter(ArrayList<CalendarItemModel> daysOfMonth, ArrayList<SpecialDay> specialDays, String today, OnItemListener onItemListener) {
        this.daysOfMonthList = daysOfMonth;
        this.onItemListener = onItemListener;
        this.specialDays = specialDays;
        this.todayDateString = today;
    }

    public void setMultiSelectionStatus(Boolean isMultiSelectionActive){
        this.isMultiSelectionActive = isMultiSelectionActive;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);

        return new CalendarViewHolder(view);
    }

    class CalendarViewHolder extends RecyclerView.ViewHolder {
        public final TextView cellDayNumberText;
        public final LinearLayout lnLayoutDotsContainer;
        public final FrameLayout frmLayoutItemCalendar;
        public final View vSpecialGlobalDot;
        public final View vSpecialEmployeeDot;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            cellDayNumberText = itemView.findViewById(R.id.cellDayText);
            lnLayoutDotsContainer = itemView.findViewById(R.id.lnLayoutDotsContainer);
            frmLayoutItemCalendar = itemView.findViewById(R.id.frmLayoutItemCalendar);
            vSpecialGlobalDot = itemView.findViewById(R.id.specialGlobalDot);
            vSpecialEmployeeDot = itemView.findViewById(R.id.specialEmployeeDot);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        if (!holderList.contains(holder)) {
            holderList.add(holder);
          //  Log.d("Day::: eklendi ", holder.cellDayNumberText.getText().toString());
        }

        holder.cellDayNumberText.setText(daysOfMonthList.get(position).getDateDayNumber());
        if (daysOfMonthList.get(position).getDateDayNumber().isEmpty()) {
            holder.cellDayNumberText.setBackground(ContextCompat.getDrawable(context, R.drawable.item_calendar_background_white_borderless));
            holder.lnLayoutDotsContainer.setVisibility(View.GONE);
        }
        else {

            // check date is today
            checkDateIsToday(holder,position);

            // special day check
            for (int i = 0; i <= specialDays.size()-1; i++) {
                if (daysOfMonthList.get(position).getDate().toString().equals(specialDays.get(i).getSpecialDayName())){

                    // check is special global day
                    if (specialDays.get(i).getSpecialGlobal()) holder.vSpecialGlobalDot.setVisibility(View.VISIBLE);
                    else holder.vSpecialGlobalDot.setVisibility(View.GONE);
                    // check is special employee day
                    if (specialDays.get(i).getSpecialEmployee()) holder.vSpecialEmployeeDot.setVisibility(View.VISIBLE);
                    else holder.vSpecialEmployeeDot.setVisibility(View.GONE);

                    // set special background
                    if (!specialDays.get(i).getBackgroundColor().isEmpty()){
                        holder.cellDayNumberText.setBackgroundColor(Color.parseColor(specialDays.get(i).getBackgroundColor()));
                        holderList.remove(holder);
                    }else{
                        setCellBackgroundColor(holder.cellDayNumberText,ContextCompat.getDrawable(context, R.drawable.item_calendar_background_white));
                        checkDateIsToday(holder,position);
                    }

                    holder.lnLayoutDotsContainer.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    holder.lnLayoutDotsContainer.setVisibility(View.GONE);

                }
            }

            // item cell click listener
            holder.cellDayNumberText.setOnClickListener(v -> {
                Log.d("MODEL",daysOfMonthList.toString());
                onItemListener.onItemClick(daysOfMonthList.get(position));
                if (isMultiSelectionActive){

                }else {
                    // clear background colors
                    clearBackgroundColors();
                    setCellBackgroundColor(holder.cellDayNumberText,ContextCompat.getDrawable(context, R.drawable.item_calendar_selected_background_color));
                    holder.cellDayNumberText.setTextColor(Color.WHITE);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return daysOfMonthList.size();
    }

    public void setCellBackgroundColor(TextView textView, Drawable color){
        textView.setBackground(color);
    }

    private void checkDateIsToday(CalendarViewHolder holder, int position){
        // today background
      /* if (todayDateString.equals(daysOfMonthList.get(position).getDate().toString())) {
            holder.cellDayNumberText.setBackgroundColor(ContextCompat.getColor(context, R.color.light_blue));
            holder.cellDayNumberText.setTextColor(ContextCompat.getColor(context, R.color.white));
            holderList.remove(holder);
        } else {
            holder.cellDayNumberText.setBackground(ContextCompat.getDrawable(context, R.drawable.item_calendar_background_white));
        }

       */
    }


    public interface OnItemListener {
        void onItemClick(CalendarItemModel calendarItemModel);
    }

    private void clearBackgroundColors(){
        for (CalendarViewHolder c : holderList){
            c.cellDayNumberText.setTextColor(Color.BLACK);
            setCellBackgroundColor(c.cellDayNumberText,ContextCompat.getDrawable(context, R.drawable.item_calendar_background_white));
        }
    }

}




