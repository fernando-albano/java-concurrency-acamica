package lesson2.example1;

import java.util.Random;
import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {

	private final static int MAX_EMPLOYEES = 200; 

	private int toCheck;
	private String department;
	private EmployeesCacheSync cache;
	
	public Task(int toCheck, String department, EmployeesCacheSync cache) {
		this.toCheck = toCheck;
		this.department = department;
		this.cache = cache;
	}
	
	@Override
	public Integer call() {
		Random random = new Random();
		int count = 0;
		for (int i=0; i<toCheck; i++) {
			Employee employee = cache.getEmployee(random.nextInt(MAX_EMPLOYEES));
			if (department.equals(employee.getDepartment())) {
				count++;
			}
		}
		return count;
	}
}
