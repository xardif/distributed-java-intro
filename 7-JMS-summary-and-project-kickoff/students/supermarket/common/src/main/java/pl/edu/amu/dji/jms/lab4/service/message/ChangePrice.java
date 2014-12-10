package pl.edu.amu.dji.jms.lab4.service.message;

import pl.edu.amu.dji.jms.lab4.Item;

/**
 * Created by kasztan on 09.12.14.
 */
public class ChangePrice {
    private Item item;
    private double newPrice;

    public ChangePrice(Item item, double newPrice) {
        this.item = item;
        this.newPrice = newPrice;
    }

    public Item getItem() {
        return item;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
