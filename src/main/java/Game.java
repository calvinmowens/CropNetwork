package main.java;

public class Game {

    private String name;
    private String gender;
    private int difficulty; // 1-3
    private String startingSeed; // convert this to an int??
    private int startingSeason; // convert this to an int??

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getStartingSeed() {
        return startingSeed;
    }

    public void setStartingSeed(String startingSeed) {
        this.startingSeed = startingSeed;
    }

    public int getStartingSeason() {
        return startingSeason;
    }

    public void setStartingSeason(int startingSeason) {
        this.startingSeason = startingSeason;
    }
}
