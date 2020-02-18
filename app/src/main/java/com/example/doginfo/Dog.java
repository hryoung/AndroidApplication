package com.example.doginfo;

public class Dog {

    //private variables
    int _id;
    String _breed;
    String _dogsize;
    String _age;


    // Empty constructor
    public Dog(){

    }
    // constructor
    public Dog(int id, String breed, String dogsize, String age){
        this._id = id;
        this._breed = breed;
        this._dogsize = dogsize;
        this._age = age;
    }

    // constructor
    public Dog(String breed, String dogsize, String age){

        this._breed = breed;
        this._dogsize = dogsize;
        this._age = age;
    }

    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting dog breed
    public String getBreed(){
        return this._breed;
    }

    // setting dog breed
    public void setBreed(String breed){
        this._breed = breed;
    }

    // getting dog size
    public String getDogSize(){
        return this._dogsize;
    }

    // setting dog size
    public void setDogSize(String dogsize){
        this._dogsize = dogsize;
    }

    // getting dog age
    public String getAge(){
        return this._age;
    }

    // setting dog age
    public void setAge(String age){
        this._age = age;
    }

}
