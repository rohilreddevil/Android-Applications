package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ToDoListManager listManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reference the list view to add some data
        ListView todoList = findViewById(R.id.todo_list);

        //reference the image button to add items
        ImageButton addButton = findViewById(R.id.add_item);

        //reference the image button to remove items
        ImageButton removeButton = findViewById(R.id.remove_item);

        //instantiate the newly made class

        listManager = new ToDoListManager();

        //instantiate an object of type ToDoItemAdapter
        ToDoItemAdapter adapter = new ToDoItemAdapter(this, listManager.getItems());

        //connect the todoList with the adapter to wire it up
        todoList.setAdapter(adapter);

        //wire up the on-click listener for the IMAGE BUTTON
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClick(); //reference the newly made method
            }
        });

    }

    //code for the on-click listener for the IMAGE BUTTON
    private void onAddButtonClick(){ // deals with the functionality of the image button

        //create a dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_item); //sets the title to "Add Item"

        //introduce an Edit-Text to allow a new description
        final EditText input = new EditText(this); // "final" treats it like a CONSTANT
        builder.setView(input);

        //wire up a CANCEL button
        builder.setNegativeButton(
                android.R.string.cancel, //pre defined cancel button
                new DialogInterface.OnClickListener() { //ANONYMOUS ON-CLICK Listener
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }
        );

        //wire up a POSITIVE "OK" button
        builder.setPositiveButton(
                android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //get the text out of the EDIT text so that it can show up on the list
                        //create a NEW ITEM
                        ToDoItem item = new ToDoItem(
                                input.getText().toString(), //pull the DESCRIPTION out of the TEXT FIELD
                                false // ITEM NOT YET COMPLETED
                        );

                        //call the list manager to add the item
                        listManager.addItem(item);
                    }
                }
        );

        //ensure that the DIALOG APPEARS
        builder.show();

    }

    private class ToDoItemAdapter extends ArrayAdapter<ToDoItem>{

        //class level variables
        private Context context;
        public List<ToDoItem> items;

        private ToDoItemAdapter(Context context, List<ToDoItem> items){

            // calling the parent 'Array Adapter' constructor

            super(context, -1, items); // -1 Indicates we won't be using the layout given above

            //set the private variables in the constructor
            this.context = context;
            this.items = items;

        }

        //position indicates the RENDERED ROW
        //convertView - the view object to RENDER for that PARTICULAR ROW
        //View Group - the view that the ROWS ARE ATTACHED TO

        @NonNull
        @Override
        public View getView(final int position, View convertView, @NonNull ViewGroup parent){

            //REFERENCE the newly made XML (to_do_item_layout) file using LayoutInflator
            //obtain the layout inflator service from the context

            //layout inflator service that runs in the background of the O.S
            //**INFLATE - Make the XML code USABLE in JAVA

            if(convertView == null ) { //only if it's null will it RE-INFLATE

                //now inflate the view. convertView will hold a REFERENCE to the XML file
                //FALSE indicates that we want this to be returned AS A VIEW

                convertView = LayoutInflater.from(context).inflate(
                        R.layout.to_do_item_layout,
                        parent,
                        false
                );

            }
            //now pull out the CHECKBOX and the TEXT VIEW
            final TextView itemTextView = convertView.findViewById(R.id.itemTextView);
            CheckBox completedCheckBox = convertView.findViewById(R.id.completedCheckBox);

            //RENDER the row elements one by one. POSITION indicates the ROW
            itemTextView.setText(items.get(position).getDescription()); //obtain the description
            completedCheckBox.setChecked(items.get(position).isCompleted()); // wire up the CHECKBOX and dictate its state

            ImageButton removeButton = findViewById(R.id.remove_item);

            //wire up the on-click listener for the REMOVE BUTTON
            removeButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    boolean checked = items.get(position).isCompleted();

                    if(checked) {
                        remove(items.get(position));
                        removeItem();
                    }
                }

            });


            removeButton.setTag(items.get(position));

            //store a TAG OF DATA by passing in the row
            convertView.setTag(items.get(position));

            completedCheckBox.setTag(items.get(position));

            //make the row clickable using a listener
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) { //this will make the row clickable
                    ToDoItem item = (ToDoItem) v.getTag(); //obtain the tag declared above.
                    item.toggleComplete();
                    // tell the list to update its state as follows
                    notifyDataSetChanged(); // list will re-render anything that needs updating
                }
            };
            convertView.setOnClickListener(onClickListener);

            completedCheckBox.setOnClickListener(onClickListener);

            return convertView; //return the data in the form of a view
        }

    }

    public void removeItem(){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.remove_item);

        builder.setPositiveButton(
                android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                }
        );

        //ensure that the DIALOG APPEARS
        builder.show();


    }


}


