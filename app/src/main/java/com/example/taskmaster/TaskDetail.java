package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        TextView taskDetailText=findViewById(R.id.taskDatailText);
        taskDetailText.setText("Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Mauris maximus " +
                "vestibulum sagittis. Nam pretium erat dui," +
                " quis convallis nisl eleifend aliquam. " +
                "Nulla quis quam id ante imperdiet lacinia. ");

        TextView taskDetailTitleLabel = findViewById(R.id.taskDetailTitle);
        String taskTitle = getIntent().getExtras().get("taskTitle").toString();
        taskDetailTitleLabel.setText(taskTitle);

    }
}