package final_lesson.task;

import final_lesson.model.QueueMessage;
import final_lesson.model.Country;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class ReductionTask implements Callable<Map<String, Long>> {

    private BlockingQueue<QueueMessage> queue;
    private Map<String, Long> population = new HashMap<>();

    public ReductionTask(BlockingQueue<QueueMessage> queue) {
        this.queue = queue;
    }

    @Override
    public Map<String, Long> call() {
        Thread.currentThread().setName("Reduction Task");
        try {
            QueueMessage message = queue.take();
            while(!message.isEndMessage()) {
                addCountry(message.getCountry());
                message = queue.take();
            }
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
        return population;
    }

    private void addCountry(Country country) {
        population.compute(country.getRegion(), 
            (k,v) -> country.getPopulation() + (v != null ? v : 0));
    }
}
