package pl.edu.amu.dji.jms.lab1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class HelloMain {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        /*
        Create Connection instance from ConnectionFactory

        Create Session instance from connection object.
        - session shouldn't by transactional and should by in auto acknowledge mode (Session.AUTO_ACKNOWLEDGE).

        Create Destination queue from session (check Session class and createQueue method)
        - queue name should be "SayHelloQueue"


        Create Destination topic instance from session (check Session class and createTopic method)
        - topic name should be "SayHelloTopic"
         */

        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination topic = session.createTopic("SayHelloTopic");
        MessageConsumer consumer = session.createConsumer(topic);

        /*
        Create MessageConsumer instance from session (check Session class and createConsumer method)

        Implement onMessage in MessageListener interface
        - check if message is in proper type (see message type in Say class) by instanceof
        - get text from message (remember to cast message to proper type)
        - print message text to sysout
        - don't forget to handle JMSException
         */
        MessageListener helloListener = new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(!(message instanceof TextMessage)){
                    throw new UnsupportedOperationException();
                }

                try {
                    System.out.println(((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        };

        //Set MessageListener implementation as a message listener in MessageConsumer
        consumer.setMessageListener(helloListener);

        /*
        EXERCISE 1
            2. Run - first HelloMain consumer gets all messages at once
            3. Run - Consumers get messages ordered by who run first.
        EXERCISE 2
            2. Run - nothing has shown
            3. Run - All consumers get same messages.
        */
        connection.start();
    }
}