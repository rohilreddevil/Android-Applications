package com.example.todolist;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.todolist.domain.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ToDoListManager {


    //get a reference to the database helper class so that the database can be used in this class
    private DatabaseHelper dbHelper;


    public ToDoListManager(Context context){

        //instantiate the database helper
        dbHelper = DatabaseHelper.getInstance(context);

    }

    public List<ToDoItem> getItems() {

        //get a copy of the database -READABLE COPY
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //CURSOR - used for get items from the database - pull out each row
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + DatabaseHelper.TABLE_NAME,
                null
        );

        List<ToDoItem> items = new ArrayList<>();

        //move to the first row of data
        if(cursor.moveToFirst()){
            //while the cursor still moves forward
            while(!cursor.isAfterLast()){
                ToDoItem item = new ToDoItem(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.DESCRIPTION)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COMPLETED)) != 0,
                        cursor.getLong(cursor.getColumnIndex(DatabaseHelper.ID))
                );
                items.add(item);
                //move to the next item
                cursor.moveToNext();
            }
        }
        //close the cursor
        cursor.close();

        return items;
    }

    //ADD-ITEM method
    public void addItem(ToDoItem item){ //takes in ONE PARAMETER - the ITEM TO ADD


        //wrap the data to be put inside the database in "CONTENT VALUES"
        //the following code will help us insert an item into the database

        ContentValues newItem = new ContentValues();
        newItem.put(DatabaseHelper.DESCRIPTION, item.getDescription());
        newItem.put(DatabaseHelper.COMPLETED, item.isCompleted());

        //get a copy of the database
        //get access to the database for writing

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //add the new item using "insert"
        db.insert(DatabaseHelper.TABLE_NAME, null, newItem);

    }

    //UPDATE ITEM
    public void updateItem(ToDoItem item){

        //UPDATE items
        //SET Description = "", Completed = ""
        //WHERE _id = ""

        ContentValues updateItem = new ContentValues();
        updateItem.put(DatabaseHelper.DESCRIPTION, item.getDescription());
        updateItem.put(DatabaseHelper.COMPLETED, item.isCompleted());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //populate the WHERE clause
        String [] args = new String[] {
                String.valueOf(item.getId())
        };

        //this will create the UPDATE query
        db.update(
                DatabaseHelper.TABLE_NAME,
                updateItem, // the SET clause
                DatabaseHelper.ID + "=?",
                args //where ID equals the String version of the ID - established in the array
        );

    }

    //delete item to follow
    public void deleteItem(ToDoItem item){
        ContentValues removeItem = new ContentValues();
        removeItem.remove(DatabaseHelper.DESCRIPTION);
        removeItem.remove(DatabaseHelper.COMPLETED);
        removeItem.remove(DatabaseHelper.ID);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] args = new String[]{String.valueOf(item.getId())};

        db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.ID + "=?", args);
    }


}


