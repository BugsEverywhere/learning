package indi.simon.learning.models;

import java.util.UUID;

/**
 * @author chenzhuo(zhiyue)
 */
public class MessageModel {

    private String key;
    private String message;

    public MessageModel() {
        key = System.currentTimeMillis() + "_" + Thread.currentThread().getName();
        message = UUID.randomUUID().toString();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "key='" + key + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
