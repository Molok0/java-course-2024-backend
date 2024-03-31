package edu.java.processors.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlParser {
    private static final int GROUP_THREE = 3;
    private static final int GROUP_FOUR = 4;
    private static final String PATTERN_FOR_SITE = "(https?://)(www\\.)?([a-zA-Z0-9]+)\\.(com|org|net)";

    private UrlParser() {

    }

    public static String getWebSiteName(String url) {
        Pattern pattern = Pattern.compile(PATTERN_FOR_SITE);
        Matcher matcher = pattern.matcher(url);
        String domain = "";
        if (matcher.find()) {
            domain = matcher.group(GROUP_THREE) + "." + matcher.group(GROUP_FOUR);
        }
        return domain;
    }
}

