package com.kelvSYC.curbside;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Simple test that will compare the results between Curbside and SmartRecruiters.
 * @author kelvSYC
 *
 */
public class SiteComparisonTest {
	private WebDriver driver;
	
	@Before
	public void setupDriver() {
		driver = new ChromeDriver();
	}
	
	@After
	public void closeDriver() {
		driver.close();
	}
	
	@Test
	public void run() {
		CurbsideJobScraper curbsideScraper = new CurbsideJobScraper(driver);
		SmartRecruitersScraper srScraper = new SmartRecruitersScraper(driver);
		
		Map<String, Integer> curbsideMap = curbsideScraper.scrape();
		Map<String, Integer> srMap = srScraper.scrape();
		
		assertEquals("Mismatch", curbsideMap, srMap);
	}
}
