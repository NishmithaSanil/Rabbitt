package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Sender1
    {
        private final static String QUEUE_NAME = "heee";
        public static void main( String[] args ) throws IOException, TimeoutException {
            Scanner scan=new Scanner(System.in);
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("192.168.20.50");
            factory.setUsername("admin");
            factory.setPassword("admin");
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = String.join(".","hello....");

                channel.basicPublish("", "heee", null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }


