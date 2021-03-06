package players.club.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

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
        assertThrows(NumberFormatException.class, () -> inputHelper.parseIntFromString(str, 1, 20));
        String anotherStr = "f";
        assertThrows(NumberFormatException.class, () -> inputHelper.parseIntFromString(anotherStr, -100, 100));
    }


    @Test
    void parseInt() {
        String initialString = String.join(System.lineSeparator(), "text", "123", "-12", "12.3", "4");
        InputStream in = new ByteArrayInputStream(initialString.getBytes());

        ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outBytes);

        inputHelper.setIn(in);
        inputHelper.setOut(out);

        int value = inputHelper.parseInt(1, 10, "Enter int: ",
                "Incorrect input!", 10);
        assertEquals(outBytes.toString(), "Enter int: Incorrect input!" +
                "Incorrect input!Incorrect input!Incorrect input!");
        assertEquals(value, 4);


        initialString = String.join(System.lineSeparator(), "text", "123", "-12", "12.3", "4text");
        in = new ByteArrayInputStream(initialString.getBytes());

        inputHelper.setIn(in);
        assertThrows(NumberFormatException.class, () ->
                inputHelper.parseInt(1, 10, "Enter int: ",
                        "Incorrect input!", 3));

    }

    @Test
    void getLine() {
        String initialString = "text" + System.lineSeparator() + "123";
        InputStream in = new ByteArrayInputStream(initialString.getBytes());
        inputHelper.setIn(in);

        assertEquals(inputHelper.getLine(), "text");
    }
}