package com.mikhalevich.roadtogoal;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mikhalevich.roadtogoal.domain.GoalEntity;
import com.mikhalevich.roadtogoal.domain.GoalRepository;
import com.mikhalevich.roadtogoal.domain.InsertGoalEntityProxy;
import com.mikhalevich.roadtogoal.domain.dbtasks.InsertGoalTask;

public class AddGoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
    }

    public void onClickCancel(View view) {
        this.finish();
    }

    public void onClickOk(View view) {
        EditText editText = findViewById(R.id.taskNameEdit);

        InsertGoalEntityProxy newEntity = new InsertGoalEntityProxy(new GoalEntity());
        newEntity.setName(editText.getText().toString());
        InsertGoalTask task = new InsertGoalTask();
        task.execute(newEntity);

        this.finish();
    }
}
