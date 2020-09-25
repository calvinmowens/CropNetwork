package main.java;

public class Game {

    private String name;
    private String gender;
    private String difficulty; // 1-3
    private String startingSeed; // convert this to an int??
    private String startingSeason; // convert this to an int??
    private int money = 0;

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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getStartingSeed() {
        return startingSeed;
    }

    public void setStartingSeed(String startingSeed) {
        this.startingSeed = startingSeed;
    }

    public String getStartingSeason() {
        return startingSeason;
    }

    public void setStartingSeason(String startingSeason) {
        this.startingSeason = startingSeason;
    }

    public int getMoney() {
        setMoney(difficulty);
        return money;
    }

    public void setMoney(String difficulty) {
        switch (difficulty) {
            case ("Casual"):
                money = 10000;
                break;
            case ("Regular"):
                money = 5000;
                break;
            case ("Hardcore"):
                money = 1000;
                break;
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", startingSeed='" + startingSeed + '\'' +
                ", startingSeason='" + startingSeason + '\'' +
                ", money=" + money +
                '}';
    }
}
