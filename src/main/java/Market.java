package main.java;

public class Market {

//    private Item[] avaliableItems = new Item[18];

    private Item CornSeed = new Item(100, "Corn Seed", "/main/resources/corn.png");

    public Item getCornSeed() {
        return CornSeed;
    }

    public Item getWatermelonSeed() {
        return WatermelonSeed;
    }

    private Item WatermelonSeed = new Item(150, "Watermelon Seed", "/main/resources/watermelon.png");


}
