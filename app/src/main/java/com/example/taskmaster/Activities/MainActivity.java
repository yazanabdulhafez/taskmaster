package com.example.taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskmaster.Adapters.TaskAdapter;
import com.example.taskmaster.Database.AppDatabase;
import com.example.taskmaster.Database.TaskDao;
import com.example.taskmaster.Models.Task;
import com.example.taskmaster.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // Called when the activity is first created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       AppDatabase db=Room
               .databaseBuilder(getApplicationContext(), AppDatabase.class, "tasksDatabase")
               .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        List<Task> taskList = db.taskDao().getAll();
        System.out.println(taskList);

        RecyclerView recyclerView=findViewById(R.id.tasksResyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(taskList));

        Button addTaskButton = (Button) findViewById(R.id.addTask);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent goToAddTaskPage = new Intent(MainActivity.this, AddATask.class);
                startActivity(goToAddTaskPage);
            }
        });

        Button allTasksButton = (Button) findViewById(R.id.allTasks);
        allTasksButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent goToAllTasksPage = new Intent(MainActivity.this, AllTasks.class);
                startActivity(goToAllTasksPage);
            }
        });

        Button settingButton =findViewById(R.id.settingButton);
        settingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent goToSettingPage = new Intent(MainActivity.this, Settings.class);
                startActivity(goToSettingPage);
            }
        });


    }

    // Called when the activity is about to become visible.
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart Called", Toast.LENGTH_LONG).show();
    }

    // Called when the activity has become visible.
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName","the user didn't add a name yet!");
        TextView userNameText=findViewById(R.id.userNameField);
        userNameText.setText(userName+"’s tasks");
    }

    // Called when another activity is taking focus.
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause Called", Toast.LENGTH_LONG).show();
    }

    // Called when the activity is no longer visible.
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop Called", Toast.LENGTH_LONG).show();
    }


    //It is invoked after the activity has been stopped and prior to its starting stage.
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart Called", Toast.LENGTH_LONG).show();
    }

    // Called just before the activity is destroyed.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy Called", Toast.LENGTH_LONG).show();
    }
}