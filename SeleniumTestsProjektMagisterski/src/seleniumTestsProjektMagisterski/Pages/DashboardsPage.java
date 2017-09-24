package seleniumTestsProjektMagisterski.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardsPage {

	WebDriver webdriver;
	public DashboardsPage(WebDriver webdriver) {
		this.webdriver = webdriver;
	}
	
	public String getProperUrl() {
		return "http://localhost:8080/login#!/dashboards";
	}
	
	public WebElement findTab(String tab) {
		return webdriver.findElement(By.cssSelector("button[ng-click='" + tab + "']"));
	}
}
