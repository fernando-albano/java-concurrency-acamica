package lesson2.extra1;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;

public class FixedTask implements Callable<Integer> {

    private String department;
    private Collection<Employee> employees;

    public FixedTask(String department, Collection<Employee> employees) {
        this.department = department;
        this.employees = employees;
    }

    @Override
    public Integer call() {
        int count = 0;
        synchronized(employees) {
            for (Iterator<Employee> it = employees.iterator(); it.hasNext();) {
                Employee e = it.next();
                if (e.isDeleted() && department.equals(e.getDepartment())) {
                    it.remove();
                    count++;
                }
            }
        }
        System.out.println("Removed " + count + " deleted employees from " + department + " department.");
        return count;
    }
}
