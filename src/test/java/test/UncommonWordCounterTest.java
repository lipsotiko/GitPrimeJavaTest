package test;

import com.gitprime.test.UncommonWordCounter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UncommonWordCounterTest {

    @Test
    void returns_a_count_of_only_the_uncommon_words() {
        UncommonWordCounter uncommonWordCounter = new UncommonWordCounter(new FakeCommonWordFinder());
        String sampleText = "vango is cool but not always \n bikes!    are--always cool, because it's not one Bike note-book";
        Map<String, Integer> results = uncommonWordCounter.evaluate(sampleText);

        assertTrue(results.get("vango") == null);
        assertTrue(results.get("bike") == null);

        assertTrue(results.get("are") == 1);
        assertTrue(results.get("bikes") == 1);
        assertTrue(results.get("but") == 1);
        assertTrue(results.get("because") == 1);
        assertTrue(results.get("is") == 1);
        assertTrue(results.get("its") == 1);
        assertTrue(results.get("one") == 1);
        assertTrue(results.get("always") == 2);
        assertTrue(results.get("cool") == 2);
        assertTrue(results.get("not") == 2);

        assertTrue(results.get("note") == 1);
        assertTrue(results.get("book") == 1);
    }

    @Test
    void integration() throws IOException {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        UncommonWordCounter.main("../resources/common.txt", "../resources/alice.txt");

        assertTrue(outContent.toString().contains("abide             1"));
        assertTrue(outContent.toString().contains("zigzag            1"));
        System.setOut(originalOut);
    }

}