package players.club.interfaces;

public interface Input {
    /**
     * From string int parser.
     */
    int parseIntFromString(String input, int from, int to);

    /**
     * Int parser.
     */
    int parseInt(int from, int to, String prompt, String errorMessage, int attempts);

    /**
     * Get line.
     */
    String getLine();
}
