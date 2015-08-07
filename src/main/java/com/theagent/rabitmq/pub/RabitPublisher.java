package com.theagent.rabitmq.pub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.theagent.rabitmq.Constants;

/**
 * Created by Omar MEBARKI on 07/08/2015.
 */
public class RabitPublisher {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Constants.QUEUE_HOST);
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            String message = "Hello World!";
            channel.basicPublish("", Constants.QUEUE_NAME, null, message.getBytes());
            System.out.printf("message sent:" + message);

        } finally {
            try {
                channel.close();
            } catch (Exception e) {
            }
            try {
                connection.close();
            } catch (Exception e) {
            }
        }

    }
}
