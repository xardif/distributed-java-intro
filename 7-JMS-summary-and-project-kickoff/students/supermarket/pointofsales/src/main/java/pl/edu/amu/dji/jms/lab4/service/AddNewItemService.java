package pl.edu.amu.dji.jms.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.Item;
import pl.edu.amu.dji.jms.lab4.PointOfSales;
import pl.edu.amu.dji.jms.lab4.PointOfSalesPane;
import pl.edu.amu.dji.jms.lab4.service.message.ChangePrice;

@Component("addNewItemService")
public class AddNewItemService {
    @Autowired
    @Qualifier("pointOfSales")
    private PointOfSales pointOfSales;

    @Autowired
    @Qualifier("pointOfSalesPane")
    private PointOfSalesPane pointOfSalesPane;

    @Transactional
    public void add(Item item) {
        pointOfSalesPane.log("Received new item: " + item + ".");
        pointOfSales.addNewItem(item);
        pointOfSalesPane.updateProductList();
    }
}
