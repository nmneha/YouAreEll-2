package controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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

    public ArrayList<Message> getMessages() {
        //convert HashSet message seen to an array
        Message[] array = messagesSeen.toArray(new Message[0]);
        //create new array list to hold the Message objects from Message[] array (mutable)
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            messages.add(array[i]);
        }
        // New ArrayList copy to sort the values of messages ArrayList by timestamp using stream sort
        //this gives me all messages in order from oldest to most recent
        ArrayList<Message> copy = (ArrayList<Message>) messages.stream()
                .sorted(Comparator.comparing(Message::getTimestamp)).collect(Collectors.toList());

        //create new ArrayList to collect only the most recent 20 objects from ArrayList copy
        //iterates through copy starting at the last index and stopping before the first index to only get the most recent 20;
        ArrayList<Message> mostRecent = new ArrayList<>();
        for (int i = copy.size()-1; i > copy.size()-21; i--) {
            mostRecent.add(copy.get(i));
        }

        mostRecent.forEach(System.out::println);
        return mostRecent;
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