package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    public static int MAX_HARVEST_PER_DAY = 1;
    public static int MAX_WATER_PER_DAY = 1;
    private String name;
    private String gender;
    private String difficulty; // 1-3
    private String currentSeed; // convert this to an int??
    private String startingSeason; // convert this to an int??

    public int getInitCounter() {
        return initCounter;
    }

    public void setInitCounter(int initCounter) {
        this.initCounter = initCounter;
    }

    private int initCounter = 0;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    private int day = 1;
    private int money = 0;
    private Inventory inventory;
    private int cropPrice;
    private int seedPrice;
    private List<InventoryItem> inventoryList = new ArrayList<>();
    public List<InventoryItem> getInventoryList() {
        return inventoryList;
    }
    private Map<String, InventoryItem> inventoryMap = new HashMap<>();


    private CropPlot[] plots = new CropPlot[12];
    private InventoryItem defaultItem = new InventoryItem(0, "default",
            "/main/resources/blank.png", 0);
    private int plotHarvested = 0; // count plots that has been harvested in one day
    private int plotWatered = 0; // count plots that has been watered in one day
    public int getPlotHarvested() {
        return plotHarvested;
    }
    public int getPlotWatered() {
        return plotWatered;
    }
    public void incrementPlotHarvested() {
        this.plotHarvested++;
    }
    public void incrementPlotWatered() {
        this.plotWatered++;
    }
    public void resetPlotWatered() {
        this.plotWatered = 0;
    }
    public void resetPlotHarvested() {
        this.plotHarvested = 0;
    }
    public void incrementMaxPlotHarvested() {
        MAX_HARVEST_PER_DAY = 2;
    }
    public void incrementMaxPlotWatered() {
        MAX_WATER_PER_DAY = 2;
    }




    public void initializeInventory() {
        inventoryMap.put(
                "Corn Seed",
                new InventoryItem(
                        seedPrice,
                        "Corn Seed",
                        "/main/resources/cornBag.png",
                        0));
        inventoryMap.put(
                "Onion Seed",
                new InventoryItem(
                        seedPrice,
                        "Onion Seed",
                        "/main/resources/onionBag.png",
                        0));
        inventoryMap.put(
                "Watermelon Seed",
                new InventoryItem(
                        seedPrice,
                        "Watermelon Seed",
                        "/main/resources/watermelonBag.png",
                        0));
        inventoryMap.put(
                "Potato Seed",
                new InventoryItem(
                        seedPrice,
                        "Potato Seed",
                        "/main/resources/potatoBag.png",
                        0));
        inventoryMap.put(
                "Corn",
                new InventoryItem(
                        cropPrice,
                        "Corn",
                        "/main/resources/corn.png",
                        0));
        inventoryMap.put(
                "Onion",
                new InventoryItem(
                        cropPrice,
                        "Onion",
                        "/main/resources/OnionCrop.png",
                        0));
        inventoryMap.put(
                "Watermelon",
                new InventoryItem(
                        cropPrice,
                        "Watermelon",
                        "/main/resources/WatermelonCrop.png",
                        0));
        inventoryMap.put(
                "Potato",
                new InventoryItem(
                        cropPrice,
                        "Potato",
                        "/main/resources/PotatoCrop.png",
                        0));
        inventoryMap.put(
                "Fertilizer",
                new InventoryItem(
                        seedPrice,
                        "Fertilizer",
                        "/main/resources/Fertilizer.png",
                        0));
        inventoryMap.put(
                "Pesticide",
                new InventoryItem(
                        seedPrice,
                        "Pesticide",
                        "/main/resources/Pesticide.png",
                        0));
        inventoryMap.put(
                "Corn P",
                new InventoryItem(
                        cropPrice - 30,
                        "Corn P",
                        "/main/resources/corn-pesticide.png",
                        0));
        inventoryMap.put(
                "Onion P",
                new InventoryItem(
                        cropPrice - 30,
                        "Onion P",
                        "/main/resources/onion-pesticide.png",
                        0));
        inventoryMap.put(
                "Watermelon P",
                new InventoryItem(
                        cropPrice - 30,
                        "Watermelon P",
                        "/main/resources/watermelon-pesticide.png",
                        0));
        inventoryMap.put(
                "Potato P",
                new InventoryItem(
                        cropPrice - 30,
                        "Potato P",
                        "/main/resources/potato-pesticide.png",
                        0));
        inventoryMap.put(
                "Irrigation",
                new InventoryItem(
                        5000,
                        "Irrigation",
                        "/main/resources/irrigation.png",
                        0));
        inventoryMap.put(
                "Tractor",
                new InventoryItem(
                        5000,
                        "Tractor",
                        "/main/resources/tractor.png",
                        0));
    }

    public InventoryItem getDefaultItem() {
        return this.defaultItem;
    }
    // Tells whether to harvest or water or kill
    private String plotClickMode = null;

    public String getPlotClickMode() {
        return this.plotClickMode;
    }

    public void setPlotClickMode(String mode) {
        this.plotClickMode = mode;
    }

    public Map<String, InventoryItem> getInventoryMap() {
        return this.inventoryMap;
    }

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

    public String getCurrentSeed() {
        return currentSeed;
    }

    public void setCurrentSeed(String currentSeed) {
        this.currentSeed = currentSeed;
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
        if (inventoryMap.get(itemName) != null) {
            inventoryMap.get(itemName).setCount(inventoryMap.get(itemName).getCount() - amount);
            setMoney(getMoney() + amount * price);
        }

    }

    public void buyFromMarket(String itemName, int amount, int price) {
        if (inventoryMap.get(itemName) != null) {
            if (inventoryMap.get(itemName).getCount() + amount <= 100
                    && (getMoney() - amount * price) >= 0) {
                inventoryMap.get(itemName).setCount(inventoryMap.get(itemName).getCount() + amount);
                setMoney(getMoney() - amount * price);
            }
        }
    }



    @Override
    public String toString() {
        return "Game{"
                + "name='" + name + '\''
                + ", gender='" + gender + '\''
                + ", difficulty='" + difficulty + '\''
                + ", startingSeed='" + currentSeed + '\''
                + ", startingSeason='" + startingSeason + '\''
                + ", money=" + money
                + '}';
    }
}
