package pl.edu.amu.dji.jms.lab4.service.message;

import com.google.common.base.Preconditions;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component("initPointOfSalesConverter")
public class InitPointOfSalesConverter implements MessageConverter{

    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(((InitPointOfSales)o).getId()+"");
        textMessage.setJMSReplyTo(((InitPointOfSales)o).getReplyTo());
        return textMessage;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        Preconditions.checkArgument(message instanceof TextMessage);
        TextMessage textMessage = (TextMessage) message;
        return new InitPointOfSales(Integer.parseInt(textMessage.getText()), textMessage.getJMSReplyTo());
    }
}
