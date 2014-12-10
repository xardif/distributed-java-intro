package pl.edu.amu.dji.jms.lab4.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.PointOfSales;
import pl.edu.amu.dji.jms.lab4.PointOfSalesPane;
import pl.edu.amu.dji.jms.lab4.service.message.ChangePrice;

@Service("changePriceService")
public class ChangePriceService {

    @Autowired
    @Qualifier("pointOfSales")
    private PointOfSales pointOfSales;

    @Autowired
    @Qualifier("pointOfSalesPane")
    private PointOfSalesPane pointOfSalesPane;

    @Transactional
    public void change(ChangePrice changePrice) {
        pointOfSalesPane.log("Received change price on: " + changePrice.getItem() + " to: " + changePrice.getNewPrice() + ".");
        pointOfSales.changePrice(changePrice);
        pointOfSalesPane.updateProductList();
    }
}
