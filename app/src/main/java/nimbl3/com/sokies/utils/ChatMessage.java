package nimbl3.com.sokies.utils;

import java.util.Date;

/**
 * Created by yeshu on 1/12/2017.
 */
public class ChatMessage {
    private String messagetext;
    private String messageUser;
    private long messageTime;
    public ChatMessage(String messagetext, String messageUser){
        this.messagetext=messagetext;
        this.messageUser=messageUser;
        messageTime=new Date().getTime();
    }
    public ChatMessage(){

    }

    public String getMessagetext() {
        return messagetext;
    }

    public void setMessagetext(String messagetext) {
        this.messagetext = messagetext;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
