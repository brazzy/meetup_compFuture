package com.zuehlke.meetup.compFuture;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Exercise2Test {
	private Downloader testee = new Downloader();

	@Test
	public void extractNextLinkNoBraces(){
		String testPage = testee.readFully(getClass().getResourceAsStream("Welt"));
		String found = testee.extractNextLink(testPage);
		assertEquals("Totalität", found);
	}
	
	@Test
	public void extractNextLinkWithBraces(){
		String testPage = testee.readFully(getClass().getResourceAsStream("Muenchen"));
		String found = testee.extractNextLink(testPage);
		assertEquals("Oberbayern", found);
	}
	
	@Test
	public void getLink(){
		String result = testee.getLink("Landeshauptstadt_(Deutschland)");
		assertEquals("https://de.wikipedia.org/wiki/Landeshauptstadt_(Deutschland)?action=raw", result);
	}
	
	@Test
	public void getCycle() throws Exception{
		List<String> result = testee.getCycle("Revolution");
		assertEquals(Arrays.asList("Louvre", "Palais du Louvre", "Glaspyramide im Innenhof des Louvre"), result);
	}
	
}
