package main.java;

public class InventoryItem extends Item{
    private int count;
    public InventoryItem(int sellPrice, String name, String imgUrl, int count) {
        super(sellPrice, name, imgUrl);
        this.count = count;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int newCount) {
        this.count = newCount;
    }
}
