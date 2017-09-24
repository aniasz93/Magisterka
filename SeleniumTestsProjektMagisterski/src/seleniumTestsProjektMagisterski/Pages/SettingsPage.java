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
	
//	@FindBy(cssSelector="button[ng-click='$settings.addGamePopover.open()']")
	public WebElement getNewGameButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='$settings.addGamePopover.open()']"));
	}
	
	public WebElement getNewCategoryButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='$settings.addCategoryPopover.open()']"));
	}
	
	public WebElement findTab(String tab) {
		return webdriver.findElement(By.cssSelector("button[ng-click='" + tab + "']"));
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
	
	public List<WebElement> getGamesTable() {
		//return webdriver.findElements(By.cssSelector("div[ng-init='$settings.list()']"));
		
		return webdriver.findElements(By.xpath("div[starts-with(@id, 'accordiongroup-') and ends-with(@id, '-panel')]/div/div[2]/div[1]/table/"));//"//div[@ng-init='$settings.list()']"));
	}
	//*[@id="accordiongroup-7-3933-panel"]/div/div[2]/div[1]/table
	
}