package main.java;

public class Market {

    private Item[] availableItems = new Item[18];

    private MarketItem CornSeed = new MarketItem(100, "Corn Seed", "/main/resources/corn.png");

    public MarketItem getCornSeed() {
        return CornSeed;
    }

    public MarketItem getWatermelonSeed() {
        return WatermelonSeed;
    }

    private MarketItem WatermelonSeed = new MarketItem(150, "Watermelon Seed", "/main/resources/watermelon.png");


}
