package com.example.quickpic;

import android.content.Context;

import java.util.Locale;

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
    char topic;
    String[][] answer;



    //Mountains
    String[][] m = {
            {"img_amadablam","Ama Dablam","6812 m","Nepal"},
            {"img_dreizinnen","Drei Zinnen","2999 m","Italy"},
            {"img_everest","Mount Everest","8848 m","Nepal/China"},
            {"img_fitzroy","Fitz Roy","3395 m","Argentina"},
            {"img_fuji","Fuji","3776 m","Japan"},
            {"img_halfdome","Half Dome","2694 m","U.S.A"},
            {"img_huashan","Hua Shan","2155 m","China"},
            {"img_illimani", "Illimani", "6438 m", "Bolivia"},
            {"img_kilimanjaro","Kilimanjaro","5895 m","Tansania"},
            {"img_matterhorn","Matterhorn","4478 m","Switzerland"},
            {"img_paodeacucar", "Pão de Açúcar", "396 m", "Brasil"}, //dt. Zuckerhut
            {"img_popocatepetl","Popocatepetl","5426 m","Mexico"},
            {"img_tablemountain","Table Mountain","1085 m","South Africa"}, //dt. Tafelberg
            {"img_uluru","Uluru","863 m","Australia"},
            {"img_zugspitze","Zugspitze","2962 m","Germany"}};

    //Berge
    String[][] m_de = {
            {"img_amadablam","Ama Dablam","6812 m","Nepal"},
            {"img_dreizinnen","Drei Zinnen","2999 m","Italien"},
            {"img_everest","Mount Everest","8848 m","Nepal/China"},
            {"img_fitzroy","Fitz Roy","3395 m","Argentinien"},
            {"img_fuji","Fuji","3776 m","Japan"},
            {"img_halfdome","Half Dome","2694 m","USA"},
            {"img_huashan","Hua Shan","2155 m","China"},
            {"img_illimani", "Illimani", "6438 m", "Bolivien"},
            {"img_kilimanjaro","Kilimanjaro","5895 m","Tansania"},
            {"img_matterhorn","Matterhorn","4478 m","Schweiz"},
            {"img_paodeacucar", "Zuckerhut", "396 m", "Brasilien"},
            {"img_popocatepetl","Popocatepetl","5426 m","Mexiko"},
            {"img_tablemountain","Tafelberg","1085 m","Südafrika"},
            {"img_uluru","Uluru","863 m","Australien"},
            {"img_zugspitze","Zugspitze","2962 m","Deutschland"}};

    //Marine animals:
    String[][] f = {
            {"img_beluga",      "Beluga Whale", "4,5 m",    "1.400 kg"},
            {"img_blowfish",    "Blowfish",     "35 cm",    "13,5 kg"},
            {"img_bullshark",   "Bull Shark",   "1,8 m",    "130 kg"},
            {"img_clownfish",   "Clownfish",    "8 cm",     "250 g"},
            {"img_eaglerays",   "Eagle Ray",    "9,1 m",    "1.600 kg"},
            {"img_finnwhale",   "Finn Whale",   "27 m",     "up to 80 tons"},
            {"img_goldfish",    "Goldfish",     "30 cm",    "up to 3 kg"},
            {"img_hammerhead",  "Hammerhead",   "6,1 m",    "450 kg"},
            {"img_humpbackwhale","Humpback Whale","16 m",   "30.000 kg"},
            {"img_orca",        "Orca Whale",   "8 m",      "5.400 kg"},
            {"img_pilotwhale",  "Pilot Whale",  "5,5 m",    "3.000 kg"},
            {"img_spermwhale",  "Sperm Whale",  "12 m",     "57.000 kg"},
            {"img_tigershark",  "Tiger Shark",  "5 m",      "650 kg"},
            {"img_whaleshark",  "Whale Shark",  "10 m",     "19.000 kg"},
            {"img_whiteshark",  "White Shark",  "6,4 m",    "1.100 kg"}
    };

    //Meerestiere
    String[][] f_de = {
            {"img_beluga",      "Beluga",       "4,5 m",    "1'400 kg"},
            {"img_blowfish",    "Kugelfisch",     "35 cm",    "13,5 kg"},
            {"img_bullshark",   "Bullenhai",   "1,8 m",    "130 kg"},
            {"img_clownfish",   "Clownfisch",    "8 cm",     "250 g"},
            {"img_eaglerays",   "Adlerrochen",    "9,1 m",    "1'600 kg"},
            {"img_finnwhale",   "Finnwal",   "27 m",     "bis zu 80 t"},
            {"img_goldfish",    "Goldfisch",     "30 cm",    "bis 3 kg"},
            {"img_hammerhead",  "Hammerhai",   "6,1 m",    "450 kg"},
            {"img_humpbackwhale","Buckelwal","16 m",   "30'000 kg"},
            {"img_orca",        "Orca",   "8 m",      "5'400 kg"},
            {"img_pilotwhale",  "Grindwal",  "5,5 m",    "3'000 kg"},
            {"img_spermwhale",  "Pottwal",  "12 m",     "57'000 kg"},
            {"img_tigershark",  "Tigerhai",  "5 m",      "650 kg"},
            {"img_whaleshark",  "Walhai",  "10 m",     "19'000 kg"},
            {"img_whiteshark",  "Weisser Hai",  "6,4 m",    "1'100 kg"}
    };


    //TODO: Trees
    String[][] t = {
            {"img_amadablam","Ama Dablam","6812 m","Nepal"},
            {"img_dreizinnen","Drei Zinnen","2999 m","Italy"},
            {"img_everest","Mount Everest","8848 m","Nepal/China"},
            {"img_fitzroy","Fitz Roy","3395 m","Argentina"},
            {"img_fuji","Fuji","3776 m","Japan"},
            {"img_halfdome","Half Dome","2694 m","U.S.A"},
            {"img_huashan","Hua Shan","2155 m","China"},
            {"img_kilimanjaro","Kilimanjaro","5895 m","Tansania"},
            {"img_matterhorn","Matterhorn","4478 m","Switzerland"},
            {"img_paodeacucar", "Pão de Açúcar", "396 m", "Brasil"},
            {"img_popocatepetl","Popocatepetl","5426 m","Mexico"},
            {"img_saentis", "Säntis", "2505 m", "Switzerland"},
            {"img_tablemountain","Table Mountain","1085 m","South Africa"},
            {"img_uluru","Uluru","863 m","Australia"},
            {"img_zugspitze","Zugspitze","2962 m","Germany"}};

    //TODO Bäume
    String[][] t_de = {
            {"img_amadablam","Ama Dablam","6812 m","Nepal"},
            {"img_dreizinnen","Drei Zinnen","2999 m","Italy"},
            {"img_everest","Mount Everest","8848 m","Nepal/China"},
            {"img_fitzroy","Fitz Roy","3395 m","Argentina"},
            {"img_fuji","Fuji","3776 m","Japan"},
            {"img_halfdome","Half Dome","2694 m","U.S.A"},
            {"img_huashan","Hua Shan","2155 m","China"},
            {"img_kilimanjaro","Kilimanjaro","5895 m","Tansania"},
            {"img_matterhorn","Matterhorn","4478 m","Switzerland"},
            {"img_paodeacucar", "Pão de Açúcar", "396 m", "Brasil"},
            {"img_popocatepetl","Popocatepetl","5426 m","Mexico"},
            {"img_saentis", "Säntis", "2505 m", "Switzerland"},
            {"img_tablemountain","Table Mountain","1085 m","South Africa"},
            {"img_uluru","Uluru","863 m","Australia"},
            {"img_zugspitze","Zugspitze","2962 m","Germany"}};

    /**
     * Constructor for new Quickpic Object, setting the context.
     */
    Context context;

    public Quickpic(Context context) {
        this.context = context;
    }

    /**
     * generates the question for mountains, fish or trees.
     * @param level
     * @return question (String)
     */

    public String getQuestion(char topic, int level) {

        switch (topic) {
            case 'm': question = getMountainQuestions(level); break;
            case 'f': question = getFishQuestions(level); break;
            case 't': question = getTreeQuestions(level); break;
        }
        return question;
    }

    public String getMountainQuestions(int level) {


        switch (level) {
            case 1: question = context.getString(R.string.qm1); break;
            case 2: question = context.getString(R.string.qm2); break;
            case 3: question = context.getString(R.string.qm3); break;
            //default: question = "You're done with this level!";
        }
        return question;
    }

    public String getFishQuestions(int level) {
        switch (level) {
            case 1: question = context.getString(R.string.qf1); break;
            case 2: question = context.getString(R.string.qf2); break;
            case 3: question = context.getString(R.string.qf3); break;
            //default: question = "You're done with this level!";
        }
        return question;
    }

    public String getTreeQuestions(int level) {
        switch (level) {
            case 1: question = context.getString(R.string.qt1); break;
            case 2: question = context.getString(R.string.qt2); break;
            case 3: question = context.getString(R.string.qt3); break;
            //default: question = "You're done with this level!";
        }
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    } //not needed??

    public String[][] getAnswer(char topic) {

        String lang = Locale.getDefault().getLanguage();

        switch (topic) {
            case 'm': if (lang == "de") {
                answer = m_de; break;
            } else answer = m; break;
            case 'f': if (lang == "de") {
                answer = f_de; break;
            } else answer = f; break;
            case 't': if (lang == "de") {
                answer = t_de; break;
            } else answer = t; break;
        }
        return answer;
    }

    public void setAnswer(String[][] answer) { //not needed?
        this.answer = answer;
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
    } //not needed?

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public char getTopic() {
        return topic;
    }

    public void setTopic(char topic) {
        this.topic = topic;
    }

}
