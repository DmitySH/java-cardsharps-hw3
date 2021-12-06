package players.club.blackjack;

import players.club.interfaces.Deck;
import players.club.interfaces.GamblingTable;
import players.club.players.Gambler;

import java.util.ArrayList;
import java.util.List;

public final class BlackjackTable implements GamblingTable {
    private static final int GAME_TIME = 10_000;

    private final List<Gambler> players;
    private final Deck deck;

    public static void main(String[] args) {
        GamblingTable table = new BlackjackTable();
        table.playCardGame();
    }


    public BlackjackTable() {
        players = new ArrayList<>();
        deck = new BlackjackDeck();

        for (int i = 0; i < 3; ++i) {
            players.add(new Gambler(deck, "" + (i + 1)));
        }
    }

    @Override
    public void playCardGame() {
        System.out.println("Blackjack started");
        try {
            playBlackjack();
        } catch (InterruptedException ex) {
            System.out.println("Game was interrupted.");
        }

        for (Gambler player : players) {
            System.out.println(player);
        }
    }

    private void playBlackjack() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (Gambler player : players) {
            Thread thread = new Thread(player);
            thread.setName("Player" + player.getName());
            threads.add(thread);
            thread.start();
        }
        Thread.sleep(GAME_TIME);

        for (Thread thread : threads) {
            thread.interrupt();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
