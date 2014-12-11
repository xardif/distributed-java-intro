package pl.edu.amu.dji.jms.lab4.service.message;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import pl.edu.amu.dji.jms.lab4.Item;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;



@Component("changePriceConverter")
public class ChangePriceConverter implements MessageConverter {
    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        ChangePrice changePrice = (ChangePrice)o;
        MapMessage message = session.createMapMessage();

        message.setObject("name", changePrice.getItem().getName());
        message.setObject("price", changePrice.getItem().getPrice());
        message.setDouble("newPrice", changePrice.getNewPrice());

        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        MapMessage mapMessage = (MapMessage)message;

        Item item = new Item(mapMessage.getString("name"), mapMessage.getDouble("price"));
        double newPrice = mapMessage.getDouble("newPrice");

        return new ChangePrice(item, newPrice);
    }
}
