package com.example.taskmaster.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.example.taskmaster.Adapters.TaskAdapter;
import com.example.taskmaster.R;

import java.util.ArrayList;
import java.util.List;

public class AllTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AllTasks.this);
        String teamId = sharedPreferences.getString("teamId", "");


        //Get the data from data base and display it in recyclerView
        System.out.println("**********************************" + teamId);

        if (!teamId.equals("")) {
            RecyclerView recyclerView = findViewById(R.id.allTasksRecycler);

            //get the data from the dynamoDB
            Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message message) {
                    recyclerView.getAdapter().notifyDataSetChanged();
                    return false;
                }
            });

            List<Task> taskList = new ArrayList<Task>();
            Amplify.API.query(
                    ModelQuery.get(Team.class, teamId),
                    response -> {
                        Log.i("response", response.toString());
                        for (Task task : response.getData().getAmpTasks()) {
                            taskList.add(task);
                        }
                        handler.sendEmptyMessage(1);
                    },
                    error -> Log.e("TaskMaster", error.toString(), error)
            );

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new TaskAdapter(taskList));
        }

    }
}