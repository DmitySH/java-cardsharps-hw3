package players.club.players;

import players.club.interfaces.Deck;
import players.club.interfaces.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Gambler implements Player {
    private static final int MAX_SLEEP_TIME = 200;
    private static final int MIN_SLEEP_TIME = 100;

    protected final Deck deck;
    protected int balance;
    protected String name;

    protected static final List<Gambler> fairPlayers;

    //todo: remove
    public int got = 0;
    public int lost = 0;

    static {
        fairPlayers = new ArrayList<>();
    }



    public Gambler(Deck deck) {
        this.deck = deck;
        balance = 0;
        name = "";
        if (!(this instanceof Cardsharp)){
            fairPlayers.add(this);
        }
    }

    public Gambler(Deck deck, String name) {
        this(deck);
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run() {
        makeMove();
    }

    @Override
    public String toString() {
        return "Gambler " + getName() + " has " + getBalance();
    }

    @Override
    public void makeMove() {
        Thread current = Thread.currentThread();
        while (!current.isInterrupted()) {
            try {
                takeCard(current);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }

    protected void takeCard(Thread thread) throws InterruptedException {
        try {
            int added = deck.getNextCard();
            System.out.println(thread.getName() + " got " + added);
            balance += added;
            got += added;
            Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_SLEEP_TIME,
                    MAX_SLEEP_TIME + 1));
        } catch (InterruptedException ex) {
            System.out.printf("%s was interrupted!%n", thread.getName());
            throw ex;
        }
    }
}
