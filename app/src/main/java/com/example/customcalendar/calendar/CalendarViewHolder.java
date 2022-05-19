package com.example.customcalendar.calendar;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customcalendar.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView cellDayNumberText;
    public final LinearLayout lnLayoutDotsContainer;
    public final FrameLayout frmLayoutItemCalendar;
    private final CalendarAdapter.OnItemListener onItemListener;
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        cellDayNumberText = itemView.findViewById(R.id.cellDayText);
        lnLayoutDotsContainer = itemView.findViewById(R.id.lnLayoutDotsContainer);
        frmLayoutItemCalendar = itemView.findViewById(R.id.frmLayoutItemCalendar);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(), (String) cellDayNumberText.getText());
    }
}
