package com.example.taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.services.s3.util.Mimetypes;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.options.StorageDownloadFileOptions;
import com.example.taskmaster.R;

import java.io.File;

public class TaskDetail extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent intent = getIntent();
        TextView taskDetailText=findViewById(R.id.taskDatailText);
        String details="The description of task: "+getIntent().getExtras().get("taskBody").toString()
                + "\n" +" state of task is: " +getIntent().getExtras().get("taskState").toString();
        taskDetailText.setText(details);

        TextView taskDetailTitleLabel = findViewById(R.id.taskDetailTitle);
        String taskTitle = getIntent().getExtras().get("taskTitle").toString();
        taskDetailTitleLabel.setText(taskTitle);


        //******************/
        ImageView storedFile = findViewById(R.id.retrivedFile);
        if (intent.getExtras().getString("taskFileKey") != null) {
            Amplify.Storage.downloadFile(

                    intent.getExtras().getString("taskFileKey"),
                    new File(getApplicationContext().getFilesDir() + "/" + intent.getExtras().getString("taskFileKey") + ".jpg"),
                    StorageDownloadFileOptions.defaultInstance(),

                    progress -> Log.i("TaskMaster", "Fraction completed: " + progress.getFractionCompleted()),

                    result -> {

                        final Mimetypes mimetypes = Mimetypes.getInstance();
                        String fileType = mimetypes.getMimetype(result.getFile());
                        Log.i("TaskMaster", "FileType: " + fileType);

//
                        if (fileType.startsWith("image")) {
                            Bitmap bitmap = BitmapFactory.decodeFile(result.getFile().getPath());
                            storedFile.setImageBitmap(bitmap);
                            Log.i("TaskMaster", "Successfully downloaded: " + result.getFile().getName());
                        }
                    },
                    error -> Log.e("TaskMaster", "Download Failure", error)
            );
        }

        //**************/

    }
}