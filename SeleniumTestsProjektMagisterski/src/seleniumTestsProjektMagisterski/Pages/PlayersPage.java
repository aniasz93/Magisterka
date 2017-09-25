package seleniumTestsProjektMagisterski.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PlayersPage {

	private WebDriver webdriver;
	public PlayersPage(WebDriver webdriver) {
		this.webdriver = webdriver;
	}
	
	public String getProperUrl() {
		return "http://localhost:8080/login#!/players";
	}
	
	public WebElement getNewPlayerButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='$players.addPlayerPopover.open()']"));
	}
	
	public WebElement getPlayerFirstNameField() {
		return webdriver.findElement(By.cssSelector("input[ng-model='$players.playerFirst']"));
	}
	
	public WebElement getPlayerLastNameField() {
		return webdriver.findElement(By.cssSelector("input[ng-model='$players.playerLast']"));
	}
	
	public WebElement getPlayerNicknameField() {
		return webdriver.findElement(By.cssSelector("input[ng-model='$players.playerNickname']"));
	}
	
	public WebElement getPlayerAgeField() {
		return webdriver.findElement(By.cssSelector("input[ng-model='$players.playerAge']"));
	}
	
	public WebElement getCloseNewPlayerButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='$players.addPlayerPopover.close()']"));
	}
	
	public WebElement getSaveNewPlayerButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='form.$valid && $players.create()']"));
	}
}
