package lesson2.extra2;

/**
 * Example of Executor and ThreadFactory implementations.
 */
public class Main {

    public static void main(String[] args) {

        MyExecutor executor = new MyExecutor(4);

        for (int i=0; i<50; i++) {
            executor.execute(new MyTask("Task " + i));
        }

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.interrupt();
    }

    public static class MyTask implements Runnable {

        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is executing " + name);
        }
    }
}
