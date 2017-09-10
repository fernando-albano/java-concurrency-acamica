package lesson2.example1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;

public class EmployeesCacheSync {

	private Map<Integer, Employee> cache;
	private ThreadPoolExecutor executor;
	private String[] departments;
	
	public EmployeesCacheSync(ThreadPoolExecutor executor, String[] departments) {
		this.cache = Collections.synchronizedMap(new HashMap<>());
		this.executor = executor;
		this.departments = departments;
	}
	
	public Employee getEmployee(Integer id) {
		return cache.computeIfAbsent(id, this::computeEmployee);
	}
	
	public int size() {
		return cache.size();
	}
	
	private Employee computeEmployee(Integer id) {
		try {
			return executor.submit(() -> {
				int i = ThreadLocalRandom.current().nextInt(departments.length);
				Thread.sleep(10);
				return new Employee(id, departments[i], false);
			}).get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Failed to compute employee " + id);
			return null;
		}
	}
}
