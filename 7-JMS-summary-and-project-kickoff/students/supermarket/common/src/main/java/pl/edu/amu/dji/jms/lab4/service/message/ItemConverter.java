package pl.edu.amu.dji.jms.lab4.service.message;

import com.google.common.base.Preconditions;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import pl.edu.amu.dji.jms.lab4.Item;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;


@Component("itemConverter")
public class ItemConverter implements MessageConverter {
    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        Item soldItem = (Item)o;
        MapMessage message = session.createMapMessage();
        message.setString("name", soldItem.getName());
        message.setDouble("price", soldItem.getPrice());
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        Preconditions.checkArgument(message instanceof MapMessage);

        MapMessage mapMessage = (MapMessage) message;
        double price = mapMessage.getDouble("price");
        String name = mapMessage.getString("name");
        Item item = new Item(name, price);

        return item;
    }
}
