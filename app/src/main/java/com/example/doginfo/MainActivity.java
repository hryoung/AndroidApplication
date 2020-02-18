package com.example.doginfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;


import java.util.ArrayList;

public class MainActivity extends Activity {

    private EditText Breed,dogsize,age;
    private DatabaseHandler db;
    private String d_breed,d_size,d_age;
    private  ListView lv;
    private dataAdapter data;
    private Dog dataModel;
    private int rowNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiate database handler
        db=new DatabaseHandler(this);

        lv = (ListView) findViewById(R.id.list1);

        Breed=(EditText) findViewById(R.id.txt1);
        dogsize=(EditText) findViewById(R.id.txt2);
        age=(EditText) findViewById(R.id.txt3);
    }

    public void buttonClicked(View v){
        int id=v.getId();
        System.out.println("get id value for button clicked: " + id);

        switch(id){

            case R.id.save:
                addDog();

                break;

            case R.id.display:

                ShowRecords();
                break;

            case R.id.delete:

                DeleteDog();
                break;

            case R.id.search:

                SearchDogs();
                break;
        }
    }

    // function to get values from the Edit text
    private void getValues(){
        d_breed = Breed.getText().toString();
        d_size = dogsize.getText().toString();
        d_age = age.getText().toString();
    }

    //Insert data to the database
    private void addDog(){
        getValues();
        db.addDogs(new Dog(d_breed, d_size, d_age));
        Toast.makeText(getApplicationContext(),"Saved successfully", Toast.LENGTH_LONG).show();
        closeKeyboard();
    }

    //Retrieve data from the database and set to the list view
    private void ShowRecords(){

        final ArrayList<Dog> Dogs = new ArrayList<>(db.getAllDogs());
        data=new dataAdapter(this, Dogs);
        closeKeyboard();

        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataModel = Dogs.get(position);
                rowNumber = dataModel.getID();
                Toast.makeText(getApplicationContext(),String.valueOf(dataModel.getID()), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // Delete dog from the database and update database
    private void DeleteDog(){
        int sucessful = 0;
        db.deleteData(rowNumber);
        sucessful = db.updateDog(new Dog(d_breed, d_size, d_age), rowNumber);
        System.out.println("returned value: " + sucessful);

    }
    // Search dogs in the database
    private void SearchDogs(){
        getValues();
        final ArrayList<Dog> dogs = new ArrayList<>(db.searchData(new Dog(d_breed, d_size, d_age)));
        Toast.makeText(getApplicationContext(),"Searching Data", Toast.LENGTH_LONG).show();
        closeKeyboard();
        data=new dataAdapter(this, dogs);

        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = dogs.get(position);
                rowNumber = dataModel.getID();
                Toast.makeText(getApplicationContext(),String.valueOf(dataModel.getID()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // close the keyboard after data has been inputted
    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
