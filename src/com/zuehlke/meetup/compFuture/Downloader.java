package com.zuehlke.meetup.compFuture;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Downloader {
	public CompletableFuture<String> download(String url) throws IOException{
		CompletableFuture<String> result = new CompletableFuture<>();
		result.complete(readFully(new URL(url).openStream()));
		return result;
	}
	
	public String extractNextLink(String page){
		return null; // TODO
	}

	public String buildAbsoluteLink(String relativeLink){
		return null; // TODO
	}

	public List<String> getCycle (String firstUrl){
		return null; // TODO
	}

	/**
	 * A utility method also used by the tests.
	 */
	public String readFully(InputStream stream){
		return new Scanner(stream, "UTF-8").useDelimiter("\\A").next();
	}	
}
