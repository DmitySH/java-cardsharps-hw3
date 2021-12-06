package players.club.players;

import players.club.interfaces.Deck;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Cardsharp extends Gambler {
    private static final int MAX_SLEEP_TIME = 300;
    private static final int MIN_SLEEP_TIME = 180;

    public Cardsharp(Deck deck) {
        super(deck);
    }

    public Cardsharp(Deck deck, String name) {
        super(deck, name);
    }

    @Override
    public void makeMove() {
        Thread current = Thread.currentThread();
        while (!current.isInterrupted()) {
            try {
                if (ThreadLocalRandom.current().nextInt(0, 11) <= 3) {
                    System.out.println(Gambler.fairPlayers);
                } else {
                    super.takeCard(current);
                }
            } catch (InterruptedException ex) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Cardsharper " + getName() + " has " + getBalance();
    }
}
