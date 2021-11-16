package com.example.taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskmaster.Adapters.TaskAdapter;
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

        List<Task> taskList=new ArrayList<Task>();
        taskList.add(new Task("lab26","this is lab26","complete"));
        taskList.add(new Task("lab27","this is lab27","assigned"));
        taskList.add(new Task("lab28","this is lab28","in progress"));

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

//        //define what the three buttons do
//        Button lab26Button = findViewById(R.id.lab26Button);
//        Button lab27Button = findViewById(R.id.lab27Button);
//        Button lab28Button = findViewById(R.id.lab28Button);
//
//        lab26Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String taskTitle=lab26Button.getText().toString();
//                Intent goTolab26Task=new Intent(MainActivity.this, TaskDetail.class);
//                goTolab26Task.putExtra("taskTitle",taskTitle);
//                startActivity(goTolab26Task);
//
//            }
//        });
//
//        lab27Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String taskTitle=lab27Button.getText().toString();
//                Intent goTolab27Task=new Intent(MainActivity.this,TaskDetail.class);
//                goTolab27Task.putExtra("taskTitle",taskTitle);
//                startActivity(goTolab27Task);
//
//            }
//        });
//
//        lab28Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String taskTitle=lab28Button.getText().toString();
//                Intent goTolab28Task=new Intent(MainActivity.this,TaskDetail.class);
//                goTolab28Task.putExtra("taskTitle",taskTitle);
//                startActivity(goTolab28Task);
//
//            }
//        });
//


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