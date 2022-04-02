package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.json.simple.JSONArray;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MessageControllerTest extends TestCase {
    ServerController serverController = ServerController.shared();

    public void testGetMessages() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        JSONArray messages = ServerController.shared().messagesGet();
//        ArrayList<Object> messageStr = new ArrayList<Object>(messages);
//        HashMap<String, String> map = objectMapper.readValue(messageStr, Map.class);
//        System.out.println(map.size());
//        Logger.getLogger(String.valueOf(messageStr));
    }

    public void testGetMessagesForId() {
    }

    public void testGetMessageForSequence() {
    }

    public void testGetMessagesFromFriend() {
    }

    public void testPostMessage() {
    }
}