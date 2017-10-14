import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by VBelov on 13.10.2017.
 */
public class SMTPServer implements Callable {

    private void sendMessage(Message message) {
        System.out.println("SMTP server Отправляю письмецо " + message.getAuthor() + " " + message.getMessage());
    }

    @Override
    public String call() throws Exception {
        try {
            if ((MessagesService.getMessage() != null)) {
                assert MessagesService.getMessage() != null;
                synchronized (MessagesService.getMessage()) {
//                    TimeUnit.SECONDS.sleep(random.nextInt(2));
                    Thread.sleep(5000);
                    sendMessage(MessagesService.getMessage());
                    MessagesService.remove();
                }
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed";

    }
}
