package com.example.taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.example.taskmaster.R;

import java.util.HashMap;
import java.util.Map;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //***************************
        //get the team list from dynamo database

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Log.i("MasterTask", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MasterTask", "Could not initialize Amplify", error);
        }

        Map< String,String> teamList = new HashMap<>();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Team.class),
                response -> {
                    for (Team team : response.getData()) {

                        teamList.put(team.getName(),team.getId());

                    }
                },
                error -> Log.e("MasterTask", error.toString(), error)
        );


        //**************************



        Button saveUserNameButton = findViewById(R.id.userNameSaveButton);
        saveUserNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userNameField = findViewById(R.id.userNameInputFeild);
                String userName = userNameField.getText().toString();

                // add value to the shared preferences and send the user name to the main page
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);

                SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
                sharedPreferencesEditor.putString("userName", userName);
                sharedPreferencesEditor.apply();
                Toast.makeText(getApplicationContext(), "submmited!", Toast.LENGTH_SHORT).show();


                //get the team name chosen from the radio buttons and send it to the main page
                RadioGroup radioGroup = findViewById(R.id.radioGroupSetting);
                int chosenButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton chosenButton = findViewById(chosenButtonId);


                String teamName = chosenButton.getText().toString();
                sharedPreferencesEditor.putString("teamName", teamName);
                sharedPreferencesEditor.putString("teamId", teamList.get(teamName));
                sharedPreferencesEditor.apply();
                Intent goHomePage = new Intent(Settings.this, MainActivity.class);
                startActivity(goHomePage);
            }
        });

    }
}