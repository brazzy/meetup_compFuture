package com.zuehlke.meetup.compFuture;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An asynchronous API you have to work with - no changes allowed!
 */
public final class API {
    /**
     * Gets the content of the given URL.
     * 
     * @param url
     *            to get.
     * @param success
     *            will be passed the content if the download succeeds.
     * @param error
     *            will be passed the error, if something goes wrong.
     */
    public final void download(String url, Consumer<String> success, Consumer<Exception> error) {
        ForkJoinPool.commonPool().execute(
                () -> {
                    try {
                        success.accept(new Scanner(new URL(url).openStream(), "UTF-8")
                                .useDelimiter("\\A").next());
                    } catch (Exception ex) {
                        error.accept(ex);
                    }
                });
    }

    /**
     * Gets the first Wiki link from the given page (works on the raw wiki
     * content you get when appending "?action=raw").
     * 
     * @param page
     *            content.
     * @param success
     *            will be passed the link word if the download succeeds.
     * @param error
     *            will be passed the error, if something goes wrong.
     */
    public final void extractNextLink(String page, Consumer<String> success,
            Consumer<Exception> error) {
        ForkJoinPool.commonPool().execute(() -> {
            Pattern p = Pattern.compile("\\[\\[([^|\\]:#]*)(\\||\\]|#)");
            Matcher m = p.matcher(page);
            if (m.find()) {
                success.accept(m.group(1));
            } else {
                error.accept(new IllegalArgumentException());
            }
        });
    }

    /**
     * Builds the download URL from a link word.
     * 
     * @param word
     *            to create an URL for.
     * @param callback
     *            will be passed the URL.
     */
    public final void getLink(String word, Consumer<String> callback) {
        ForkJoinPool.commonPool().execute(() -> {
            callback.accept("https://de.wikipedia.org/wiki/" + word + "?action=raw");
        });
    }
}
