package twittermessagesfetch;

/**
 * POJO for the messages.
 */
public class Message {
    private String text;
    private long senderId;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getSenderId() {
        return senderId;
    }
}
