import org.junit.Test;

public class ThreadHierarchyTest {

    @Test
    public void testThreadHierarchy() throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        ThreadHierarchy threadHierarchy = new ThreadHierarchy();


        Thread testThread = new Thread(threadHierarchy);
        testThread.start();


        testThread.join();


        assertSameThreadHierarchy(mainThread);
    }


    private void assertSameThreadHierarchy(Thread thread) {

    }
}
