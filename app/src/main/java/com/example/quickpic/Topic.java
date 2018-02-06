package com.example.quickpic;

/**
 * Created by kathr on 05.02.2018.
 */

public abstract class Topic {
    //Attribute:

    private String photo_id;
    private String name;


    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }


    public void setName(String name) {
        this.name = name;
    }


    //Konstruktor:
    public Topic(String name, String photo_id) {
        this.photo_id = photo_id;
        this.name = name;

    }

    public abstract String getQuestions(int level); //wird überschrieben

    //getter, falls benötigt:
    public String getName() {
        return name;
    }

    public String getPhoto_id() {
        return photo_id;
    }
}
