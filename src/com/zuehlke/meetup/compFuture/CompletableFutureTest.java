package com.zuehlke.meetup.compFuture;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompletableFutureTest {

	private Downloader testee = new Downloader();
	
	@Test
	public void downloadContent() throws Exception{
		String content = testee.download("https://de.wikipedia.org/wiki/Java_(Programmiersprache)");
		assertThat(content, CoreMatchers.containsString("Java Technology: The Early Years."));
	}

}
