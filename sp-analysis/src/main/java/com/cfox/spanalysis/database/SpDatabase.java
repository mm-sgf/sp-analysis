package com.cfox.spanalysis.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RefTree.class}, version = 1)
public abstract class SpDatabase extends RoomDatabase {
    public abstract RefTreeDao getRefTreeDao();
}
