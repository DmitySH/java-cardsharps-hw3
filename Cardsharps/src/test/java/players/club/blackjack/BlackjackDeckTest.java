package players.club.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackDeckTest {

    @Test
    void getNextCard() {
        BlackjackDeck deck = new BlackjackDeck();

        for (int i = 0; i < 1_000_000; ++i) {
            int value = deck.getNextCard();
            assertTrue(value >= 1 && value <= 10);
        }
    }
}