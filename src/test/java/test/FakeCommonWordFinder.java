package test;

import com.gitprime.test.WordFinder;

public class FakeCommonWordFinder implements WordFinder {
    @Override
    public boolean isCommon(String word) {
        return word.equals("vango") || word.equals("bike");
    }
}
