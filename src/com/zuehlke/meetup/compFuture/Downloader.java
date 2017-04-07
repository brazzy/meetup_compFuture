package com.zuehlke.meetup.compFuture;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Downloader {
	public String download(String url) throws IOException{
		try(InputStream stream = new URL(url).openStream()){
			return new Scanner(stream, "UTF-8").useDelimiter("\\A").next();			
		}
	}
}
