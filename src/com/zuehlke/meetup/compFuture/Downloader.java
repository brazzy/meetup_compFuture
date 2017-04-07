package com.zuehlke.meetup.compFuture;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Downloader {

	public CompletableFuture<String> download(String url) throws IOException{
		CompletableFuture<String> result = new CompletableFuture<>();
		result.complete(readFully(new URL(url).openStream()));
		return result;
	}

	public List<String> getCycle (String firstWord) throws Exception{
		List<String> result = new ArrayList<>();
		String current = firstWord;
		while(!result.contains(current)){
			//System.out.println(current);
			result.add(current);
			String url =getLink(current);
			//System.out.println(url);
			String page = download(url).get();
			current = extractNextLink(page);
		}
		return result.subList(result.indexOf(current), result.size());
	}

	public String extractNextLink(String page){
		Pattern p = Pattern.compile("\\[\\[([^|\\]:#]*)(\\||\\]|#)");
		Matcher m = p.matcher(page);
		if (m.find()) {
		    return m.group(1);
		} else {
			return null;
		}
	}

	public String getLink(String word){
		return "https://de.wikipedia.org/wiki/"+word+"?action=raw";
	}

	/**
	 * A utility method also used by the tests.
	 */
	public String readFully(InputStream stream){
		return new Scanner(stream, "UTF-8").useDelimiter("\\A").next();
	}	
}
