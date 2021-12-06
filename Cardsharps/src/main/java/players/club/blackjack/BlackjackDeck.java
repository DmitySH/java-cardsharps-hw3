package players.club.blackjack;

import players.club.interfaces.Deck;

import java.util.concurrent.ThreadLocalRandom;

public final class BlackjackDeck implements Deck {
    private static final int MAX_CARD_VALUE = 10;
    private static final int MIN_CARD_VALUE = 1;

    @Override
    public int getNextCard() {
        return ThreadLocalRandom.current().nextInt(MIN_CARD_VALUE, MAX_CARD_VALUE + 1);
    }
}
