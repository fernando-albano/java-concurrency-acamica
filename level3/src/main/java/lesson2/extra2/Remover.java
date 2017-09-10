package lesson2.extra2;

public class Remover implements Runnable {

    private SyncQueue<String> queue;
    private int removedElements;

    public Remover(SyncQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(true) {
                if (queue.dequeue() != null) {
                    removedElements++;
                }
                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted. Removed: " + removedElements);
        }
    }
}
