package org.thread.creation.policeThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadFactory;

public class Main {
    public static final int MAX_PASSWORD = 9999;
    public static void main(String[] args) {
        Random random = new Random();
        Valt valt = new Valt(random.nextInt(MAX_PASSWORD));

        List<Thread> threads = new ArrayList<>();

        threads.add(new AccendingHackerThread(valt));
        threads.add(new DescendingHackerThread(valt));
        threads.add(new PoliceThread());

        for (Thread thread : threads){
            thread.start();
        }
    }
    private static class Valt{
        private int password;

        public Valt(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return this.password == guess;
        }
    }
    private static abstract class HackerThread extends Thread{
            protected Valt valt;

            public HackerThread(Valt valt){
                this.valt=valt;
                this.setName(this.getClass().getSimpleName());
                this.setPriority(Thread.MAX_PRIORITY);
            }
            @Override
            public void start(){
                System.out.println("start thread "+ this.getName());
                super.start();
            }
    }
    private static class AccendingHackerThread extends HackerThread{
        public AccendingHackerThread(Valt valt){
            super(valt);
        }

        @Override
        public void run(){
            for (int guess = 0 ; guess<MAX_PASSWORD;guess++){
                if (valt.isCorrectPassword(guess)){
                    System.out.println(this.getName()+ "guessed the password "+ guess);
                    System.exit(0);
                }
            }
        }
    }
    private static class DescendingHackerThread extends HackerThread{

        public DescendingHackerThread(Valt valt) {
            super(valt);
        }
        @Override
        public void run(){
            for (int guess = MAX_PASSWORD; guess >=0;guess--){
                if (valt.isCorrectPassword(guess)){
                    System.out.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread{
        @Override
        public void run(){
            for (int i = 10;i>0;i--){
                try {
                    Thread.sleep(1000);
                    System.out.println(i);
                }catch (InterruptedException e){
                    System.out.println(i);
                }
            }
            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }
}