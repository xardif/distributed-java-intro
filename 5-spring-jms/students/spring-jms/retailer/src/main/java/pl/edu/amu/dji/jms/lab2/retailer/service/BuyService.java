package pl.edu.amu.dji.jms.lab2.retailer.service;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class BuyService implements MessageListener {

    private JmsTemplate jmsTemplate;

    private Double maxPrice;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public void onMessage(Message message) {
        //throw new UnsupportedOperationException();
        MapMessage mapMessage = (MapMessage) message;
        Double price = null;
        try {
            price = mapMessage.getDouble("price");
        } catch (JMSException e) {
            e.printStackTrace();
        }
        if(maxPrice.compareTo(price) == 1){
            try {
                jmsTemplate.send(mapMessage.getJMSReplyTo(), new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        MapMessage mapMessage = new ActiveMQMapMessage();
                        mapMessage.setString("retailerID", getClass().getName());
                        mapMessage.setDouble("quantify", 5.3 + Math.random());
                        return mapMessage;
                    }
                });
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}
