import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by VBelov on 13.10.2017.
 */
public class MessageSource implements Runnable {

    private Integer num;
    private Integer numMessages;

    MessageSource(Integer num, Integer numMessages) {
        this.num = num;
        this.numMessages = numMessages;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < numMessages; i++) {
                Random random = new Random();
                TimeUnit.SECONDS.sleep(random.nextInt(10));
                System.out.println("Пришло письмо от потока №" + num.toString());
                MessagesService.addMessage(new Message(num.toString(), "Пишу тебе письмо"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
