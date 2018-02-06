package com.example.quickpic;

/**
 * Created by kathr on 05.02.2018.
 */

public class Mountain extends Topic {

    private String height;

    private String country;

    //ID für Typ Mountains:
    private static final char ID = 'm';

    //Konstruktor
    public Mountain(String height, String country, String name, String photo_id) {
        super(name, photo_id);
        this.height = height;
        this.country = country;
    }

    /**
     * generates the question to be shown for the according level
     * @param level
     * @return question (String)
     */

    public String getQuestions(int level) {
        //hier wird die Frage generiert

        String question;

        switch (level) {
            case 1: question = "What is the name of this mountain?"; break;
            case 2: question = "How high is this mountain?"; break;
            case 3: question = "In which country lies this mountain?"; break;
            default: question = "";
        }

        return question;

    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public static char getID() {
        return ID;
    }
}
