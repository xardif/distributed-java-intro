package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class OfferService {

    private JmsTemplate jmsTemplate;

    private Destination offerTopic;

    private Destination orderQueue;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setOfferTopic(Destination offerTopic) {
        this.offerTopic = offerTopic;
    }

    public void setOrderQueue(Destination orderQueue) {
        this.orderQueue = orderQueue;
    }

    public void sendOffer(final Double price) {
        //throw new UnsupportedOperationException();
        jmsTemplate.send(offerTopic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = new ActiveMQMapMessage();
                message.setDouble("price", price);
                message.setJMSReplyTo(orderQueue);
                return message;
            }
        });
    }
}
