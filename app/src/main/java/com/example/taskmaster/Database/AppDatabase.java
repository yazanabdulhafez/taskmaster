package com.example.taskmaster.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.taskmaster.Models.Task;

@Database(entities = {Task.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

    //This is the Singleton
    private static AppDatabase appDatabase; //declare the instance


    //the default constructor
    public AppDatabase() {
    }

    //get instance method
    public static synchronized AppDatabase getInstance(final Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "tasksDatabase")
                    .allowMainThreadQueries().build();
        }
        return appDatabase;
    }

    //destroy instance
    public static void destroyInstance() {
        appDatabase = null;
    }
}