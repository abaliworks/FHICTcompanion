package com.example.ab.fhictcompanion.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ab.fhictcompanion.Model.Schedule;
import com.example.ab.fhictcompanion.R;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    private ArrayList<Schedule> mSchedules;

    public ScheduleAdapter(ArrayList<Schedule> schedules) {
        mSchedules = schedules;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subject, subjectName, teacher, room, startTime, endTime;
        public View colorLine;

        public MyViewHolder(View itemView) {
            super(itemView);
            subject = (TextView) itemView.findViewById(R.id.subjectList);
            subjectName = (TextView) itemView.findViewById(R.id.subjectNameList);
            teacher = (TextView) itemView.findViewById(R.id.teacherList);
            room = (TextView) itemView.findViewById(R.id.roomList);
            startTime = (TextView) itemView.findViewById(R.id.startList);
            endTime = (TextView) itemView.findViewById(R.id.endList);

            colorLine = (View) itemView.findViewById(R.id.colorLine);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_schedule, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Schedule schedule = mSchedules.get(position);

        holder.subject.setText(schedule.getSubject());
        holder.subjectName.setText(schedule.getSubjectName());
        holder.teacher.setText(schedule.getTeacher());
        holder.room.setText(schedule.getRoom());
        holder.startTime.setText(schedule.getStartTime());
        holder.endTime.setText(schedule.getEndTime());

        holder.colorLine.setBackgroundColor(schedule.getColorLine());
    }

    @Override
    public int getItemCount() {
        return mSchedules.size();
    }

}
