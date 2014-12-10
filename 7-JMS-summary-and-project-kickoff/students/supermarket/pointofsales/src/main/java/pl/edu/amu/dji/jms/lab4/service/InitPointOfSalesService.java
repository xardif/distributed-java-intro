package pl.edu.amu.dji.jms.lab4.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.PointOfSales;
import pl.edu.amu.dji.jms.lab4.PointOfSalesPane;
import pl.edu.amu.dji.jms.lab4.service.message.InitPointOfSales;

import javax.jms.Destination;

@Service("initPointOfSalesService")
public class InitPointOfSalesService {

    @Autowired
    @Qualifier("initPointOfSalesJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("productListTopic")
    private Destination productListTopic;

    @Autowired
    @Qualifier("pointOfSales")
    private PointOfSales pointOfSales;

    @Autowired
    @Qualifier("pointOfSalesPane")
    private PointOfSalesPane pointOfSalesPane;

    @Transactional
    public void sendAddPointOfSale() {
        pointOfSalesPane.log("Sending initializing PoS message.");
        InitPointOfSales initPointOfSales = new InitPointOfSales(pointOfSales.getId(), productListTopic);
        jmsTemplate.convertAndSend(initPointOfSales);
    }
}