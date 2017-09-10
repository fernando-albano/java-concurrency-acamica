package lesson2.extra1;

public class Employee {

    private int id;
    private String department;
    private boolean deleted;

    public Employee(int id, String department, boolean deleted) {
        this.id = id;
        this.department = department;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
