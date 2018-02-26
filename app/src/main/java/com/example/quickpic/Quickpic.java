package com.example.quickpic;

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

    /**
     * Constructor for new Quickpic Object
     */

    public Quickpic() {

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
            case 1: question = "What is the name of this mountain?"; break;
            case 2: question = "How high is this mountain?"; break;
            case 3: question = "In which country lies this mountain?"; break;
            default: question = "You're done with this level!";
        }
        return question;
    }

    public String getFishQuestions(int level) {
        switch (level) {
            case 1: question = "What is the name of this animal?"; break;
            case 2: question = "How long can this animal get?"; break;
            case 3: question = "How much can this animal weigh?"; break;
            default: question = "You're done with this level!";
        }
        return question;
    }

    public String getTreeQuestions(int level) {
        switch (level) {
            case 1: question = "What is the name of this tree?"; break;
            case 2: question = "How high is this tree?"; break;
            case 3: question = "On which continent grows this tree?"; break;
            default: question = "You're done with this level!";
        }
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    } //not needed??

    public String[][] getAnswer(char topic) {

        switch (topic) {
            case 'm': answer = m; break;
            case 'f': answer = f; break;
            case 't': answer = t; break;
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
