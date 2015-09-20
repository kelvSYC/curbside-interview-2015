package com.kelvSYC.curbside;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Scraper of the SmartRecruiters website for jobs by location.
 * @author kelvSYC
 *
 */
public class SmartRecruitersScraper {
	private final WebDriver driver;
	
	public SmartRecruitersScraper(WebDriver driver) {
		this.driver = driver;
	}
	
	public Map<String, Integer> scrape() {
		driver.get("https://careers.smartrecruiters.com/Curbside1/");
		
		// Assume that "browse by location is default"
		List<WebElement> locationElements = driver.findElements(By.cssSelector(".opening"));
		return locationElements.stream().map(element -> {
			WebElement locationElement = element.findElement(By.cssSelector(".opening-title"));
			List<WebElement> jobElements = element.findElements(By.cssSelector(".opening-job"));
			
			return new AbstractMap.SimpleImmutableEntry<>(locationElement.getText(), jobElements.size());
		}).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
}
