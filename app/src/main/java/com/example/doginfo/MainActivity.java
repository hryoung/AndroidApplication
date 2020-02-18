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
    private String dogBreed,dogSize,dogAge;
    private  ListView listView;
    private dataAdapter data;
    private Dog dataModel;
    private int rowNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db=new DatabaseHandler(this);

        listView = (ListView) findViewById(R.id.list1);

        Breed=(EditText) findViewById(R.id.text1);
        dogsize=(EditText) findViewById(R.id.text2);
        age=(EditText) findViewById(R.id.text3);
    }

    public void buttonClicked(View v){
        int id=v.getId();

        switch(id){

            case R.id.save:
                addDog();

                break;

            case R.id.display:

                ShowDogRecords();
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
        dogBreed = Breed.getText().toString();
        dogSize = dogsize.getText().toString();
        dogAge = age.getText().toString();
    }

    //Insert data to the database
    private void addDog(){
        getValues();
        db.addDogs(new Dog(dogBreed, dogSize, dogAge));
        Toast.makeText(getApplicationContext(),"Saved successfully", Toast.LENGTH_LONG).show();
        closeKeyboard();
    }

    //Retrieve data from the database and set to the list view
    private void ShowDogRecords(){

        final ArrayList<Dog> Dogs = new ArrayList<>(db.getAllDogs());
        data=new dataAdapter(this, Dogs);
        closeKeyboard();

        listView.setAdapter(data);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        sucessful = db.updateDog(new Dog(dogBreed, dogSize, dogAge), rowNumber);
        if(sucessful == 0){
            Toast.makeText(getApplicationContext(),"Updated successfully", Toast.LENGTH_LONG).show();
        }

    }
    // Search dogs in the database
    private void SearchDogs(){
        getValues();
        final ArrayList<Dog> dogs = new ArrayList<>(db.searchData(new Dog(dogBreed, dogSize, dogAge)));
        Toast.makeText(getApplicationContext(),"Searching Data", Toast.LENGTH_LONG).show();
        closeKeyboard();
        if(dogs.isEmpty()){
            Toast.makeText(getApplicationContext(),"Database is empty", Toast.LENGTH_LONG).show();
        }
        data=new dataAdapter(this, dogs);

        listView.setAdapter(data);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
