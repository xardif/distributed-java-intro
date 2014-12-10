package pl.edu.amu.dji.jms.lab4.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.Item;
import pl.edu.amu.dji.jms.lab4.WarehouseUI;
import pl.edu.amu.dji.jms.lab4.service.message.ProductList;

import javax.jms.Destination;
import java.util.List;


@Service("productListService")
public class ProductListService {

    @Autowired
    @Qualifier("productListJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("warehouseUI")
    private WarehouseUI warehouseUI;

    @Transactional
    public void sendProductList(ProductList productList){
        warehouseUI.log("Sending product list to Points of Sales.");
        jmsTemplate.convertAndSend(productList);
    }
}
