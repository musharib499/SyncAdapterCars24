package com.example.user.syncadaptercars24.model;

/**
 * Created by Mushareb Ali on 2/17/2017.
 * mushareba.ali@cars24.com
 */

public class User {
    int _id=0;
    String _name=null;
    String detials=null;


    // Empty constructor
    public User(){

    }
    // constructor
    public User(int id, String name, String detials){
        this._id = id;
        this._name = name;
        this.detials = detials;
    }

    // constructor
    public User(String name, String detials){
        this._name = name;
        this.detials = detials;
    }


    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getDetials() {
        return detials;
    }

    public void setDetials(String detials) {
        this.detials = detials;
    }


}
