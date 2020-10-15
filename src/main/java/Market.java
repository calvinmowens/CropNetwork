package main.java;

public class Market {

    private Item[] availableItems = new Item[18];

    private MarketItem cornSeed =
            new MarketItem(100, "Corn Seed", "/main/resources/corn.png");

    public MarketItem getCornSeed() {
        return cornSeed;
    }

    public MarketItem getWatermelonSeed() {
        return watermelonSeed;
    }

    private MarketItem watermelonSeed =
            new MarketItem(150, "Watermelon Seed", "/main/resources/watermelon.png");


}
