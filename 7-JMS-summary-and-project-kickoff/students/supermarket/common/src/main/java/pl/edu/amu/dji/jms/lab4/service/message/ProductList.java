package pl.edu.amu.dji.jms.lab4.service.message;

import pl.edu.amu.dji.jms.lab4.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kasztan on 09.12.14.
 */
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
}
