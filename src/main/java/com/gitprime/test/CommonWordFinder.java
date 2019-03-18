package com.gitprime.test;

import java.util.Arrays;
import java.util.List;

public class CommonWordFinder implements WordFinder {

    private List<String> wordsList;

    public CommonWordFinder(String... words) {
        wordsList = Arrays.asList(words);
    }

    public boolean isCommon(String word) {
        return wordsList.stream().anyMatch(x -> x.equalsIgnoreCase(word));
    }

}
