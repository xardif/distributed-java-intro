package pl.edu.amu.dji.jms.lab4.service.message;

import pl.edu.amu.dji.jms.lab4.Item;

import java.util.ArrayList;
import java.util.List;


public class ProductList {
    List<Item> itemList;

    public ProductList() {
        this.itemList = new ArrayList<Item>();
    }

    public ProductList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Item[] getItemArray() {
        return itemList.toArray(new Item[itemList.size()]);
    }

    public void init(){
        itemList.add(new Item("Pair of Boots", 5.21d));
        itemList.add(new Item("Bottle of wine", 10.5d));
        itemList.add(new Item("Shirt", 20.0d));
    }
}
