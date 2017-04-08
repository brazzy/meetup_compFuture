package com.zuehlke.meetup.compFuture;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;

public class Exercise2Test {
    private WikiCrawler testee = new WikiCrawler();

    @Test
    public void getFirstLinkInArticle() throws Exception {
        CompletableFuture<String> result = testee.getFirstLinkInArticle("Welt");
        assertEquals("Totalit\u00e4t", result.get());
        result = testee.getFirstLinkInArticle("M\u00fcnchen");
        assertEquals("Oberbayern", result.get());
    }

}
