package com.cfox.spanalysis.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RefTreeDao {

    @Query("select * from ref_tree")
    List<RefTree> getAll();

    @Query("select * from ref_tree where activity_state in (:states)")
    List<RefTree> query(String[] states);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRefTree(RefTree refTree);

    @Delete
    void deleteRefTree(RefTree refTree);


    @Query("delete from ref_tree")
    void deleteAll();

}
