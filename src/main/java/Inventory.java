package main.java;

public class Inventory {
    // seed counts
    private int cornSeedCount = 0;
    private int watermelonSeedCount = 0;
    private int onionSeedCount = 0;
    private int potatoSeedCount = 0;

    // crop counts
    private int potatoCount = 0;
    private int onionCount = 0;
    private int watermelonCount = 0;
    private int cornCount = 0;


    public int getCornSeedCount() {
        return cornSeedCount;
    }

    public int getWatermelonSeedCount() {
        return watermelonSeedCount;
    }

    public void setCornSeedCount(int cornSeedCount) {
        this.cornSeedCount = cornSeedCount;
    }

    public void setWatermelonSeedCount(int watermelonSeedCount) {
        this.watermelonSeedCount = watermelonSeedCount;
    }

    public void setOnionSeedCount(int onionSeedCount) {
        this.onionSeedCount = onionSeedCount;
    }

    public void setPotatoSeedCount(int potatoSeedCount) {
        this.potatoSeedCount = potatoSeedCount;
    }

    public void setPotatoCount(int potatoCount) {
        this.potatoCount = potatoCount;
    }

    public void setOnionCount(int onionCount) {
        this.onionCount = onionCount;
    }

    public void setWatermelonCount(int watermelonCount) {
        this.watermelonCount = watermelonCount;
    }

    public void setCornCount(int cornCount) {
        this.cornCount = cornCount;
    }

    public int getOnionSeedCount() {
        return onionSeedCount;
    }

    public int getPotatoSeedCount() {
        return potatoSeedCount;
    }

    public int getPotatoCount() {
        return potatoCount;
    }

    public int getOnionCount() {
        return onionCount;
    }

    public int getWatermelonCount() {
        return watermelonCount;
    }

    public int getCornCount() {
        return cornCount;
    }


    @Override
    public String toString() {
        return "Inventory{" +
                "cornSeedCount=" + cornSeedCount +
                ", watermelonSeedCount=" + watermelonSeedCount +
                ", onionSeedCount=" + onionSeedCount +
                ", potatoSeedCount=" + potatoSeedCount +
                ", potatoCount=" + potatoCount +
                ", onionCount=" + onionCount +
                ", watermelonCount=" + watermelonCount +
                ", cornCount=" + cornCount +
                '}';
    }
}
