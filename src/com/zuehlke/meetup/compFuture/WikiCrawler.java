package com.zuehlke.meetup.compFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WikiCrawler {
	
	private API api = new API();

	public CompletableFuture<String> download(String url) {
		return null; // TODO
	}

	public CompletableFuture<List<String>> getCycle (String firstWord) {
		return null; // TODO
	}
}
