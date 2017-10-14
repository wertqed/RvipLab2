import sun.rmi.server.InactiveGroupException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by VBelov on 13.10.2017.
 */
public class MessagesService {
    private static CopyOnWriteArrayList<Message> messageList =new CopyOnWriteArrayList<>();

    public synchronized static void addMessage(Message message){
        messageList.add(message);
    }

    public synchronized static Message getMessage(){
        if (messageList.size()!=0){
            return messageList.get(0);
        }
        return null;
    }

    public synchronized static void remove(){
        if (messageList.size()!=0){
            messageList.remove(0);
        }
    }

    public synchronized static Integer getSize(){
        return messageList.size();
    }
}
