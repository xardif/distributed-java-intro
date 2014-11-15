package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

@Component("offerConverter")
public class OfferConverter implements MessageConverter {
    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        Offer offer = (Offer)o;
        MapMessage message = session.createMapMessage();
        message.setDouble("price", offer.price);
        message.setJMSReplyTo(offer.replyTo);
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        throw new UnsupportedOperationException();
    }
}
