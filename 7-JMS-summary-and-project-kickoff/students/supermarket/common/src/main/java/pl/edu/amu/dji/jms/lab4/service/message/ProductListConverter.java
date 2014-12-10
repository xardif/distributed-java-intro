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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("productListConverter")
public class ProductListConverter implements MessageConverter {
    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        ProductList productList = (ProductList)o;
        MapMessage message = session.createMapMessage();

        Map<String, Double> map = new HashMap<String, Double>();

        for(Item item : productList.getItemList())
            map.put(item.getName(), item.getPrice());

        message.setObject("itemList", map);

        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        Preconditions.checkArgument(message instanceof MapMessage);
        MapMessage mapMessage = (MapMessage)message;

        Map<String, Double> map = (Map<String, Double>) mapMessage.getObject("itemList");
        List<Item> itemList = new ArrayList<Item>();
        for(Map.Entry<String, Double> entry : map.entrySet()){
            itemList.add(new Item(entry.getKey(), entry.getValue()));
        }
        return new ProductList(itemList);
    }
}