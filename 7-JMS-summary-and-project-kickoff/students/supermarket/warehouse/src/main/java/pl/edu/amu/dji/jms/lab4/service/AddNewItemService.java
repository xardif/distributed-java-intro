package pl.edu.amu.dji.jms.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.Item;
import pl.edu.amu.dji.jms.lab4.WarehouseUI;
import pl.edu.amu.dji.jms.lab4.service.message.ChangePrice;

@Service("addNewItemService")
public class AddNewItemService {

    @Autowired
    @Qualifier("addNewItemJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("warehouseUI")
    private WarehouseUI warehouseUI;


    @Transactional
    public void add(Item item){
        warehouseUI.log("Added new item: " + item + ". Sending info to Points of Sales.");
        jmsTemplate.convertAndSend(item);
    }
}
