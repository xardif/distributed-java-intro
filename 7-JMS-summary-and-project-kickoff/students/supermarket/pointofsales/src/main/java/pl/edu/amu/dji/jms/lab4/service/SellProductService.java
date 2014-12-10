package pl.edu.amu.dji.jms.lab4.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.Item;
import pl.edu.amu.dji.jms.lab4.PointOfSalesPane;

@Service("sellProductService")
public class SellProductService {

    @Autowired
    @Qualifier("reportJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("pointOfSalesPane")
    private PointOfSalesPane pointOfSalesPane;

    @Transactional
    public void sellItem(Item soldItem){
        pointOfSalesPane.log("Sold " + soldItem + ". Sending report.");
        jmsTemplate.convertAndSend(soldItem);
    }
}