package controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import models.Id;
import models.Message;
import org.json.simple.JSONArray;
import org.junit.Assert;

import java.io.DataInput;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MessageControllerTest extends TestCase {

    MessageController messages = new MessageController();
    Id id = new Id("blossom", "bubbles");

    public MessageControllerTest() throws JsonProcessingException {
    }

    public void testGetMessages() throws IOException {
        ArrayList<Message> actual = messages.getMessages();
        Assert.assertTrue(actual.size() != 0);
    }

    public void testGetMessagesForId() throws JsonProcessingException {
        Id id = new Id("blossom", "bubbles");
        ArrayList<Message> actual = messages.getMessagesForId(id);
        Assert.assertTrue(actual.size() == 0);
    }

    public void testGetMessageForSequence() throws JsonProcessingException {
        Message actual = messages.getMessageForSequence("3f88230afa5414d79248030a3131c9d8ae6c6b7c");
        Assert.assertTrue(actual == null);
    }

    public void testGetMessagesFromFriend() throws JsonProcessingException {
        Id friend = new Id("caterpie", "weedle");
        ArrayList<Message> actual = messages.getMessagesFromFriend(id, friend );
        Assert.assertFalse(actual.size() == 0);
    }

    public void testPostMessage() throws JsonProcessingException {
        ArrayList<Message> before = messages.getMessagesForId(id);
        Message m = new Message("bubbles", "velvet", "test3");
        messages.postMessage(m, "bubbles");
        ArrayList<Message> after = messages.getMessagesForId(id);
        Assert.assertEquals(after.get(after.size()-1).getMessage(), "test3");
        after.forEach(System.out::println);

    }
}