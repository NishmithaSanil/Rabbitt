package org.example;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;


/**
 * Hello world!
 *
 */
public class App 
{
    private final static String QUEUE_NAME = "seven";
    public static void main( String[] args ) throws IOException, TimeoutException {
        Scanner scan=new Scanner(System.in);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.20.50");
        factory.setUsername("admin");
        factory.setPassword("admin");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message;
            System.out.println("Sender can send the message now");
            message=scan.next();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
