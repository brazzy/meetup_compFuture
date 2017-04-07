package com.zuehlke.meetup.compFuture;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompletableFutureTest {

	private static final String START_URL = "https://de.wikipedia.org/wiki/Java_(Programmiersprache)";
	private Downloader testee = new Downloader();
	
	@Test
	public void downloadContent() throws Exception{
		String content = testee.download(START_URL).get();
		assertThat(content, CoreMatchers.containsString("Java Technology: The Early Years."));
	}

	@Test
	public void downloadAsynchronous() throws Exception{
		long start = System.currentTimeMillis();
		testee.download(START_URL).get();
		long elapsed = System.currentTimeMillis() - start;
		assertTrue(String.format("Call took too %s milliseconds, cannot have been asynchronous.", elapsed), elapsed < 10);
	}
}
