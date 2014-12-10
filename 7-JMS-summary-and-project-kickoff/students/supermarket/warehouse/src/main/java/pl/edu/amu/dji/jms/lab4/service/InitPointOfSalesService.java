package pl.edu.amu.dji.jms.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.WarehouseUI;
import pl.edu.amu.dji.jms.lab4.service.message.InitPointOfSales;
import pl.edu.amu.dji.jms.lab4.service.message.ProductList;


@Service("initPointOfSalesService")
public class InitPointOfSalesService {

    @Autowired
    @Qualifier("productList")
    private ProductList productList;

    @Autowired
    @Qualifier("productListJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("warehouseUI")
    private WarehouseUI warehouseUI;

    @Transactional
    public void init(InitPointOfSales initPointOfSales) {
        warehouseUI.log("Received initalizing request from PoS id:" + initPointOfSales.getId() + ". Sending product list.");
        jmsTemplate.convertAndSend(productList);
    }
}
