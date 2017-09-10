package lesson2.example1;

import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {

	private Integer id;
	private Printer printer;
	
	public Task(Integer id, Printer printer) {
		this.id = id;
		this.printer = printer;
	}
	
	@Override
	public Integer call() {
		printer.print("Task " + id);
		printer.print("Task " + id);
		printer.print("Task " + id);
		return id;
	}
}
