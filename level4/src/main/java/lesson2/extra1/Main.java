package lesson2.extra1;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Example of Synchronized Collection usage.
 */
public class Main {

    private static final int EMPLOYEES = 500;
    private static String[] DEPARTMENTS = { "Administration", "Logistics", "Marketing", "Service Desk", "Legal" };

    public static void main(String[] args) {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(DEPARTMENTS.length);
        Random random = new Random();

        Collection<Employee> employees = new ArrayList<>();
        for (int i=0; i<EMPLOYEES; i++) {
            employees.add(new Employee(i, DEPARTMENTS[random.nextInt(5)], random.nextBoolean()));
        }
        employees = Collections.synchronizedCollection(employees);

        List<Future<Integer>> results = new ArrayList<>(DEPARTMENTS.length);
        for (int i=0; i<DEPARTMENTS.length; i++) {
            results.add(executor.submit(new Task(DEPARTMENTS[i], employees)));
        }

        try {
            int j = 0;
            for (Future<Integer> result : results) {
                j += result.get();
            }
            System.out.println("Total employees removed: " + j);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
