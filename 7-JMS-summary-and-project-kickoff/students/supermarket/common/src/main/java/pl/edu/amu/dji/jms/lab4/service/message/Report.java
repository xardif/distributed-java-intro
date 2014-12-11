package pl.edu.amu.dji.jms.lab4.service.message;

import pl.edu.amu.dji.jms.lab4.Item;

/**
 * Created by kasztan on 11.12.14.
 */
public class Report {
    private int posId;
    private Item item;

    public Report(int posId, Item item) {
        this.posId = posId;
        this.item = item;
    }

    public int getPosId() {
        return posId;
    }

    public Item getItem() {
        return item;
    }
}
