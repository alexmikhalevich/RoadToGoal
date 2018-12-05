package com.mikhalevich.roadtogoal;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikhalevich.roadtogoal.domain.ViewGoalEntityProxy;
import com.mikhalevich.roadtogoal.circlemenu.CircleMenuView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<ViewGoalEntityProxy> goals;

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        final TextView nameView;
        final ProgressBar progressBar;
        public LinearLayout viewForeground;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.task_name);
            progressBar = view.findViewById(R.id.progressBar);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            viewForeground = view.findViewById(R.id.foregroundLayout);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            ViewGoalEntityProxy goal = goals.get(position);
            Intent intent = new Intent(view.getContext(), ChildrenGoalsActivity.class);
            intent.putExtra("parentId", goal.getId());
            intent.putExtra("parentName", goal.getName());
            view.getContext().startActivity(intent);
        }

        @Override
        public boolean onLongClick(View view) {
            CircleMenuView circleMenuView = view.findViewById(R.id.circleMenu);
            circleMenuView.setMCenterX(circleMenuView.getWidth() / 2);
            circleMenuView.setMCenterY(circleMenuView.getHeight() / 2);
            circleMenuView.open(true);

            return true;
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