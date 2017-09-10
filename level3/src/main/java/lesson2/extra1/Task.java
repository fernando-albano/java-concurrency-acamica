package lesson2.extra1;

import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {

    private Integer id;
    private Long sleep;
    private Printer printer;

    public Task(Integer id, Long sleep, Printer printer) {
        this.id = id;
        this.sleep = sleep;
        this.printer = printer;
    }

    @Override
    public Integer call() {
        printer.print(sleep, "Task " + id);
        return id;
    }
}
