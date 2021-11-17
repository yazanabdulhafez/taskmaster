package com.example.taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taskmaster.R;

public class TaskDetail extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        TextView taskDetailText=findViewById(R.id.taskDatailText);
        String details="The description of task: "+getIntent().getExtras().get("taskBody").toString()
                + "\n" +" state of task is: " +getIntent().getExtras().get("taskState").toString();
        taskDetailText.setText(details);

        TextView taskDetailTitleLabel = findViewById(R.id.taskDetailTitle);
        String taskTitle = getIntent().getExtras().get("taskTitle").toString();
        taskDetailTitleLabel.setText(taskTitle);

    }
}