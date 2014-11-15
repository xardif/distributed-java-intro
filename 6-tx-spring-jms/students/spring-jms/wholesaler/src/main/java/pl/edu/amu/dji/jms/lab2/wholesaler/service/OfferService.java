package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;


@Service("offerService")
public class OfferService {

    @Autowired
    @Qualifier("offerJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("orderQueue")
    private Destination orderQueue;

    @Transactional
    public void sendOffer(final Double price){
        Offer offer = new Offer();
        offer.price = price;
        offer.replyTo = orderQueue;
        jmsTemplate.convertAndSend(offer);
    }
}
