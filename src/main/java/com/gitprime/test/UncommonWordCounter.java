package com.gitprime.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class UncommonWordCounter {

    private WordFinder wordFinder;

    public static void main(String... args) throws IOException {
        String commonWordsFilePath = args[0];
        String textToEvaluateFilePath = args[1];

        System.out.println(System.getProperty("user.dir"));

        String jarDirectory = new File(
                Objects.requireNonNull(
                        ClassLoader.getSystemClassLoader().getResource(".")
                ).getPath()
        ).getAbsolutePath();


        String common = getFileContentsAsString(commonWordsFilePath, jarDirectory);
        String text = getFileContentsAsString(textToEvaluateFilePath, jarDirectory);

        UncommonWordCounter uncommonWordCounter =
                new UncommonWordCounter(
                        new CommonWordFinder(common.split("\n")
                        )
                );
        printFormattedOutput(uncommonWordCounter.evaluate(text));
    }

    private static String getFileContentsAsString(String commonWordsFilePath, String jarDirectory) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(jarDirectory + "/" + commonWordsFilePath));
        return new String(encoded, Charset.defaultCharset());
    }

    public UncommonWordCounter(WordFinder wordFinder) {
        this.wordFinder = wordFinder;
    }

    public Map<String, Integer> evaluate(String sampleText) {
        Map<String, Integer> uncommonWords = new HashMap<>();

        String[] words = sampleText
                .replaceAll("'", "")
                .replaceAll("[^a-zA-Z ]", " ")
                .toLowerCase()
                .split(" ");

        for (String word : words) {
            if (wordFinder.isCommon(word) || word.length() == 0) continue;
            uncommonWords.merge(word, 1, (a, b) -> a + b);
        }

        return uncommonWords;
    }

    private static void printFormattedOutput(Map<String, Integer> results) {
        TreeMap<String, Integer> sortedResults = new TreeMap<>(results);
        String resultStringLine;

        int lineLength = 0;

        for (String word : sortedResults.keySet()) {
            if (lineLength < word.length()) {
                lineLength = word.length();
            }
        }

        lineLength += 5;

        for (String word : sortedResults.keySet()) {
            String count = sortedResults.get(word).toString();
            int whiteSpaceLength = lineLength - word.length() - count.length();
            resultStringLine = word + whiteSpaceGenerator(whiteSpaceLength) + count;
            System.out.println(resultStringLine);
        }
    }

    static private String whiteSpaceGenerator(int length) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
