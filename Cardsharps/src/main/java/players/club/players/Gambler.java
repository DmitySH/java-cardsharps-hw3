package players.club.players;

import players.club.interfaces.Deck;

import java.util.concurrent.ThreadLocalRandom;

public final class Gambler implements Runnable {
    private static final int MAX_SLEEP_TIME = 200;
    private static final int MIN_SLEEP_TIME = 100;

    private final Deck deck;
    private int balance;
    private String name;

    public Gambler(Deck deck) {
        this.deck = deck;
        balance = 0;
        name = "";
    }

    public Gambler(Deck deck, String name) {
        this(deck);
        this.name = name;
    }

    public synchronized void takeCards() {
        Thread current = Thread.currentThread();
        while (!current.isInterrupted()) {
            try {
                int added = deck.getNextCard();
                System.out.println(current.getName() + " got " + added);
                balance += added;
                Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_SLEEP_TIME,
                        MAX_SLEEP_TIME + 1));
            } catch (InterruptedException ex) {
                System.out.printf("%s was interrupted!%n", current.getName());
                break;
            }
        }
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void run() {
        takeCards();
    }

    @Override
    public String toString() {
        return "Gambler " + getName() + " has " + getBalance();
    }
}
