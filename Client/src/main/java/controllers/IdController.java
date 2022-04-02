package controllers;

import java.util.ArrayList;
import java.util.HashMap;

import models.Id;
import org.json.simple.JSONArray;

public class IdController {
    private HashMap<String, Id> allIds;

    Id myId;

    public ArrayList<Id> getIds() {
        ArrayList<Id> ids = new ArrayList<>();
        ServerController serverController = ServerController.shared();
        ids = serverController.idGet();
        return null;
    }

    public Id postId(Id id) {
        // create json from id
        // call server, get json result Or error
        // result json to Id obj

        return null;
    }

    public Id putId(Id id) {
        return null;
    }
 
}