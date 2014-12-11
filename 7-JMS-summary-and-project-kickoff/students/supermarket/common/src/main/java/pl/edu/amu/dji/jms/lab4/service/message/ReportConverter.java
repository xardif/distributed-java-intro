package pl.edu.amu.dji.jms.lab4.service.message;


import com.google.common.base.Preconditions;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import pl.edu.amu.dji.jms.lab4.Item;

import javax.jms.*;

@Component("reportConverter")
public class ReportConverter implements MessageConverter{

    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        MapMessage mapMessage = session.createMapMessage();
        Report report = (Report)o;
        mapMessage.setInt("posId", report.getPosId());
        mapMessage.setString("itemName", report.getItem().getName());
        mapMessage.setDouble("itemPrice", report.getItem().getPrice());
        return mapMessage;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        Preconditions.checkArgument(message instanceof MapMessage);
        MapMessage mapMessage = (MapMessage) message;
        int posId = mapMessage.getInt("posId");
        Item soldItem = new Item(mapMessage.getString("itemName"), mapMessage.getDouble("itemPrice"));
        return new Report(posId, soldItem);
    }

}
