package com.zuehlke.meetup.compFuture;

import static org.junit.Assert.*;

import java.util.concurrent.CompletableFuture;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class Exercise1Test {

    private static final String START_URL = "https://de.wikipedia.org/wiki/Java_(Programmiersprache)";
    private static final String ERROR_URL = "http://localhost:555/";
    private WikiCrawler testee = new WikiCrawler();

    @Test
    public void downloadContent() throws Exception {
        String content = testee.download(START_URL).get();
        assertThat(content, CoreMatchers.containsString("Java Technology: The Early Years."));
    }

    @Test
    public void downloadAsynchronous() throws Exception {
        long start = System.currentTimeMillis();
        testee.download(START_URL);
        long elapsed = System.currentTimeMillis() - start;
        assertTrue(String.format("Call took too %s milliseconds, cannot have been asynchronous.",
                elapsed), elapsed < 30);
    }

    @Test
    public void downloadError() throws Exception {
        CompletableFuture<String> result = testee.download(ERROR_URL);
        while (!result.isDone()) {
            Thread.sleep(100);
        }
        assertTrue("Did not complete exceptionally", result.isCompletedExceptionally());
    }

    @Test
    public void getFirstLinkInArticle() throws Exception {
        CompletableFuture<String> result = testee.getFirstLinkInArticle("Welt");
        assertEquals("Totalit\u00e4t", result.get());
        result = testee.getFirstLinkInArticle("M\u00fcnchen");
        assertEquals("Oberbayern", result.get());
    }
}
