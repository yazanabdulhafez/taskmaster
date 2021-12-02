package com.example.taskmaster.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;

import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.example.taskmaster.Database.AppDatabase;
//import com.example.taskmaster.Models.Task;

import com.example.taskmaster.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class AddATask extends AppCompatActivity {
    private int taskCounter = 0;
    private Intent chooseFile;
    private String fileName;
    private Uri fileData;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_atask);



        Button addNewTaskButton = findViewById(R.id.addTask);
        EditText title = findViewById(R.id.taskTitleFeild);
        EditText body = findViewById(R.id.taskBodyFeild);
        EditText state = findViewById(R.id.taskStateFeild);
        TextView taskCounter=findViewById(R.id.taskCounter);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AddATask.this);
        int counter= sharedPreferences.getInt("Counter", 0);

        taskCounter.setText("Total Tasks: "+ counter);
        //****************************/

        Map<String, String> teamList = new HashMap<>();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Team.class),
                response -> {

                    Log.i("response", response.toString());
                    for (Team oneTeam : response.getData()) {
                        teamList.put(oneTeam.getName(), oneTeam.getId());

                    }
                },
                error -> Log.e("TaskMaster", error.toString(), error)
        );

        Log.i("teamlist", teamList.toString());

        //****************************/
        Button addFile = findViewById(R.id.uploadButton);
        addFile.setOnClickListener(v -> {

            chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.setType("*/*");
            chooseFile = Intent.createChooser(chooseFile, "Choose a file");
            startActivityForResult(chooseFile, 1234);

        });
        //***************************/

        addNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //              Task newTask = new Task(title.getText().toString(), body.getText().toString(), state.getText().toString());
//                AppDatabase db =AppDatabase.getInstance(getApplicationContext());
//                db.taskDao().insertAll(newTask);
                //this model is from Amplify it used to store the data

              //*****************/
                if (fileData != null) {
                    try {
                        InputStream exampleInputStream = getContentResolver().openInputStream(fileData);
                        Amplify.Storage.uploadInputStream(
                                fileName,
                                exampleInputStream,
                                result -> Log.i("TaskMaster", "Successfully uploaded: " + result.getKey()),
                                storageFailure -> Log.e("TaskMaster", "Upload failed", storageFailure)
                        );
                    } catch (FileNotFoundException error) {
                        Log.e("TaskMaster", "Could not find file to open for input stream.", error);
                    }
                }
                //**************/
                RadioGroup radioGroup = findViewById(R.id.teamRadioGroup);
                int chosenButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton chosenButton = findViewById(chosenButtonId);
                String chosenTeam = chosenButton.getText().toString();


                Amplify.API.query(
                        ModelQuery.get(Team.class, teamList.get(chosenTeam)),
                        response1 -> {
                            Log.i("response1", response1.getData().toString());

                            Task task = Task.builder()
                                    .title(title.getText().toString())
                                    .body(body.getText().toString())
                                    .state(state.getText().toString())
                                    .filekey(fileName)
                                    .teamId(response1.getData().getId())
                                    .build();

                            Amplify.API.mutate(
                                    ModelMutation.create(task),
                                    response3 -> Log.i("TaskMaster", "Added Task with id: " + response3.getData().getId()),
                                    error -> Log.e("TaskMaster", "Create failed", error));
                        }, error -> Log.e("TaskMaster", error.toString(), error)
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = new File(data.getData().getPath());
        fileName = file.getName();
        fileData = data.getData();
    }
}