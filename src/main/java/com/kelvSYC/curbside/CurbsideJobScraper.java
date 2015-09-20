package com.kelvSYC.curbside;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;

/**
 * Scraper of the Curbside website for jobs by location.
 * @author kelvSYC
 *
 */
public class CurbsideJobScraper {
	private final WebDriver driver;
	
	public CurbsideJobScraper(WebDriver driver) {
		this.driver = driver;
	}
	
	public Map<String, Integer> scrape() {
		driver.get("https://www.shopcurbside.com/jobs/");
		// The locations table may not load straight away...
		sleepUninterruptibly(1L, TimeUnit.SECONDS);			// Hacky, but will do the trick
		
		WebElement table = driver.findElement(By.id("jobsTable"));
		List<WebElement> locations = table.findElements(By.cssSelector(".location"));
		
		return locations.stream().map(WebElement::getText).
				collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0, e -> 1, Integer::sum)));	// Collectors.counting() works with long instead of int
	}
}
