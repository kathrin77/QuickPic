package com.example.quickpic;

import java.util.ArrayList;

/**
 * Created by kathr on 05.02.2018.
 */

public class Quickpic {

    int points;
    int round;
    int level;
    int lives;
    String question;
    String picture_id;

    //ArrayList<Topic> mountainTopics;
    ArrayList<Object> mountainTopics;
    ArrayList<Topic> fishQuestions;
    ArrayList<Topic> treeQuestions;

    public Quickpic() {
        mountainTopics = new ArrayList<> ();
        String[] mnt1 = {"img_everest","Mount Everest","8848 m","Nepal/China"};
        String[] mnt2 = {"img_saentis","Säntis","2505 m","Switzerland"};
        String[] mnt3 = {"img_k2","K2","8611 m","Pakistan"};
        String[] mnt4 = {"img_zuckerhut","Zuckerhut","396 m","Brasil"};

        mountainTopics.add(mnt1);
        mountainTopics.add(mnt2);
        mountainTopics.add(mnt3);
        mountainTopics.add(mnt4);

    }

    public String getMountainQuestions(int level) {
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
