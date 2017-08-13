package final_lesson;

import final_lesson.service.TaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Example of parallel application.
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static int PRODUCER_TASKS = 15;

    public static void main(String[] args) {
        try {
            TaskManager taskManager = new TaskManager(PRODUCER_TASKS);
            long time = System.currentTimeMillis();

            Map<String, Long> population = taskManager.runTasks();
            time = System.currentTimeMillis() - time;

            logger.info("Execution time: {} ms", time);
            logger.info("Total countries: {}", taskManager.totalCountries());
            logger.info("Population per region:");
            population.forEach((k, v) -> {
                if (k != null && !k.isEmpty()) {
                    logger.info("  {}: {}", k, v);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
