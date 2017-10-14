import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public List<Message> messages = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        ExecutorService messageSourceService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            messageSourceService.submit(new MessageSource(i, 10));
        }
        ExecutorService service = Executors.newFixedThreadPool(5);

        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (MessagesService.getSize() > 0) {
                try {
                    Future future = service.submit(new SMTPServer());
                    if (future.isDone()) {
                        System.out.println(future.get());
                    } else {
                        System.out.println("Занято");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
