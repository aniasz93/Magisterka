package seleniumTestsProjektMagisterski.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsPage {

	WebDriver webdriver;
	
	public SettingsPage(WebDriver webdriver) {
		this.webdriver = webdriver;
	}
	
	public String getProperUrl() {
		return "http://localhost:8080/login#!/settings";
	}
	
//	@FindBy(cssSelector="[@id=\"accordiongroup-154-2629-panel\"]/div/div[2]/div[2]/span/button")
	public WebElement getNewGameButton() {
		return webdriver.findElement(By.cssSelector("[ng-click='buttonOnClick($settings.addGamePopover.open())']"));
	}
	
	public WebElement getNewCategoryButton() {
		return webdriver.findElement(By.cssSelector("[ng-click='buttonOnClick($settings.addCategoryPopover.open()]'"));
	}
}