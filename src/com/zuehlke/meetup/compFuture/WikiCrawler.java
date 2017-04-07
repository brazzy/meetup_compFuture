package com.zuehlke.meetup.compFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WikiCrawler {
	
	private API api = new API();

	/**
	 * Use the API to download the content of an URL and return a nice CompletableFuture.
	 */
	public CompletableFuture<String> download(String url) {
		return null; // TODO
	}

	/**
	 * Use the API to return the first wiki link in the Wikipedia article about the given word.
	 */
	public CompletableFuture<String> getFirstLinkInArticle(String word) {
		return null; // TODO
	}

	/**
	 * Use the API to get the repeating ofd the cycle which you eventually encounter when repeatedly following 
	 * the first wiki link on a Wikipedia page, starting at the given word.
	 */
	public CompletableFuture<List<String>> getCycle (String firstWord) {
		return null; // TODO
	}
}
