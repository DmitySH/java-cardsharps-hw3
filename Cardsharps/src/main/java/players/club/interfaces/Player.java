package players.club.interfaces;

public interface Player extends Runnable {
    void playGame();
    String getName();
    int getBalance();
}
