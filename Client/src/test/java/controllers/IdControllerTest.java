package controllers;

import junit.framework.TestCase;
import models.Id;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;

public class IdControllerTest extends TestCase {
    IdController id = new IdController();

    public void testGetIds() throws IOException, InterruptedException {
        ArrayList<Id> ids = id.getIds();
        Assert.assertTrue(ids.size() != 0);
    }


    public void testPostId() throws IOException, InterruptedException {
        ArrayList<Id> before = id.getIds();
        Id redVel = new Id("red", "velvet");
        id.postId(redVel);
        ArrayList<Id> after = id.getIds();
        Assert.assertTrue(before.size() != after.size());

    }

    public void testPutId() {
    }
}