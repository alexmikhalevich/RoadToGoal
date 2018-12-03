package com.mikhalevich.roadtogoal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikhalevich.roadtogoal.domain.ViewGoalEntityProxy;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private List<ViewGoalEntityProxy> goals;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView nameView;
        final ProgressBar progressBar;
        public LinearLayout viewForeground;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.task_name);
            progressBar = view.findViewById(R.id.progressBar);
            view.setOnClickListener(this);
            viewForeground = view.findViewById(R.id.foregroundLayout);
        }

        public void onClick(View view){
            int position = getLayoutPosition();
        }
    }

    RecyclerAdapter(List<ViewGoalEntityProxy> mainTasks) {
        this.goals = mainTasks;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position){
        ViewGoalEntityProxy mainTask = goals.get(position);
        viewHolder.nameView.setText(mainTask.getName());
    }
    @Override
    public int getItemCount(){
        return goals.size();
    }

}
