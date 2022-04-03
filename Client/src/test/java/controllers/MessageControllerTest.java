package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import models.Message;
import org.json.simple.JSONArray;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
        //given
        MessageController messageController = MessageController.shared();
//        String[] array = {"Hello old buddy!", "", "Howdy partner", "Holy'Moley'Macaroni", "Holy Hell",
//                "holyHel", "holyHel", "'it'sforthegreatergood'", "'butImustsendmoremessages'", "'iwannasleep'",
//                "'sendingsendingsending'", "'tiredbutpersisting!'", "'THANKYOU'", "'hi'", "Hello", "Hello",
//                "Message via HttpClient", "Message via HttpClient", "\"yo\"", "a"};
//        ArrayList<String> expected = new ArrayList<>(Arrays.asList(array));
//        System.out.println(expected.size());
//        System.out.println(expected.get(0) + " " + expected.get(1));

        ArrayList<Message> actual = messageController.getMessages();

//        System.out.println(actual.size());
//        System.out.println(actual.get(0));
//        System.out.println(actual.get(1));
//        System.out.println(actual.get(2));
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