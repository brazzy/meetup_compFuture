package com.zuehlke.meetup.compFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WikiCrawler {

    private API api = new API();

    /**
     * Use the API to download the content of an URL and return a nice
     * CompletableFuture.
     */
    public CompletableFuture<String> download(String url) {
        CompletableFuture<String> result = new CompletableFuture<>();
        api.download(url, result::complete, result::completeExceptionally);
        return result;
    }

    private CompletableFuture<String> getLink(String word) {
        CompletableFuture<String> result = new CompletableFuture<>();
        api.getLink(word, result::complete);
        return result;
    }

    private CompletableFuture<String> extractNextLink(String content) {
        CompletableFuture<String> result = new CompletableFuture<>();
        api.extractNextLink(content, result::complete, result::completeExceptionally);
        return result;
    }

    /**
     * Use the API to return the first wiki link in the Wikipedia article about
     * the given word.
     */
    public CompletableFuture<String> getFirstLinkInArticle(String word) {
        return getLink(word).thenComposeAsync(this::download).thenComposeAsync(
                this::extractNextLink);
    }

    /**
     * This will hold all the articles we have visited in getCycle().
     */
    private List<String> trail = new ArrayList<>();

    /**
     * Use the API to get the repeating ofd the cycle which you eventually
     * encounter when repeatedly following the first wiki link on a Wikipedia
     * page, starting at the given word.
     */
    public CompletableFuture<List<String>> getCycle(String firstWord) {
        System.out.println(String.format("Reading '%s' in thread %s", firstWord, Thread
                .currentThread().getName()));
        // we use recursion, this is the terminating case
        if (trail.contains(firstWord)) {
            CompletableFuture<List<String>> result = new CompletableFuture<>();
            result.complete(trail.subList(trail.indexOf(firstWord), trail.size()));
            trail = new ArrayList<>();
            return result;
        } else { // here the method calls itself recursively.
            trail.add(firstWord);
            return getFirstLinkInArticle(firstWord).thenComposeAsync(this::getCycle);
        }
    }
}
