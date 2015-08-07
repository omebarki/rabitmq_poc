package com.theagent.rabitmq.sub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.theagent.rabitmq.Constants;

/**
 * Created by Omar MEBARKI on 07/08/2015.
 */
public class RabitConsumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection=null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            QueueingConsumer consumer = new QueueingConsumer(channel);
            final boolean autoAck = false;
            channel.basicConsume(Constants.QUEUE_NAME, autoAck, consumer);


            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println(" [x] Received '" + message + "'");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }

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
