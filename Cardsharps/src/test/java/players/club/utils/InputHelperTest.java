package players.club.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputHelperTest {
    private InputHelper inputHelper;

    @BeforeEach
    void init() {
        inputHelper = InputHelper.getInstance();
    }

    @Test
    void getInstance() {
        InputHelper second = InputHelper.getInstance();
        assertEquals(second, inputHelper);
    }

    @Test
    void parseIntFromString() {
        String str = "123321";
        assertEquals(inputHelper.parseIntFromString(str, 10000, 200000), 123321);
        assertThrows(NumberFormatException.class,()-> inputHelper.parseIntFromString(str, 1, 20));
        String anotherStr = "f";
        assertThrows(NumberFormatException.class,()-> inputHelper.parseIntFromString(anotherStr, -100, 100));
    }
}