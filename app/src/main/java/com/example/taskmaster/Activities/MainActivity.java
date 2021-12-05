package com.example.taskmaster.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.cognitoauth.Auth;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

//import com.amplifyframework.datastore.generated.model.AmpTask;

import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.example.taskmaster.Adapters.TaskAdapter;
import com.example.taskmaster.Database.AppDatabase;
import com.example.taskmaster.Database.TaskDao;
//import com.example.taskmaster.Models.Task;
import com.example.taskmaster.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // Called when the activity is first created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            // Add these lines to add the AWSApiPlugin plugins

            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("TaskMaster", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("TaskMaster", "Could not initialize Amplify", error);
        }

        Amplify.Auth.signInWithWebUI(
                this,
                result -> Log.i("AuthQuickStart", result.toString()),
                error -> Log.e("AuthQuickStart", error.toString())
        );

        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );


        /********************/
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String teamId = sharedPreferences.getString("teamId", "");


//////////////**********************///////////////
//        AppDatabase db =AppDatabase.getInstance(getApplicationContext());
//        List<Task> taskList = db.taskDao().getAll();
//        System.out.println(taskList);

        //Get the data from data base and display it in recyclerView
        System.out.println("**********************************" + teamId);

        if (!teamId.equals("")) {
            RecyclerView recyclerView = findViewById(R.id.tasksResyclerView);

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
                        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
                        sharedPreferencesEditor.putInt("Counter", taskList.size());
                        sharedPreferencesEditor.apply();
                        handler.sendEmptyMessage(1);
                    },
                    error -> Log.e("TaskMaster", error.toString(), error)
            );

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new TaskAdapter(taskList));


        }


        //Go to add task page
        Button addTaskButton = (Button) findViewById(R.id.addTask);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent goToAddTaskPage = new Intent(MainActivity.this, AddATask.class);
                startActivity(goToAddTaskPage);
            }
        });


        //Go to all tasks page
        Button allTasksButton = (Button) findViewById(R.id.allTasks);
        allTasksButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent goToAllTasksPage = new Intent(MainActivity.this, AllTasks.class);
                startActivity(goToAllTasksPage);
            }
        });

        //go to setting page
        Button settingButton = findViewById(R.id.settingButton);
        settingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent goToSettingPage = new Intent(MainActivity.this, Settings.class);
                startActivity(goToSettingPage);
            }
        });


        //Sign out button

        Button signOutButton = findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );


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
        String userName = sharedPreferences.getString("userName", "the user didn't add a name yet!");
        TextView userNameText = findViewById(R.id.userNameField);
        userNameText.setText(userName + "’s tasks");


        String chooseTeamName = sharedPreferences.getString("teamName", "Choose a team");
        TextView teamName = findViewById(R.id.teamName);
        teamName.setText(chooseTeamName);


        //get the current user from auth
        AuthUser logedInUser = Amplify.Auth.getCurrentUser();
        TextView userNameAuth = findViewById(R.id.userNameField2);
        if (logedInUser != null) {
            userNameAuth.setText(logedInUser.getUsername());
        } else {
            userNameAuth.setText("Anonymous");
        }

        Toast.makeText(getApplicationContext(), "onResume Called", Toast.LENGTH_LONG).show();
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