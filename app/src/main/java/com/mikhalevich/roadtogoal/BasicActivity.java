package com.mikhalevich.roadtogoal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikhalevich.roadtogoal.domain.GoalRepository;
import com.mikhalevich.roadtogoal.domain.ViewGoalEntityProxy;
import com.mikhalevich.roadtogoal.domain.dbtasks.DeleteGoalTask;
import com.mikhalevich.roadtogoal.domain.dbtasks.LoadGoalsTask;

import java.util.ArrayList;

import lombok.Getter;

@SuppressLint("Registered")
public class BasicActivity extends AppCompatActivity
        implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    protected RecyclerView recyclerView;
    protected RecyclerView.Adapter adapter;

    protected ArrayList<ViewGoalEntityProxy> goalList;

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof RecyclerAdapter.ViewHolder) {
            String name = goalList.get(viewHolder.getAdapterPosition()).getName();
            final ViewGoalEntityProxy deletedItem = goalList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();
            goalList.remove(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            CoordinatorLayout coordinatorLayout = findViewById(R.id.main_layout);
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " removed from goal list!",
                            Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goalList.add(deletedIndex, deletedItem);
                    adapter.notifyItemInserted(deletedIndex);
                }
            });
            snackbar.addCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar snackbar, int event) {
                    DeleteGoalTask<ViewGoalEntityProxy> task = new DeleteGoalTask<>();
                    task.execute(deletedItem);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    protected void initializeFloatingActionButton(final BasicActivity parentActivity,
                                                  final int parentId) {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddGoalActivity.class);
                intent.putExtra("parentId", parentId);
                parentActivity.startActivity(intent);
            }
        });
    }

    protected void initializeRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(goalList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new RecyclerItemTouchHelper(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GoalRepository.initRepository(getApplication());
        goalList = new ArrayList<>();

        initializeRecyclerView();
    }

    @Override
    protected void onResume() {
        Log.d("Info", "onResume called");

        super.onResume();

        goalList.clear();
        goalList.addAll(GoalRepository.getRepository()
                .getAllGoals(false, -1, ViewGoalEntityProxy.class));
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}