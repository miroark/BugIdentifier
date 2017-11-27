package com.example.ryan.bugidentifier;

//Singleton used to pass complex objects between activities.

public class PassingTable {
    public static PassingTable instance = null;
    public Item passingItem;

    protected PassingTable() {}

    public static PassingTable getInstance(){
        if (instance == null){
            instance = new PassingTable();
        }
        return instance;
    }
}
