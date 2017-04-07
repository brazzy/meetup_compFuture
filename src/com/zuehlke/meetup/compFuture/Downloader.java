package com.zuehlke.meetup.compFuture;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Downloader {
	public CompletableFuture<String> download(String url) throws IOException{
		CompletableFuture<String> result = new CompletableFuture<>();
		try(InputStream stream = new URL(url).openStream()){
			result.complete(new Scanner(stream, "UTF-8").useDelimiter("\\A").next());
			return result;
		}
	}
}
