package com.zuehlke.meetup.compFuture;

import java.util.concurrent.CompletableFuture;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class Exercise1Test {

	private static final String START_URL = "https://de.wikipedia.org/wiki/Java_(Programmiersprache)";
	private static final String ERROR_URL = "http://localhost:555/";
	private Downloader testee = new Downloader();
	
	@Test
	public void content() throws Exception{
		String content = testee.download(START_URL).get();
		assertThat(content, CoreMatchers.containsString("Java Technology: The Early Years."));
	}

	@Test
	public void asynchronous() throws Exception{
		long start = System.currentTimeMillis();
		testee.download(START_URL).get();
		long elapsed = System.currentTimeMillis() - start;
		assertTrue(String.format("Call took too %s milliseconds, cannot have been asynchronous.", elapsed), elapsed < 10);
	}

	@Test
	public void error() throws Exception{
		CompletableFuture<String> result = testee.download(ERROR_URL);
		assertTrue("Did not complete exceptionally", result.isCompletedExceptionally());
	}
}
