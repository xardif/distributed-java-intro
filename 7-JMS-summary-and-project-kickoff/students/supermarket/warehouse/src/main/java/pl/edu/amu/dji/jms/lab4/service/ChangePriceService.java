package pl.edu.amu.dji.jms.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.Item;
import pl.edu.amu.dji.jms.lab4.WarehouseUI;
import pl.edu.amu.dji.jms.lab4.service.message.ChangePrice;
import pl.edu.amu.dji.jms.lab4.service.message.ProductList;

import javax.jms.Destination;


@Service("changePriceService")
public class ChangePriceService {

    @Autowired
    @Qualifier("changePriceJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("warehouseUI")
    private WarehouseUI warehouseUI;


    @Transactional
    public void changePrice(Item item, double newPrice){
        warehouseUI.log("Changing price on: " + item + " to: " + newPrice);
        ChangePrice changePrice = new ChangePrice(item, newPrice);
        jmsTemplate.convertAndSend(changePrice);
    }
}
