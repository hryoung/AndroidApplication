package com.example.doginfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heidi Young on 2/16/2020.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DogsManager";

    // Dogs table name
    private static final String TABLE_DOGS = "Dogs";

    // Dogs Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_BREED = "breed";
    private static final String KEY_DOGSIZE = "dogsize";
    private static final String KEY_AGE = "age";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_DogS="CREATE TABLE " + TABLE_DOGS + "("
                + KEY_ID +" INTEGER PRIMARY KEY,"
                + KEY_BREED +" TEXT,"
                + KEY_DOGSIZE +" TEXT,"
                + KEY_AGE  +" TEXT" + ")";
        db.execSQL(CREATE_TABLE_DogS);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOGS);

        // Create tables again
        onCreate(db);
    }

    /**
     * Insert dog info into database
     */
    public void addDogs(Dog Dog){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values=new ContentValues();

        values.put(KEY_BREED, Dog.getBreed());
        values.put(KEY_DOGSIZE, Dog.getDogSize() );
        values.put(KEY_AGE, Dog.getAge());

        db.insert(TABLE_DOGS, null, values);
        db.close();
    }


    /**
     *Getting All Dogs
     **/

    public List<Dog> getAllDogs() {
        List<Dog> DogList = new ArrayList<Dog>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DOGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Dog Dog = new Dog();
                Dog.setID(Integer.parseInt(cursor.getString(0)));
                Dog.setBreed(cursor.getString(1));
                Dog.setDogSize(cursor.getString(2));
                Dog.setAge(cursor.getString(3));

                // Adding Dog to list
                DogList.add(Dog);
            } while (cursor.moveToNext());
        }

        // return Dog list
        return DogList;
    }



    /**
     *Updating single Dog
     **/

    public int updateDog(Dog Dog, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BREED, Dog.getBreed());
        values.put(KEY_DOGSIZE, Dog.getDogSize());
        values.put(KEY_AGE, Dog.getAge());

        // updating row
        return db.update(TABLE_DOGS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    /**
     *Deleting single Dog
     **/

    public void deleteData(int Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DOGS, KEY_ID + " = ?",
                new String[] { String.valueOf(Id) });
        db.close();
    }

    /**
     * Search for data in the database
     */
    public List<Dog> searchData(Dog Dog){
        List<Dog> DogList = new ArrayList<Dog>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DOGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Dog Dogs = new Dog();
                Dogs.setID(Integer.parseInt(cursor.getString(0)));
                Dogs.setBreed(cursor.getString(1));
                Dogs.setDogSize(cursor.getString(2));
                Dogs.setAge(cursor.getString(3));

                // searching dogs based on age, breed or size
                    // search for dogs based on breed
                if(Dog.getAge().matches("") && Dog.getDogSize().matches("")){
                    if(Dog.getBreed().equals(Dogs.getBreed())) {
                        DogList.add(Dogs);
                    }
                }
                // search for dogs based on age
                else if(Dog.getBreed().matches("") && Dog.getDogSize().matches("")){
                    if(Dog.getAge().equals(Dogs.getAge())) {
                        DogList.add(Dogs);
                    }
                }
                // search for dogs based on size
                else if(Dog.getBreed().matches("") && Dog.getAge().matches("")){
                    if(Dog.getDogSize().equals(Dogs.getDogSize())) {
                        DogList.add(Dogs);
                    }
                }
                // search for dogs based size and age
                else if(Dog.getBreed().matches("")){
                    if(Dog.getDogSize().equals(Dogs.getDogSize()) && Dog.getAge().equals(Dogs.getAge())) {
                        DogList.add(Dogs);
                    }
                }
                // search for dogs based on size and breed
                else if(Dog.getAge().matches("")){
                    if(Dog.getDogSize().equals(Dogs.getDogSize()) && Dog.getBreed().equals(Dogs.getBreed())) {
                        DogList.add(Dogs);
                    }
                }
                // search for dogs based on breed and age
                else if(Dog.getDogSize().matches("")){
                    if(Dog.getBreed().equals(Dogs.getBreed()) && Dog.getAge().equals(Dogs.getAge())) {
                        DogList.add(Dogs);
                    }
                }
                // search for dogs based on breed, age, and size
                else{
                    if(Dog.getBreed().equals(Dogs.getBreed()) && Dog.getAge().equals(Dogs.getAge()) && Dog.getDogSize().equals(Dogs.getDogSize())) {
                        DogList.add(Dogs);
                    }
                }


            } while (cursor.moveToNext());
        }

        // return Dog list
        return DogList;

    }

}
