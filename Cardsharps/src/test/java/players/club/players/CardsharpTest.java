package players.club.players;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import players.club.blackjack.BlackjackDeck;

import static org.junit.jupiter.api.Assertions.*;

class CardsharpTest {
    private Cardsharp sharper;
    private Gambler gambler;

    @BeforeEach
    void init() {
        BlackjackDeck deck = new BlackjackDeck();
        sharper = new Cardsharp(deck, "Sharpodel");
        gambler = new Gambler(deck, "Fair Java guy");
    }

    @Test
    void playGame() {
        Thread threadSharper = new Thread(sharper);
        threadSharper.start();
        assertDoesNotThrow(()->Thread.sleep(2000));
        threadSharper.interrupt();
        assertDoesNotThrow((Executable) threadSharper::join);
    }
}