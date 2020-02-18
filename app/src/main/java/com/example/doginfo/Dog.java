package com.example.doginfo;

public class Dog {

    //private variables
    int Id;
    String Breed;
    String Dogsize;
    String Age;


    // Empty constructor
    public Dog(){

    }
    // constructor
    public Dog(int id, String breed, String dogsize, String age){
        this.Id = id;
        this.Breed = breed;
        this.Dogsize = dogsize;
        this.Age = age;
    }

    // constructor
    public Dog(String breed, String dogsize, String age){

        this.Breed = breed;
        this.Dogsize = dogsize;
        this.Age = age;
    }

    // getting ID
    public int getID(){
        return this.Id;
    }

    // setting id
    public void setID(int id){
        this.Id = id;
    }

    // getting dog breed
    public String getBreed(){
        return this.Breed;
    }

    // setting dog breed
    public void setBreed(String breed){
        this.Breed = breed;
    }

    // getting dog size
    public String getDogSize(){
        return this.Dogsize;
    }

    // setting dog size
    public void setDogSize(String dogsize){
        this.Dogsize = dogsize;
    }

    // getting dog age
    public String getAge(){
        return this.Age;
    }

    // setting dog age
    public void setAge(String age){
        this.Age = age;
    }

}
