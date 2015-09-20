package com.kelvSYC.curbside;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.collect.ImmutableMap;

/**
 * Program that scrapes the Curbside jobs page and prints job counts by location.
 * @author kelvSYC
 *
 */
public class CurbsidePrinter {
	// Incomplete list, but should cover the average case.
	private static final Map<String, String> stateAbbreviations = ImmutableMap.<String, String>builder().put("CA", "California").put("NY", "New York").build();
	
	public static void main(String... args) {
		WebDriver driver = new ChromeDriver();
		CurbsideJobScraper scraper = new CurbsideJobScraper(driver);
		
		scraper.scrape().forEach((location, count) -> {
			int index = location.indexOf(", ");
			String city = location.substring(0, index);
			String state = location.substring(index + 2);
			
			System.out.printf("%s, %s: %d%n", city, stateAbbreviations.get(state), count);
		});
		
		driver.close();
	}
}
