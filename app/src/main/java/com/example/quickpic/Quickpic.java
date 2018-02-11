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

    String[][] m = {
            {"img_amadablam","Ama Dablam","6812 m","Nepal"},
            {"img_dreizinnen","Drei Zinnen","2999 m","Italy"},
            {"img_everest","Mount Everest","8848 m","Nepal/China"},
            {"img_fitzroy","Fitz Roy","3395 m","Argentina"},
            {"img_fuji","Fuji","3776 m","Japan"},
            {"img_halfdome","Half Dome","2694 m","U.S.A"},
            {"img_huashan","Hua Shan","2155 m","China"},
            {"img_kilimanjaro","Kilimanjaro","5895 m","Tansania"},
            {"img_matterhorn","Matterhorn","4478 m","Switzerland"},
            {"img_paodeacucar", "Pão de Açúcar", "396 m", "Brasil"}, //Zuckerhut
            {"img_popocatepetl","Popocatepetl","5426 m","Mexico"},
            {"img_saentis", "Säntis", "2505 m", "Switzerland"}, //Zuckerhut
            {"img_tablemountain","Table Mountain","1085 m","South Africa"},
            {"img_uluru","Uluru","863 m","Australia"},
            {"img_zugspitze","Zugspitze","2962 m","Germany"}};

    String[][] f = {
            {"img_beluga","Beluga Whale","4 m","Arctic Sea"}, //https://en.wikipedia.org/wiki/Beluga_whale
            {"img_blowfish","Blowfish","35 cm","Tropic Seas"}, //https://en.wikipedia.org/wiki/Tetraodontidae
            {"img_clownfish","Clownfish","10 cm","Pacific Ocean Reefs"}, //https://en.wikipedia.org/wiki/Amphiprioninae
            {"img_eaglerays","Eagle Ray","5 m","Open Tropical Ocean"}, //https://en.wikipedia.org/wiki/Eagle_ray
            {"img_goldfish","Goldfish","? m","Water"},
            {"img_halterfish","Halterfish","? m","Water"},
            {"img_hammerhead","Hammerhead Shark","? m","Water"},
            {"img_humpbackwhale","Humpback Whale","? m","Water"},
            {"img_lionfish","Lionfish","? m","Water"},
            {"img_orca","Orca","? m","Water"},
            {"img_palettesurgeonfish","Palette Surgeonfish","? m","Water"},
            {"img_pilotwhale","Pilotwhale","? m","Water"},
            {"img_seahorse", "Seahorse", "? m", "Water"},
            {"img_whaleshark","Whale Shark","? m","Water"},
            {"img_whiteshark","Great White Shark","? m","Water"}};

    String[][] t = { //TODO: Trees
            {"img_amadablam","Ama Dablam","6812 m","Nepal"},
            {"img_dreizinnen","Drei Zinnen","2999 m","Italy"},
            {"img_everest","Mount Everest","8848 m","Nepal/China"},
            {"img_fitzroy","Fitz Roy","3395 m","Argentina"},
            {"img_fuji","Fuji","3776 m","Japan"},
            {"img_halfdome","Half Dome","2694 m","U.S.A"},
            {"img_huashan","Hua Shan","2155 m","China"},
            {"img_kilimanjaro","Kilimanjaro","5895 m","Tansania"},
            {"img_matterhorn","Matterhorn","4478 m","Switzerland"},
            {"img_paodeacucar", "Pão de Açúcar", "396 m", "Brasil"}, //Zuckerhut
            {"img_popocatepetl","Popocatepetl","5426 m","Mexico"},
            {"img_saentis", "Säntis", "2505 m", "Switzerland"}, //Zuckerhut
            {"img_tablemountain","Table Mountain","1085 m","South Africa"},
            {"img_uluru","Uluru","863 m","Australia"},
            {"img_zugspitze","Zugspitze","2962 m","Germany"}};

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
            case 1: question = "What is the name of this marine animal?"; break;
            case 2: question = "How big is this marine animal?"; break;
            case 3: question = "In which ocean lives this marine animal?"; break;
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
    } //maybe not needed??

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
