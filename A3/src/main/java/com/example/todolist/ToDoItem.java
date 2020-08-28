package com.example.todolist;

public class ToDoItem {
    private String description;
    private boolean isComplete;

    public ToDoItem(String description, boolean isComplete){ //initialize the values in the constructor
        this.description = description;
        this.isComplete = isComplete;

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

    @Override
    public String toString(){
        return getDescription(); //returns the String format of the description as expected
    }

}
