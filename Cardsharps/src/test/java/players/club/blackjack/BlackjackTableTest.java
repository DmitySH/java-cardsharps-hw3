package players.club.blackjack;

import org.junit.jupiter.api.Test;
import players.club.interfaces.GamblingTable;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackTableTest {

    /*
    Optional async test! Use only if you have Python interpreter and Windows OS.
     */

//    @Test
//    void mainTestAsync() {
//        PrintStream consoleOut = System.out;
//
//        try {
//            PrintStream out = new PrintStream(new FileOutputStream(
//                    "async_test\\files\\java_out.txt"));
//            System.setOut(out);
//            GamblingTable table = new BlackjackTable(123, 321);
//            table.playCardGame();
//
//            ProcessBuilder processBuilder = new ProcessBuilder("python", "async_test\\test_async.py");
//            processBuilder.redirectErrorStream(true);
//            Process process = processBuilder.start();
//
//            BufferedReader stdInput = new BufferedReader(new
//                    InputStreamReader(process.getInputStream()));
//            String str = "";
//            System.setOut(consoleOut);
//            str = stdInput.readLine();
//            while (str != null) {
//                System.out.println(str);
//                String prevStr = str;
//                str = stdInput.readLine();
//                if(str == null){
//                    assertEquals(prevStr, "SUCCESS");
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            System.setOut(consoleOut);
//        }
//    }
}