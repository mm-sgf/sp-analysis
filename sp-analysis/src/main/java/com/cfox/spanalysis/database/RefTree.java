package com.cfox.spanalysis.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "ref_tree", indices = {@Index(value = {"ref_key"}, unique = true)})
public class RefTree {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "ref_key")
    private int refKey;

    @ColumnInfo(name = "class_name")
    private String className;

    @ColumnInfo(name = "activity_state")
    private String activityState;

    @ColumnInfo(name = "ref_tree")
    private String refTree;

    public RefTree(int refKey, String className, String activityState, String refTree) {
        this.refKey = refKey;
        this.className = className;
        this.activityState = activityState;
        this.refTree = refTree;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRefKey() {
        return refKey;
    }

    public void setRefKey(int refKey) {
        this.refKey = refKey;
    }

    public String getClassName() {
        return className;
    }

    public String getActivityState() {
        return activityState;
    }

    public void setActivityState(String activityState) {
        this.activityState = activityState;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRefTree() {
        return refTree;
    }

    public void setRefTree(String refTree) {
        this.refTree = refTree;
    }

    @Override
    public String toString() {
        return "RefTree{" +
                "id=" + id +
                ", refKey='" + refKey + '\'' +
                ", className='" + className + '\'' +
                ", refTree='" + refTree + '\'' +
                '}';
    }
}
