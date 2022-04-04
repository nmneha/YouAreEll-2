package controllers;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import spiffyUrlManipulator; TODO - figure out what to import

import javax.swing.text.html.parser.Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

public class ServerController<JsonString> {
    private String rootURL = "http://zipcode.rocks:8085";
    HttpURLConnection connection;


    private static ServerController svr = new ServerController();

    private ServerController() {
    }

    public static ServerController shared() {
        return svr;
    }

    public JSONArray idGet() throws IOException, InterruptedException {
        BufferedReader reader;
        JSONParser jsonParser = new JSONParser();
        JSONArray ids = null;
        try {
            URL url = new URL(rootURL + "/ids");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
//            System.out.println(status);

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                Object object = jsonParser.parse(reader);
                ids = (JSONArray) object;
//                System.out.println(ids);
                reader.close();
            }
        } catch (MalformedURLException u) {
            u.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return ids;
//             url -> /ids/
//             send the server a get with url
//             return json from server
    }


//    public JsonString idPost(JsonTypeInfo.Id) {
//        // url -> /ids/
//        // create json from Id
//        // request
//        // reply
//        // return json
//    }
//    public JsonString idPut(JsonTypeInfo.Id) {
//        // url -> /ids/
//    }

    public JSONArray messagesGet() {
        BufferedReader reader;
        JSONParser jsonParser = new JSONParser();
        JSONArray message = null;
        try {
            URL url = new URL(rootURL + "/messages");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
//            System.out.println(status);

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                Object object = jsonParser.parse(reader);
                message = (JSONArray) object;
//                System.out.println(message);
                reader.close();
            }

        } catch (MalformedURLException u) {
            u.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return message;
        // url -> /ids/
        // send the server a get with url
        // return json from server
    }


    public JsonString idPost(Id id) throws IOException {
        StringBuilder response = null;
        try {
            URL url = new URL(rootURL + "/messages");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("Id", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            ObjectMapper mapper = new ObjectMapper();
            String out = mapper.writeValueAsString(id);
            OutputStream stream = connection.getOutputStream();
            byte[] input = out.getBytes(StandardCharsets.UTF_8);
            stream.write(input, 0, input.length);
            int status = connection.getResponseCode();
            System.out.println(status);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return (JsonString) response;
    }
}

// ServerController.shared.doGet()