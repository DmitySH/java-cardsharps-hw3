package players.club.interfaces;

public interface Player extends Runnable {
    /**
     * Provides playing logic for player.
     */
    void playGame();

    /**
     * Name getter.
     *
     * @return name of player.
     */
    String getName();

    /**
     * Balance getter.
     *
     * @return balance of player.
     */
    int getBalance();
}
