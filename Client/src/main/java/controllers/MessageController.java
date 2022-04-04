package controllers;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;
import org.json.simple.JSONArray;

public class MessageController {

    private ServerController serverController = ServerController.shared();
    public HashSet<Message> messagesSeen;

//    private static MessageController mC;
//
//    static {
//        try {
//            mC = new MessageController();
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static MessageController shared() {
//        return mC;
//    }

    public MessageController() throws JsonProcessingException {
//        String messageString = serverController.messagesGet().toString();
//        ObjectMapper objectMapper = new ObjectMapper();
//        messagesSeen = objectMapper.readValue(messageString, new TypeReference<>() {});
    }


    public ArrayList<Message> getMessages() throws JsonProcessingException {
        update();
        ArrayList<Message> messages = new ArrayList<>();
        for (Message i : messagesSeen) {
            messages.add(i);
        }
        ArrayList<Message> copy = (ArrayList<Message>) messages.stream()
                .sorted(Comparator.comparing(Message::getTimestamp)).collect(Collectors.toList());
        ArrayList<Message> mostRecent = new ArrayList<>();
        for (int i = copy.size()-1; i > copy.size()-21; i--) {
            mostRecent.add(copy.get(i));
        }
        mostRecent.forEach(System.out::println);
        return mostRecent;
    }

    public ArrayList<Message> getMessagesForId(Id Id) throws JsonProcessingException {
        update();
      ArrayList<Message> messages = new ArrayList<>();
        for (Message m : messagesSeen) {
            if (m.getFromId().equals(Id.getGithub())) {
                messages.add(m);
            }
        }
        ArrayList<Message> copy = (ArrayList<Message>) messages.stream()
                .sorted(Comparator.comparing(Message::getTimestamp)).collect(Collectors.toList());
        ArrayList<Message> mostRecent = new ArrayList<>();
        if (copy.size() >= 20) {
            for (int i = copy.size()-1; i > copy.size()-21; i--) {
                mostRecent.add(copy.get(i));
            }
        } else {
            mostRecent.addAll(copy);
        }
        return mostRecent;
    }

    public Message getMessageForSequence(String seq) throws JsonProcessingException {
        update();
        for (Message m : messagesSeen) {
            if (m.getSeqId().equals(seq)) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) throws JsonProcessingException {
        update();
        ArrayList<Message> messages = new ArrayList<>();
        for (Message m : messagesSeen) {
            if (m.getFromId().equals(friendId.getGithub()) && m.getToId().equals(myId.getGithub())) {
                messages.add(m);
            }
        }

        ArrayList<Message> copy = (ArrayList<Message>) messages.stream()
                .sorted(Comparator.comparing(Message::getTimestamp)).collect(Collectors.toList());
        ArrayList<Message> mostRecent = new ArrayList<>();
        if (copy.size() >= 20) {
            for (int i = copy.size()-1; i > copy.size()-21; i--) {
                mostRecent.add(copy.get(i));
            }
        } else {
            for (Message m : copy) {
                mostRecent.add(m);
            }
        }
        return mostRecent;
    }

    public void postMessage( Message m, String sourceidUrl) throws JsonProcessingException {
        serverController.messagePost(m, sourceidUrl);
    }


    public void update() throws JsonProcessingException {
        String messageString = serverController.messagesGet().toString();
        ObjectMapper objectMapper = new ObjectMapper();
        messagesSeen = objectMapper.readValue(messageString, new TypeReference<>() {});
    }



}