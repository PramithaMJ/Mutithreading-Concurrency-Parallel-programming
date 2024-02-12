package org.thread.creation.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Code that  will run in a new thread
                System.out.println("We are now in thread" + Thread.currentThread().getName());
                System.out.println("Current thread priority is "+ Thread.currentThread().getPriority());
            }
        });
        thread.setName(" New Working Thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("Which are in thread: "+ Thread.currentThread().getName() +" before starting a new thread");
        thread.start();
        System.out.println("Which are in thread: "+ Thread.currentThread().getName() +" after starting a new thread");

        Thread.sleep(10000);
    }
}