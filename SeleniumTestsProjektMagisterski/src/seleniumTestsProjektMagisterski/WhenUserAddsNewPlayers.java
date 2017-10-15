package seleniumTestsProjektMagisterski;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import seleniumTestsProjektMagisterski.Pages.DashboardsPage;
import seleniumTestsProjektMagisterski.Pages.LoginPage;
import seleniumTestsProjektMagisterski.Pages.PlayersPage;
import seleniumTestsProjektMagisterski.Pages.SettingsPage;

public class WhenUserAddsNewPlayers {

	private WebDriver webdriver;
	public String browser = "Chrome";
	private PlayersPage players = new PlayersPage(webdriver);
	private LoginPage login = new LoginPage(webdriver);
	private DashboardsPage dashboards = new DashboardsPage(webdriver);
	
	int playersNumb = 0;
	int newPlayersNumb = 0;
	String playerFirst = "Brad";
	String playerLast = "Pitt";
	String playerNick = "BiPi";
	String playerAge = "60";
	List<WebElement> playersList;
	
	@Before
	public void openTheBrowser() {
		System.out.println("Browser:" + browser);
		
		if(browser.equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver", "IEDriverServer_x64_3.0.0\\IEDriverServer.exe");
			webdriver = new InternetExplorerDriver();
		} else if(browser.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "chromedriver_win32\\chromedriver.exe");
			webdriver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "geckodriver-v0.19.0-win64\\geckodriver.exe");
			webdriver = new FirefoxDriver();
		}
		
		login = PageFactory.initElements(webdriver, LoginPage.class);
		dashboards = PageFactory.initElements(webdriver, DashboardsPage.class);
		players = PageFactory.initElements(webdriver, PlayersPage.class);
		login.open(login.getProperUrl());
	}
	
	@Test
	public void addNewPlayerAllDataFilledProperly() {
		playersNumb = 0;
		newPlayersNumb = 0;
		playerFirst = "Brad";
		playerLast = "Pitt";
		playerNick = "BiPi";
		playerAge = "60";
		
		try {
			login.loginUser("admin", "admin");
			dashboards.findTab("players()").click();
			Thread.sleep(500);
			playersNumb = players.getPlayersTable().size();
			
			players.getNewPlayerButton().click();
			players.getPlayerFirstNameField().sendKeys(playerFirst);
			players.getPlayerLastNameField().sendKeys(playerLast);
			players.getPlayerNicknameField().sendKeys(playerNick);
			players.getPlayerAgeField().sendKeys(playerAge);
			players.getSaveNewPlayerButton().click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(players.getSuccessfulPlayerSavingMessage().isDisplayed());
		
		try {
			webdriver.navigate().refresh();
			playersList = players.getPlayersTable();
			newPlayersNumb = playersList.size();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Assert.assertEquals(newPlayersNumb, playersNumb + 1);
		Assert.assertEquals(players.getLastPlayer(playersList).getText(), playerFirst + " " + playerLast + " " + playerNick + " " + playerAge);
	}
	
	@Test
	public void addNewPlayerOnlyObligatoryField() {
		playersNumb = 0;
		newPlayersNumb = 0;
		playerNick = "BiPi";
		
		try {
			login.loginUser("admin", "admin");
			dashboards.findTab("players()").click();
			Thread.sleep(500);
			playersNumb = players.getPlayersTable().size();
			
			players.getNewPlayerButton().click();
			players.getPlayerNicknameField().sendKeys(playerNick);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(players.getSaveNewPlayerButton().isEnabled());
		
		try {
			players.getSaveNewPlayerButton().click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(players.getSuccessfulPlayerSavingMessage().isDisplayed());
		
		try {
			webdriver.navigate().refresh();
			playersList = players.getPlayersTable();
			newPlayersNumb = playersList.size();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Assert.assertEquals(newPlayersNumb, playersNumb + 1);
		Assert.assertEquals(players.getLastPlayer(playersList).getText(), " " + " " + playerNick + " ");
	}
	
	@Test
	public void addEmptyPlayer() {
		playersNumb = 0;
		newPlayersNumb = 0;
		
		try {
			login.loginUser("admin", "admin");
			dashboards.findTab("players()").click();
			Thread.sleep(500);
			playersNumb = players.getPlayersTable().size();
			
			players.getNewPlayerButton().click();
			players.getSaveNewPlayerButton().click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(players.cannotSaveNewPlayerLabel.isDisplayed());
		
		try {
			webdriver.navigate().refresh();
			playersList = players.getPlayersTable();
			newPlayersNumb = playersList.size();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Assert.assertEquals(newPlayersNumb, playersNumb);
	}
	
	@After
	public void closeTheBrowser() {
		login.close();
	}
}
