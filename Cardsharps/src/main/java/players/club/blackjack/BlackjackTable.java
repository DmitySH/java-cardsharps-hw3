package players.club.blackjack;

import players.club.interfaces.Deck;
import players.club.interfaces.GamblingTable;
import players.club.interfaces.Player;
import players.club.players.Cardsharp;
import players.club.players.Gambler;

import java.util.ArrayList;
import java.util.List;

public final class BlackjackTable implements GamblingTable {
    private static final int GAME_TIME = 5_000; //todo: to 10sec

    private final List<Player> players;
    private final Deck deck;

    public static void main(String[] args) {
        GamblingTable table = new BlackjackTable();
        table.playCardGame();
    }


    public BlackjackTable() {
        players = new ArrayList<>();
        deck = new BlackjackDeck();

        for (int i = 0; i < 2; ++i) {
            players.add(new Gambler(deck, "" + (i + 1)));
        }

        for (int i = 0; i < 2; ++i) {
            players.add(new Cardsharp(deck, "" + (i + 3)));
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

        for (Player player : players) {
            System.out.println(player);
            if (player instanceof Gambler gambler) {
                System.out.println(gambler.got +"  " + gambler.lost);
            }
        }
    }

    private void playBlackjack() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (Player player : players) {
            Thread thread = new Thread(player, "Player" + player.getName());
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
