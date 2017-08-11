package final_lesson.service;

import final_lesson.model.QueueMessage;
import final_lesson.task.CountryProducerTask;
import final_lesson.task.ReductionTask;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskManager {

    private int producerTaskNumber;
    private ApiConsumer apiConsumer;
    private ExecutorService executor;
    private BlockingQueue<QueueMessage> queue;
    private List<String> countryCodes;

    public TaskManager(int producerTaskNumber) throws Exception {
        this.producerTaskNumber = producerTaskNumber;
        this.apiConsumer = new ApiConsumer();
        this.executor = Executors.newFixedThreadPool(producerTaskNumber + 1);
        this.queue = new LinkedBlockingQueue<>();
        this.countryCodes = Files.readAllLines(Paths.get("src\\main\\resource\\countries.txt"));
    }

    public int totalCountries() {
        return countryCodes.size();
    }

    public Map<String, Long> runTasks() throws ExecutionException, InterruptedException {
        AtomicInteger countDown = new AtomicInteger(producerTaskNumber);
        Map<Integer, List<String>> lists = splitCountriesList(countryCodes);

        for (Map.Entry<Integer, List<String>> entry : lists.entrySet()) {
            CountryProducerTask task = new CountryProducerTask(entry.getKey(),
                apiConsumer, entry.getValue(), queue, countDown);
            executor.execute(task);
        }

        ReductionTask reductionTask = new ReductionTask(queue);
        Map<String, Long> population = executor.submit(reductionTask).get();
        executor.shutdown();
        return population;
    }

    private Map<Integer, List<String>> splitCountriesList(List<String> countries) {
        Map<Integer, List<String>> lists = new HashMap<>();
        for (int i=0; i<producerTaskNumber; i++) {
            lists.put(i, new ArrayList<>());
        }
        for (int i=0; i<countries.size(); i++) {
            lists.get(i % producerTaskNumber).add(countries.get(i));
        }
        return lists;
    }
}
