package seleniumTestsProjektMagisterski.Pages;

import java.util.List;

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
	
	public WebElement findTab(String tab) {
		return webdriver.findElement(By.cssSelector("button[ng-click='" + tab + "']"));
	}
	
	public List<WebElement> getGamesTable() {		
		return webdriver.findElements(By.cssSelector("div[ng-init='$settings.list()'] > table > tbody > tr"));
	}
	
	public WebElement getLastGame(List<WebElement> list) {
		return list.get(list.size() - 1);
	}
	
	public WebElement getNewGameButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='$settings.addGamePopover.open()']"));
	}
	
	public WebElement getNewGameNameField() {
		return webdriver.findElement(By.cssSelector("input[ng-model='$settings.gameName']"));
	}
	
	public WebElement getNewGameDescriptionField() {
		return webdriver.findElement(By.cssSelector("input[ng-model='$settings.gameDescription']"));
	}
	
	public WebElement getCloseNewGameButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='$settings.addGamePopover.close()']"));
	}
	
	public WebElement getSaveNewGameButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='$settings.create(); $settings.addGamePopover.close()']"));
	}
	
	public WebElement getSuccessfulGameSavingMessage() {
		return webdriver.findElement(By.cssSelector("div[ng-if='$settings.gameCreated'] > div"));
	}
	
	public List<WebElement> getCategoriesTable() {		
		return webdriver.findElements(By.cssSelector("div[ng-init='$settings.listCategories()'] > table > tbody > tr"));
	}
	
	public WebElement getNewCategoryButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='$settings.addCategoryPopover.open()']"));
	}
	
	public WebElement getNewCategoryNameField() {
		return webdriver.findElement(By.cssSelector("input[ng-model='$settings.categoryName']"));
	}
	
	public WebElement getNewCategoryDescriptionField() {
		return webdriver.findElement(By.cssSelector("input[ng-model='$settings.categoryDescription']"));
	}
	
	public WebElement getCloseNewCategoryButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='$settings.addCategoryPopover.close()']"));
	}
	
	public WebElement getSaveNewCategoryButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='$settings.createCategory(); $settings.addCategoryPopover.close()']"));
	}
	
	public WebElement getSuccessfulCategorySavingMessage() {
		return webdriver.findElement(By.cssSelector("div[ng-if='$settings.categoryCreated'] > div"));
	}
	
	@FindBy(id="cannotSaveNewGameMessage")
	public WebElement cannotSaveNewGameLabel;
	
	@FindBy(id="cannotSaveNewCategoryMessage")
	public WebElement cannotSaveNewCategoryLabel;
}