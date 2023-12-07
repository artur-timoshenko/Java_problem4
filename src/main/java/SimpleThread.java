class SimpleThread extends Thread {

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(false);
    }
}

