package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);




        Button saveUserNameButton = findViewById(R.id.userNameSaveButton);
        saveUserNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userNameField = findViewById(R.id.userNameInputFeild);
                String userName = userNameField.getText().toString();

                // add value to the shared preferences
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);

                SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
                sharedPreferencesEditor.putString("userName", userName);
                sharedPreferencesEditor.apply();
                Toast.makeText(getApplicationContext(), "submmited!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}