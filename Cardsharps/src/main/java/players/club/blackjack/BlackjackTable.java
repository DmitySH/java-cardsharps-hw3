package players.club.blackjack;

import players.club.interfaces.Deck;
import players.club.interfaces.GamblingTable;
import players.club.interfaces.Input;
import players.club.interfaces.Player;
import players.club.players.Cardsharp;
import players.club.players.Gambler;
import players.club.utils.InputHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class BlackjackTable implements GamblingTable {
    private static final int GAME_TIME = 10_000;
    private static final int MAX_INPUT_ATTEMPTS = 7;
    private static final int MAX_EACH_TYPE_PLAYERS = 500;

    private final List<Player> players;
    private final Deck deck;

    private static final Input inputHelper;

    /**
     * Entry point.
     *
     * @param args CL args.
     */
    public static void main(String[] args) {
        int gamblerCount;
        int cardsharpCount;

        try {
            gamblerCount = inputHelper.parseInt(1, MAX_EACH_TYPE_PLAYERS,
                    "Enter number of fair players: ",
                    "Incorrect number! Try again: ", MAX_INPUT_ATTEMPTS);
            cardsharpCount = inputHelper.parseInt(0, MAX_EACH_TYPE_PLAYERS,
                    "Enter number of cardsharps: ",
                    "Incorrect number! Try again: ", MAX_INPUT_ATTEMPTS);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage() + " Restart application!");
            return;
        }

        GamblingTable table = new BlackjackTable(gamblerCount, cardsharpCount);
        table.playCardGame();
    }

    static {
        inputHelper = InputHelper.getInstance();
    }

    public BlackjackTable(int gamblerCount, int cardsharpCount) {
        players = new ArrayList<>();
        deck = new BlackjackDeck();

        for (int i = 0; i < gamblerCount; ++i) {
            players.add(new Gambler(deck, "Gambler_" + (i + 1)));
        }

        for (int i = 0; i < cardsharpCount; ++i) {
            players.add(new Cardsharp(deck, "Cardsharp_" + (i + 1)));
        }
    }

    /**
     * Logic of game.
     */
    @Override
    public void playCardGame() {
        System.out.println("Blackjack started");

        try {
            playBlackjack();
        } catch (InterruptedException ex) {
            System.out.println("Game was interrupted.");
        }

        players.sort(Comparator.comparingInt(Player::getBalance));

        int maxBalance = Collections.max(players, Comparator.comparingInt(Player::getBalance)).getBalance();

        System.out.println("\tResults of the game:");
        players.stream().filter(player -> player.getBalance() < maxBalance).
                forEach(System.out::println);

        System.out.println(System.lineSeparator() + "\t\t WINNERS");

        players.stream().filter(player -> player.getBalance() == maxBalance).
                forEach(x -> System.out.println("\t" + x));

        System.out.println();
    }

    /**
     * BlackJack game logic.
     *
     * @throws InterruptedException if game was interrupted.
     */
    private void playBlackjack() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (Player player : players) {
            Thread thread = new Thread(player, player.getName());
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

        System.out.println();
    }
}
