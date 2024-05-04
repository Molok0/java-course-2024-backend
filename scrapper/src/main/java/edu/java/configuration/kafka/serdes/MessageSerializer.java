package edu.java.configuration.kafka.serdes;

import messages.MessageOuterClass;
import org.apache.kafka.common.serialization.Serializer;

public class MessageSerializer implements Serializer<MessageOuterClass.Message> {
    @Override
    public byte[] serialize(String topic, MessageOuterClass.Message data) {
        return data.toByteArray();
    }
}
