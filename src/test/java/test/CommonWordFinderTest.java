package test;

import com.gitprime.test.CommonWordFinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CommonWordFinderTest {

    @Test
    void verify_word_is_common() {
        CommonWordFinder commonWords = new CommonWordFinder("a");
        assertTrue(commonWords.isCommon("a"));
    }

    @Test
    void verify_word_is_NOT_common() {
        CommonWordFinder commonWords = new CommonWordFinder();
        assertFalse(commonWords.isCommon("vango"));
    }
}