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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import seleniumTestsProjektMagisterski.Pages.DashboardsPage;
import seleniumTestsProjektMagisterski.Pages.LoginPage;
import seleniumTestsProjektMagisterski.Pages.SettingsPage;

public class WhenUserAddsNewGamesInSettings {

	private WebDriver webdriver;
	public String browser = "Chrome";
	private SettingsPage settings = new SettingsPage(webdriver);
	private LoginPage login = new LoginPage(webdriver);
	private DashboardsPage dashboards = new DashboardsPage(webdriver);

	int gamesNumb;
	int newGamesNumb;
	List<WebElement> gamesList;
	String gameName;
	String gameDescription;
	
	@Before
	public void openTheBrowser() {
		System.out.println("Browser:" + browser);
		
		if(browser.equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver", "D:\\Uczelnia\\UAM\\Magisterka\\Projekt magisterski\\IEDriverServer_x64_3.0.0\\IEDriverServer.exe");
			webdriver = new InternetExplorerDriver();
		} else if(browser.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "D:\\Uczelnia\\UAM\\Magisterka\\Projekt magisterski\\chromedriver_win32\\chromedriver.exe");
			webdriver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "D:\\Uczelnia\\UAM\\Magisterka\\Projekt magisterski\\geckodriver-v0.19.0-win64\\geckodriver.exe");
			webdriver = new FirefoxDriver();
		}
		
		login = PageFactory.initElements(webdriver, LoginPage.class);
		dashboards = PageFactory.initElements(webdriver, DashboardsPage.class);
		settings = PageFactory.initElements(webdriver, SettingsPage.class);
		login.open(login.getProperUrl());
	}
	
	@Test
	public void addNewGameProperly() {
		gamesNumb = 0;
		newGamesNumb = 0;
		gameName = "xxx";
		gameDescription = "yyy";
		
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
		gamesNumb = 0;
		newGamesNumb = 0;
		gameName = "";
		gameDescription = "";
		
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
