package lesson2.extra2;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

    private ThreadGroup threadGroup;
    private String threadNamePrefix;
    private int count = 0;

    public MyThreadFactory(String threadGroupName, String threadNamePrefix) {
        this.threadGroup = new ThreadGroup(threadGroupName);
        this.threadNamePrefix = threadNamePrefix;
    }

    @Override
    public Thread newThread(Runnable task) {
        Thread thread = new Thread(threadGroup, task, threadNamePrefix + (count++));
        thread.setPriority(Thread.NORM_PRIORITY);
        return thread;
    }

    public ThreadGroup getThreadGroup() {
        return threadGroup;
    }
}

