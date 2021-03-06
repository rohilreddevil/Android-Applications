package com.example.todolist;

import android.util.SparseBooleanArray;

public class ToDoItem {
    private String description;
    private boolean isComplete;
    private long id;
    private String timestamp;

    public ToDoItem(String description, boolean isComplete, String timestamp){ //initialize the values in the constructor
        this(description, isComplete, timestamp, -1);
    }

    public ToDoItem(String description, boolean isComplete, String timestamp, long id){
        this.description = description;
        this.isComplete = isComplete;
        this.timestamp = timestamp;
        this.id = id;
    }

     public boolean isCompleted(){
        return isComplete;
     }

    public String getDescription(){ //return the description
        return description;
    }

    //set the state of completed
    public void toggleComplete(){
        isComplete = !isComplete; // flip the value. When the checkbox is unchecked, FLIP its value from true to false
    }

    public long getId(){
        return id;
    }

    public String getTimestamp(){
        return timestamp;
    }

    @Override
    public String toString(){
        return getDescription(); //returns the String format of the description as expected
    }

}
