package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class OrderService implements MessageListener {

    @Override
    public void onMessage(Message message) {
        //throw new UnsupportedOperationException();
        try {
            MapMessage mapMessage = (MapMessage) message;
            System.out.printf("%s %f\n", mapMessage.getString("retailerID"), mapMessage.getDouble("quantify"));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
