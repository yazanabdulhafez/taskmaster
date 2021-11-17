package com.example.taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskmaster.Database.AppDatabase;
import com.example.taskmaster.Models.Task;
import com.example.taskmaster.R;

public class AddATask extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_atask);

        Button addNewTaskButton = findViewById(R.id.addTask);
        EditText title = findViewById(R.id.taskTitleFeild);
        EditText body = findViewById(R.id.taskBodyFeild);
        EditText state = findViewById(R.id.taskStateFeild);

        addNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task newTask = new Task(title.getText().toString(), body.getText().toString(), state.getText().toString());
                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasksDatabase").allowMainThreadQueries().build();
                db.taskDao().insertAll(newTask);
                Toast.makeText(getApplicationContext(), "submitted!", Toast.LENGTH_SHORT).show();
                Intent goToHome = new Intent(AddATask.this, MainActivity.class);
                startActivity(goToHome);
            }
        });

    }

}