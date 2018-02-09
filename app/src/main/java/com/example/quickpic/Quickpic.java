package com.example.quickpic;

import java.util.ArrayList;

/**
 * Created by kathr on 05.02.2018.
 */

public class Quickpic {

    int points = 0;
    int round = 1;
    int level = 1;
    int lives = 3;
    String question;
    int id;
    String img_resource;
    String answer;
    String name;
    String height;
    String location;
//provisorisch: img_ids nur für 3 Bilder
    String[][] mountains = {
            {"img_amadablam","Ama Dablam","6812 m","Nepal"},
            {"img_dreizinnen","Drei Zinnen","2999 m","Italy"},
            {"img_everest","Mount Everest","8848 m","Nepal/China"},
            {"img_fitzroy","Fitz Roy","3395 m","Argentina"},
            {"img_fuji","Fuji","3776 m","Japan"},
            {"img_halfdome","Half Dome","2694 m","U.S.A"},
            {"img_huashan","Hua Shan","2155 m","China"},
            {"img_kilimanjaro","Kilimanjaro","5895 m","Tansania"},
            {"img_matterhorn","Matterhorn","4478 m","Switzerland"},
            {"img_popocatepetl","Popocatepetl","5426 m","Mexico"},
            {"img_tablemountain","Table Mountain","1085 m","South Africa"},
            {"img_uluru","Uluru","863 m","Australia"},
            {"img_paodeacucar", "Pão de Açúcar", "396 m", "Brasil"}, //Zuckerhut
            {"img_zugspitze","Zugspitze","2962 m","Germany"}};

    String[][] fish; //TODO

    String[][] trees; //TODO

    public Quickpic() {

    }

    public String getMountainQuestions(int level) {
        //hier wird die Frage generiert

        switch (level) {
            case 1: question = "What is the name of this mountain?"; break;
            case 2: question = "How high is this mountain?"; break;
            case 3: question = "In which country lies this mountain?"; break;
            default: question = "";
        }

        return question;

    }

    public String getFishQuestions(int level) {
        //TODO
        return question;
    }

    public String getTreeQuestions(int level) {
        //TODO
        return question;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }




}
