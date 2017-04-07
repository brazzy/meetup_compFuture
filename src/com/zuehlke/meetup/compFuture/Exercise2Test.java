package com.zuehlke.meetup.compFuture;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class Exercise2Test {
	private static final String START_URL = "https://de.wikipedia.org/wiki/Philosophie";
	private Downloader testee = new Downloader();

	@Test
	public void extractNextLink(){
		String testPage = testee.readFully(getClass().getResourceAsStream("Muenchen.htm"));
		String found = testee.extractNextLink(testPage);
		assertEquals("/wiki/Landeshauptstadt_(Deutschland)", found);
	}
	
	@Test
	public void buildAbsoluteLink(){
		String result = testee.buildAbsoluteLink("/wiki/Landeshauptstadt_(Deutschland)");
		assertEquals("https://de.wikipedia.org/wiki/Landeshauptstadt_(Deutschland)", result);
	}
	
	@Test
	public void getCycle(){
		List<String> result = testee.getCycle(START_URL);
		assertEquals(Arrays.asList(START_URL,  "https://de.wikipedia.org/wiki/Welt",  "https://de.wikipedia.org/wiki/Totalit%C3%A4t"), result);
	}
	
}
