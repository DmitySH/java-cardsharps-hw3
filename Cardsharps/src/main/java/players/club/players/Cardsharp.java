package players.club.players;

import players.club.interfaces.Deck;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Cardsharp extends Gambler {
    private static final int MAX_SLEEP_TIME = 300;
    private static final int MIN_SLEEP_TIME = 180;
    private static final int MAX_RANDOM_NUMBER = 10;
    private static final int MIN_RANDOM_NUMBER = 0;
    private static final int MIN_STOLEN_SCORE = 0;
    private static final int MAX_STOLEN_SCORE = 8;


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
                if (ThreadLocalRandom.current().nextInt(MIN_RANDOM_NUMBER,
                        MAX_RANDOM_NUMBER + 1) <= 3) {
                    stoleCards(current);
                } else {
                    super.takeCard(current);
                }
            } catch (InterruptedException ex) {
                break;
            }
        }
    }

    private void stoleCards(Thread thread) throws InterruptedException {
        try {
            int randGamblerIndex = ThreadLocalRandom.current().nextInt(0,
                    Gambler.fairPlayers.size());
            Gambler victim = fairPlayers.get(randGamblerIndex);

            int stolen = ThreadLocalRandom.current().nextInt(MIN_STOLEN_SCORE,
                    MAX_STOLEN_SCORE + 1);

            if (victim.balance - stolen < 0) {
                stolen = victim.balance;
                victim.balance = 0;
            } else {
                victim.balance -= stolen;
            }

            this.balance += stolen;
            victim.lost += stolen;
            this.lost += stolen;

            System.out.println(thread.getName() + " stole " + stolen + " from " + victim.getName());
            Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_SLEEP_TIME,
                    MAX_SLEEP_TIME + 1));
        } catch (InterruptedException ex) {
            System.out.printf("%s was interrupted!%n", thread.getName());
            throw ex;
        }
    }
}
