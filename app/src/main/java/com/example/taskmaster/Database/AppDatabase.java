package com.example.taskmaster.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.taskmaster.Models.Task;

@Database(entities = {Task.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}