package lesson2.extra2;

public class Counter implements Runnable {

    private SyncQueue<String> queue;
    private int countedElements;

    public Counter(SyncQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String lastElement = "";
            while(true) {
                String element = queue.head();
                if (element != null && !lastElement.equals(element)) {
                    countedElements++;
                    lastElement = element;
                }
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted. Counted: " + countedElements);
        }
    }
}
