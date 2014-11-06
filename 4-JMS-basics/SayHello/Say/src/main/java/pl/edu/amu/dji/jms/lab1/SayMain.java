package pl.edu.amu.dji.jms.lab1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SayMain {

    public static final String EXIT = "exit";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue("SayHelloQueue");
        MessageProducer producer = session.createProducer(queue);

 
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        producer.setTimeToLive(3000);
        connection.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String in = "";
        while (!in.equalsIgnoreCase(EXIT)) {
            System.out.print("Say hello to:");
            text = bufferedReader.readLine();


            //Create TextMessage from session with text variable
            //Send this message to queue (use producer for that)
            TextMessage textMessage = session.createTextMessage(text);
            textMessage.setBooleanProperty("ifDot", text.contains("."));
            textMessage.setBooleanProperty("ifSpace", text.contains(" "));
            producer.send(textMessage);
        }

        /*
        EXERCISE 3
            After 3 seconds messages send by Producer get timeout.
            While all consumers all on, they receive it immediately.
         */


        session.close();
        connection.close();
    }
}
