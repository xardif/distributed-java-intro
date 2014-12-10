package pl.edu.amu.dji.jms.lab4;

/**
 * Created by kasztan on 08.12.14.
 */
public class Item{

    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return name + " - " + price;
    }

    public boolean equals(Item item){
        if(this.equals(item.getName()) && this.price == item.price)
                return true;
        return false;
    }
}
