package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.Id;
import models.Message;

import java.io.IOException;
import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    public TransactionController(MessageController m, IdController j) {
        this.idCtrl = j;
        this.msgCtrl = m;
    }

    public List<Id> getIds() throws IOException, InterruptedException {
        return idCtrl.getIds();
    }

    public List<Message> getMessages() throws IOException, InterruptedException {
        return msgCtrl.getMessages();
    }


//    public String postId(String idtoRegister, String githubName) {
//        Id tid = new Id(idtoRegister, githubName);
//        tid = idCtrl.postId(tid);
//        return ("Id registered.");
//    }
}
