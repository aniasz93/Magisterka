package seleniumTestsProjektMagisterski.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlayersPage {

	private WebDriver webdriver;
	public PlayersPage(WebDriver webdriver) {
		this.webdriver = webdriver;
	}
	
	public String getProperUrl() {
		return "http://localhost:8080/login#!/players";
	}
	
	public List<WebElement> getPlayersTable() {		
		return webdriver.findElements(By.cssSelector("div[ng-init='$players.list()'] > table > tbody > tr"));
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
	
	public WebElement getSuccessfulPlayerSavingMessage() {
		return webdriver.findElement(By.cssSelector("div[ng-if='$players.success'] > div"));
	}
	
	public WebElement getLastPlayer(List<WebElement> list) {
		return list.get(list.size() - 1);
	}
	
	@FindBy(id="cannotSaveNewPlayerMessage")
	public WebElement cannotSaveNewPlayerLabel;
}
