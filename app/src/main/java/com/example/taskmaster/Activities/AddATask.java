package com.example.taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
//import com.example.taskmaster.Database.AppDatabase;
import com.amplifyframework.datastore.generated.model.Task;
import com.example.taskmaster.R;

public class AddATask extends AppCompatActivity {
private int taskCounter=0;

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
//                Task newTask = new Task(title.getText().toString(), body.getText().toString(), state.getText().toString());
//                AppDatabase db =AppDatabase.getInstance(getApplicationContext());
//                db.taskDao().insertAll(newTask);

                //this model is from Amplify it used to store the data
                Task task = Task.builder()
                        .title(title.getText().toString())
                        .body(body.getText().toString())
                        .state(state.getText().toString())
                        .build();

                Amplify.API.mutate(
                        ModelMutation.create(task),
                        response -> Log.i("TaskMaster", "Added Task with id: " + response.getData().getId()),
                        error -> Log.e("TaskMaster", "Create failed", error)
                );

                Toast.makeText(getApplicationContext(), "submitted!", Toast.LENGTH_SHORT).show();
                Intent goToHome = new Intent(AddATask.this, MainActivity.class);
                startActivity(goToHome);
            }
        });

    }

    public int getTaskCounter() {
        return taskCounter;
    }

    public void setTaskCounter(int taskCounter) {
        this.taskCounter = taskCounter;
    }
}