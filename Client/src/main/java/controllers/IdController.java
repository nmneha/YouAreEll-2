package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;
import org.json.simple.JSONArray;

public class IdController {
    public HashMap<String, Id> allIds = new HashMap<>();

    ServerController serverController = ServerController.shared();

    public IdController() {}

    public ArrayList<Id> getIds() throws IOException, InterruptedException {
        String getIds = String.valueOf(serverController.idGet());
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Id> ids = objectMapper.readValue(getIds, new TypeReference<>() {});
        for (Id i : ids) {
            allIds.put(i.getGithub(), i);
        }
        return ids;
    }

    public Id postId(Id id) {
        id = new Id("blossom", "bubbles");
        serverController.idPost(id);
        // create json from id
        // call server, get json result Or error
        // result json to Id obj

        return null;
    }

    public Id putId(Id id) {
        return null;
    }
 
}