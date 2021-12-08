package players.club.interfaces;

public interface Player extends Runnable {
    void makeMove();
    String getName();
    int getBalance();
}
