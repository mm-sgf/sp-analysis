package com.cfox.spanalysis.database;

import android.content.Context;

import androidx.room.Room;

public class DBManager {

    private static SpDatabase sSpDatabase;


    public static void initDBRoom(Context context) {
        sSpDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                SpDatabase.class, "db-sp-tree-ref")
                .allowMainThreadQueries().build();
    }


    public static SpDatabase getDatabase() {


        return sSpDatabase;
    }




}
