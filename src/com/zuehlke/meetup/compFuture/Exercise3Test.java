package com.zuehlke.meetup.compFuture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.Test;

public class Exercise3Test {
	private WikiCrawler testee = new WikiCrawler();
	
	@Test
	public void getCycle() throws Exception{
		CompletableFuture<List<String>> result = testee.getCycle("Revolution");
		assertEquals(Arrays.asList("Louvre", "Palais du Louvre", "Glaspyramide im Innenhof des Louvre"), result.get());
	}
	
	@Test
	public void asynchronous() throws Exception{
		long start = System.currentTimeMillis();
		testee.getCycle("Revolution");
		long elapsed = System.currentTimeMillis() - start;
		assertTrue(String.format("Call took too %s milliseconds, cannot have been asynchronous.", elapsed), elapsed < 30);
	}

	@Test
	public void error() throws Exception{
		CompletableFuture<List<String>> result = testee.getCycle("");
		while(!result.isDone()){
		    Thread.sleep(100);
		}
		assertTrue("Did not complete exceptionally", result.isCompletedExceptionally());
	}
}
