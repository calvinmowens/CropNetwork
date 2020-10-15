package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {

    private String name;
    private String gender;
    private String difficulty; // 1-3
    private String startingSeed; // convert this to an int??
    private String startingSeason; // convert this to an int??
    private int money = 0;
    private Inventory inventory;
    private int cropPrice;
    private int seedPrice;
    private List<InventoryItem> inventoryList = new ArrayList<>();

    public List<InventoryItem> getInventoryList() {
        return inventoryList;
    }

    private HashMap<String, CropPlot> plotMap = new HashMap<>();
    public HashMap<String, CropPlot> getPlotMap() {
        return plotMap;
    }
    private CropPlot[] plots = new CropPlot[12];


    public CropPlot[] getPlots() {
        return plots;
    }

    public int getSeedPrice() {
        return seedPrice;
    }

    public void setSeedPrice(int seedPrice) {
        this.seedPrice = seedPrice;
    }

    public int getCropPrice() {
        return cropPrice;
    }

    public void setCropPrice(int cropPrice) {
        this.cropPrice = cropPrice;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

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
        return money;
    }

    public void setMoney(String difficulty) {
        switch (difficulty) {
        case ("Casual"):
            money = 10000;
            seedPrice = 70;
            cropPrice = 130;
            break;
        case ("Regular"):
            money = 5000;
            seedPrice = 100;
            cropPrice = 100;
            break;
        case ("Hardcore"):
            money = 1000;
            seedPrice = 130;
            cropPrice = 70;
            break;
        default:
            break;
        }
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void sellFromInventory(String itemName, int amount, int price) {
        switch (itemName) {
        case "Corn":
            inventory.setCornCount(inventory.getCornCount() - amount);
            setMoney(getMoney() + amount * price);
            break;
        case "Watermelon":
            inventory.setWatermelonCount(inventory.getWatermelonCount() - amount);
            setMoney(getMoney() + amount * price);
            break;
        case "Onion":
            inventory.setOnionCount(inventory.getOnionCount() - amount);
            setMoney(getMoney() + amount * price);
            break;
        case "Potato":
            inventory.setPotatoCount(inventory.getPotatoCount() - amount);
            setMoney(getMoney() + amount * price);
            break;
        default:
            System.out.println("Item not selected");
        }
    }

    public void buyFromMarket(String itemName, int amount, int price) {
        switch (itemName) {
        case "Corn":
            if (inventory.getCornSeedCount() + amount <= 100
                    && (getMoney() - amount * price) > 0) {
                inventory.setCornSeedCount(inventory.getCornSeedCount() + amount);
                setMoney(getMoney() - amount * price);
            }
            break;
        case "Watermelon":
            if (inventory.getWatermelonSeedCount() + amount <= 100
                    && (getMoney() - amount * price) > 0) {
                inventory.setWatermelonSeedCount(inventory.getWatermelonSeedCount() + amount);
                setMoney(getMoney() - amount * price);
            }
            break;
        case "Onion":
            if (inventory.getOnionSeedCount() + amount <= 100
                    && (getMoney() - amount * price) > 0) {
                inventory.setOnionSeedCount(inventory.getOnionSeedCount() + amount);
                setMoney(getMoney() - amount * price);
            }
            break;
        case "Potato":
            if (inventory.getPotatoSeedCount() + amount <= 100
                    && (getMoney() - amount * price) > 0) {
                inventory.setPotatoSeedCount(inventory.getPotatoSeedCount() + amount);
                setMoney(getMoney() - amount * price);
            }
            break;
        default:
            System.out.println("Item not selected");
        }
    }



    @Override
    public String toString() {
        return "Game{"
                + "name='" + name + '\''
                + ", gender='" + gender + '\''
                + ", difficulty='" + difficulty + '\''
                + ", startingSeed='" + startingSeed + '\''
                + ", startingSeason='" + startingSeason + '\''
                + ", money=" + money
                + '}';
    }
}
