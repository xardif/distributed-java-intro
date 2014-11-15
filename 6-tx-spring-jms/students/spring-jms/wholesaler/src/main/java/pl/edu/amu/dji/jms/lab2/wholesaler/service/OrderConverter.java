package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

@Component("orderConverter")
public class OrderConverter implements MessageConverter{

    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        Order order = new Order();
        MapMessage mapMessage = (MapMessage)message;
        order.quantify = mapMessage.getInt("quantify");
        order.retailerID = mapMessage.getString("retailerID");
        return order;
    }
}
