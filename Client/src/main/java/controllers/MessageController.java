package controllers;

import java.io.IOException;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;
import org.json.simple.JSONArray;

public class MessageController {

    ServerController serverController = ServerController.shared();
    public HashSet<Message> messagesSeen = new HashSet<>();
    // why a HashSet??

    private static MessageController mC = new MessageController();
    public static MessageController shared() {
        return mC;
    }

    private MessageController() {
        ServerController serverController = ServerController.shared();
        JSONArray messagesJSON = serverController.messagesGet();

        for (int i = 0; i < messagesJSON.size(); i++) {
            Object obj = messagesJSON.get(i);
            String str = obj.toString();
            String[] messageList = str.split(",");
            String toID = messageList[0].substring(8);
            String sequence = messageList[1].substring(11);
            String message = messageList[2].substring(10);
            String fromID = messageList[3].substring(9);
            String timeStamp = messageList[4]. substring(12);
            Message messageObj = new Message(message, fromID, toID, timeStamp, sequence);
            messagesSeen.add(messageObj);
        }
    }

    public ArrayList<Message> getMessages() throws IOException {
        return null;
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }

    public Message getMessageForSequence(String seq) {
        return null;
    }

    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
 
}