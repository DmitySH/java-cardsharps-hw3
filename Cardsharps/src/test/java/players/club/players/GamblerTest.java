package players.club.players;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import players.club.blackjack.BlackjackDeck;
import players.club.interfaces.Deck;

import static org.junit.jupiter.api.Assertions.*;

class GamblerTest {
    private Gambler gambler;

    @BeforeEach
    void init() {
        gambler = new Gambler(new BlackjackDeck(), "Gumbleman");
    }

    @Test
    void getBalance() {
        assertEquals(gambler.getBalance(), 0);
        gambler.balance += 123;
        assertEquals(gambler.getBalance(), 123);
        gambler.balance -= 23;
        assertEquals(gambler.getBalance(), 100);
    }

    @Test
    void getName() {
        assertEquals(gambler.getName(), "Gumbleman");
        gambler.name = "Alex";
        assertEquals(gambler.getName(), "Alex");
    }

    @Test
    void testToString() {
        gambler.balance += 123;
        assertEquals(gambler.toString(), "Gumbleman has 123");
    }

    @Test
    void playGame() {
        Thread threadGambler = new Thread(gambler);
        threadGambler.start();
        assertDoesNotThrow(()->Thread.sleep(2000));
        threadGambler.interrupt();
        assertDoesNotThrow((Executable) threadGambler::join);
    }
}