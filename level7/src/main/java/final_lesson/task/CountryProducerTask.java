package final_lesson.task;

import final_lesson.service.ApiConsumer;
import final_lesson.model.QueueMessage;
import final_lesson.model.Country;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class CountryProducerTask implements Runnable {

    private int id;
    private ApiConsumer apiConsumer;
    private List<String> countryCodes;
    private BlockingQueue<QueueMessage> queue;
    private AtomicInteger countDown;

    public CountryProducerTask(int id, ApiConsumer apiConsumer, List<String> countryCodes,
                               BlockingQueue<QueueMessage> queue, AtomicInteger countDown) {
        this.id = id;
        this.apiConsumer = apiConsumer;
        this.countryCodes = countryCodes;
        this.queue = queue;
        this.countDown = countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Country Producer Task " + id);
        countryCodes.forEach(code -> {
            Country country = apiConsumer.getCountry(code);
            QueueMessage message = QueueMessage.newMessage(country);
            queue.add(message);
        });
        if (countDown.decrementAndGet() == 0) {
            queue.add(QueueMessage.newEndMessage());
        }
    }
}
