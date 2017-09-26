package seleniumTestsProjektMagisterski;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import seleniumTestsProjektMagisterski.Pages.DashboardsPage;
import seleniumTestsProjektMagisterski.Pages.LoginPage;
import seleniumTestsProjektMagisterski.Pages.SettingsPage;

public class WhenUserAddsNewGamesInSettings {

	private WebDriver webdriver;
	private SettingsPage settings = new SettingsPage(webdriver);
	private LoginPage login = new LoginPage(webdriver);
	private DashboardsPage dashboards = new DashboardsPage(webdriver);
	
	@Before
	public void openTheBrowser() {
		System.setProperty("webdriver.chrome.driver", "D:\\Uczelnia\\UAM\\Magisterka\\Projekt magisterski\\chromedriver_win32\\chromedriver.exe");
		webdriver = new ChromeDriver();
		login = PageFactory.initElements(webdriver, LoginPage.class);
		dashboards = PageFactory.initElements(webdriver, DashboardsPage.class);
		settings = PageFactory.initElements(webdriver, SettingsPage.class);
		login.open(login.getProperUrl());
	}
	
	@Test
	public void addNewGameProperly() {
		int gamesNumb = 0;
		int newGamesNumb = 0;
		List<WebElement> gamesList;
		String gameName = "xxx";
		String gameDescription = "yyy";
		
		login.loginUser("admin", "admin");
		dashboards.findTab("settings()").click();
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		gamesNumb = settings.getGamesTable().size();
		
		settings.getNewGameButton().click();
		settings.getNewGameNameField().sendKeys(gameName);
		settings.getNewGameDescriptionField().sendKeys(gameDescription);
		settings.getSaveNewGameButton().click();

		Assert.assertTrue(settings.getSuccessfulGameSavingMessage().isDisplayed());
		
		webdriver.navigate().refresh();
		gamesList = settings.getGamesTable();
		newGamesNumb = gamesList.size();
		
		Assert.assertEquals(newGamesNumb, gamesNumb + 1);
		Assert.assertEquals(settings.getLastGame(gamesList).getText(), gameName + " " + gameDescription);
	}
	
	@Test
	public void addEmptyGame() {
		int gamesNumb = 0;
		int newGamesNumb = 0;
		List<WebElement> gamesList;
		String gameName = "";
		String gameDescription = "";
		
		login.loginUser("admin", "admin");
		dashboards.findTab("settings()").click();
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		gamesNumb = settings.getGamesTable().size();
		
		settings.getNewGameButton().click();
		settings.getNewGameNameField().sendKeys(gameName);
		settings.getNewGameDescriptionField().sendKeys(gameDescription);
		settings.getSaveNewGameButton().click();

		Assert.assertTrue(settings.cannotSaveNewGameLabel.isDisplayed());
		
		webdriver.navigate().refresh();
		gamesList = settings.getGamesTable();
		newGamesNumb = gamesList.size();
		
		Assert.assertEquals(newGamesNumb, gamesNumb);
	}
	
	@After
	public void closeTheBrowser() {
		login.close();
	}
}
