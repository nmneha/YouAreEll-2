package youareell;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.*;
import models.Id;
import models.Message;
import views.IdTextView;
import views.MessageTextView;

import java.io.IOException;
import java.util.List;

public class YouAreEll {

    TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public YouAreEll(MessageController messageController, IdController idController) {
        this.tt = new TransactionController(messageController, idController);
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(
                new MessageController(), new IdController()
        ));
        System.out.println(urlhandler.MakeURLCall("/ids")); //, "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages")); //, "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/post"));
    }

    private String MakeURLCall(String source) throws IOException, InterruptedException {
        if(source.equals("/ids")) {
            return get_ids();
        } else if (source.equals("messages")) {
            return get_messages();
        } else {
            return post_Ids();
        }
    }

    public String get_ids() throws IOException, InterruptedException {
        List<Id> ids = tt.getIds();
        String show = "";
        for (Id i : ids) {
            IdTextView view = new IdTextView(i);
            show += view + "\n";
        }
        return show;
    }

    public String get_messages() throws IOException, InterruptedException {
        List<Message> messages = tt.getMessages();
        String show = "";
        for (Message m : messages) {
            MessageTextView view = new MessageTextView();
            show += view.toString(m) + "\n";
        }
        return show;
    }

    private String post_Ids() throws IOException {
        IdController idController = new IdController();
        idController.postId(new Id());
        return "New Id Created";
//        return MakeURLCall("/post", "GET", "");
    }

}
