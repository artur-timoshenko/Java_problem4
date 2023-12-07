
public class ThreadHierarchy implements Runnable {

    private static void printThreadInfo(Thread t, String indent) {
        if (t == null) return;
        System.out.println((indent + "Thread: " + t.getName() +
                "  Priority: " + t.getPriority() +
                (t.isDaemon() ? " Daemon" : "") +
                (t.isAlive() ? "" : " Not Alive")));
    }

    // Display info about a thread group and its threads and groups
    private static void listGroup(ThreadGroup g, String indent) {
        if (g == null) return;
        int numThreads = g.activeCount();
        int numGroups = g.activeGroupCount();

        Thread[] threads = new Thread[numThreads];
        ThreadGroup[] groups = new ThreadGroup[numGroups];

        g.enumerate(threads, false);
        g.enumerate(groups, false);

        System.out.println((indent + "Thread Group: " + g.getName() +
                "  Max Priority: " + g.getMaxPriority() +
                (g.isDaemon() ? " Daemon" : "")));

        for (int i = 0; i < numThreads; i++)
            printThreadInfo(threads[i], indent + "    ");
        for (int i = 0; i < numGroups; i++)
            listGroup(groups[i], indent + "    ");
    }

    @Override
    public void run() {
        ThreadGroup testThreadGroup = new ThreadGroup("SimpleThreadGroup");
        ThreadGroup testThreadSubGropup = new ThreadGroup(testThreadGroup, "SimpleThreadSubGroup");
        for (int i = 0; i < 50; i++) {
            if (i % 5 == 0) {
                new Thread(testThreadSubGropup, new SimpleThread()).start();
            } else {
                new Thread(testThreadGroup, new SimpleThread()).start();
            }
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
            }
            listGroup(testThreadGroup, "");
        }
    }
}
