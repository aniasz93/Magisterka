package seleniumTestsProjektMagisterski;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
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

public class CheckIfFieldsProperlyFilledInNewDashboards {

	private WebDriver webdriver;
	public String browser = "Chrome";
	private LoginPage login = new LoginPage(webdriver);
	private DashboardsPage dashboards = new DashboardsPage(webdriver);
	private SettingsPage settings = new SettingsPage(webdriver);
	
	List<WebElement> gamesList;
	List<WebElement> gamesListFromSettings;
	List<WebElement> gamesListInDashboards;
	List<WebElement> categoriesList;
	List<WebElement> categoriesListFromSettings;
	List<WebElement> categoriesListInDashboards;
	
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
		settings = PageFactory.initElements(webdriver, SettingsPage.class);
		login.open(login.getProperUrl());
	}
	
	@Test
	public void gameDropdown() {
		
		try {
			login.loginUser("admin", "admin");
			dashboards.findTab("settings()").click();
			Thread.sleep(500);
			
			gamesList = settings.getGamesTable();
	
			gamesListFromSettings = settings.getGamesTable();
			
			settings.findTab("dashboards()").click();
			Thread.sleep(500);
			
			dashboards.getNewDashboardButton().click();
			gamesListInDashboards = dashboards.getGamesListInDropdown();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(gamesListInDashboards.containsAll(gamesListFromSettings));
	}
	
	@Test
	public void categoryDropdown() {

		try {
			login.loginUser("admin", "admin");
			dashboards.findTab("settings()").click();
			Thread.sleep(500);
			
			categoriesList = settings.getCategoriesTable();
	
			((JavascriptExecutor)webdriver).executeScript("arguments[0].scrollHeight;", settings.getCategoriesTable());
			categoriesListFromSettings = settings.getCategoriesTable();
			
			settings.findTab("dashboards()").click();
			Thread.sleep(500);
			
			dashboards.getNewDashboardButton().click();
			categoriesListInDashboards = dashboards.getCategoriesListInDropdown();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(categoriesListInDashboards.containsAll(categoriesListFromSettings));
	}
	
	@After
	public void closeTheBrowser() {
		login.close();
	}
}
