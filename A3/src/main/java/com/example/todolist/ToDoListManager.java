package com.example.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;

public class ToDoListManager {

    private List<ToDoItem> items;


    public ToDoListManager(){
        items = new ArrayList<ToDoItem>(); //array list inherits the list class

        items.add(new ToDoItem("Get Milk", false));
        items.add(new ToDoItem("Walk the dog", true));
        items.add(new ToDoItem("Go to the gym", false));
        items.add(new ToDoItem("Play soccer", true));
    }

    public List<ToDoItem> getItems() {
        return items;
    }

    //ADD-ITEM method
    public void addItem(ToDoItem item){ //takes in ONE PARAMETER - the ITEM TO ADD

        items.add(item);

    }


}


