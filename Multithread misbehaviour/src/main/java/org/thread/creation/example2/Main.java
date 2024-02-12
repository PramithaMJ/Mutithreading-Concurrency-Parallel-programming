package org.thread.creation.example2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new NewThread();
        thread.start();
    }

    private static class NewThread extends Thread{
        @Override
        public void run(){
            // Code that  will run in a new thread
            System.out.println("Helo from "+ Thread.currentThread().getName());
        }
    }
}