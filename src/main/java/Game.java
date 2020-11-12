package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private String name;
    private String gender;
    private String difficulty; // 1-3
    private String startingSeed; // convert this to an int??
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


    public void initializeInventory() {
        inventoryMap.put("Corn Seed",
                new InventoryItem(seedPrice, "Corn Seed",
                        "/main/resources/cornBag.png", 0));
        inventoryMap.put("Onion Seed",
                new InventoryItem(seedPrice, "Onion Seed",
                        "/main/resources/onionBag.png", 0));
        inventoryMap.put("Watermelon Seed",
                new InventoryItem(seedPrice, "Watermelon Seed",
                        "/main/resources/watermelonBag.png", 0));
        inventoryMap.put("Potato Seed",
                new InventoryItem(seedPrice, "Potato Seed",
                        "/main/resources/potatoBag.png", 0));
        inventoryMap.put("Corn",
                new InventoryItem(cropPrice, "Corn",
                        "/main/resources/corn.png", 0));
        inventoryMap.put("Onion",
                new InventoryItem(cropPrice, "Onion",
                        "/main/resources/OnionCrop.png", 0));
        inventoryMap.put("Watermelon",
                new InventoryItem(cropPrice, "Watermelon",
                        "/main/resources/WatermelonCrop.png", 0));
        inventoryMap.put("Potato",
                new InventoryItem(cropPrice, "Potato",
                        "/main/resources/PotatoCrop.png", 0));
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
        if (inventoryMap.get(itemName) != null) {
            inventoryMap.get(itemName).setCount(inventoryMap.get(itemName).getCount() - amount);
            setMoney(getMoney() + amount * price);
            }
//        switch (itemName) {
//        case "Corn":
//            inventory.setCornCount(inventory.getCornCount() - amount);
//            setMoney(getMoney() + amount * price);
//            break;
//        case "Watermelon":
//            inventory.setWatermelonCount(inventory.getWatermelonCount() - amount);
//            setMoney(getMoney() + amount * price);
//            break;
//        case "Onion":
//            inventory.setOnionCount(inventory.getOnionCount() - amount);
//            setMoney(getMoney() + amount * price);
//            break;
//        case "Potato":
//            inventory.setPotatoCount(inventory.getPotatoCount() - amount);
//            setMoney(getMoney() + amount * price);
//            break;
//        default:
//            System.out.println("Item not selected");
//        }
    }

    public void buyFromMarket(String itemName, int amount, int price) {
        if (inventoryMap.get(itemName) != null) {
            if(inventoryMap.get(itemName).getCount() + amount <= 100
                    && (getMoney() - amount * price) > 0) {
                inventoryMap.get(itemName).setCount(inventoryMap.get(itemName).getCount() + amount);
                setMoney(getMoney() - amount * price);
            }
        }
//        switch (itemName) {
//        case "Corn":
//            if (inventoryMap.get("Corn Seed").getCount() + amount <= 100
//                    && (getMoney() - amount * price) > 0) {
//                inventoryMap.get("Corn Seed").setCount(inventoryMap.get("Corn Seed").getCount() + amount);
//                setMoney(getMoney() - amount * price);
//            }
//            break;
//        case "Watermelon":
//            if (inventoryMap.get("Watermelon Seed").getCount() + amount <= 100
//                    && (getMoney() - amount * price) > 0) {
//                inventoryMap.get("Watermelon Seed").setCount(inventoryMap.get("Watermelon Seed").getCount() + amount);
//                setMoney(getMoney() - amount * price);
//            }
//            break;
//        case "Onion":
//            if (inventoryMap.get("Onion Seed").getCount() + amount <= 100
//                    && (getMoney() - amount * price) > 0) {
//                inventoryMap.get("Onion Seed").setCount(inventoryMap.get("Onion Seed").getCount() + amount);
//                setMoney(getMoney() - amount * price);
//            }
//            break;
//        case "Potato":
//            if (inventoryMap.get("Potato Seed").getCount() + amount <= 100
//                    && (getMoney() - amount * price) > 0) {
//                inventoryMap.get("Potato Seed").setCount(inventoryMap.get("Potato Seed").getCount() + amount);
//                setMoney(getMoney() - amount * price);
//            }
//            break;
//        default:
//            System.out.println("Item not selected");
//        }
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
