package seleniumTestsProjektMagisterski.Pages;

import org.openqa.selenium.WebDriver;

public class PlayersPage {

	private WebDriver webdriver;
	public PlayersPage(WebDriver webdriver) {
		this.webdriver = webdriver;
	}
	
	public String getProperUrl() {
		return "http://localhost:8080/login#!/players";
	}
}
