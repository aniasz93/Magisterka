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
	public void gameDropdown() {
		
		login.loginUser("admin", "admin");
		dashboards.findTab("settings()").click();
		webdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		gamesList = settings.getGamesTable();

		gamesListFromSettings = settings.getGamesTable();
		
		settings.findTab("dashboards()").click();
		webdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		dashboards.getNewDashboardButton().click();
		gamesListInDashboards = dashboards.getGamesListInDropdown();
		
		Assert.assertTrue(gamesListInDashboards.containsAll(gamesListFromSettings));
	}
	
	@Test
	public void categoryDropdown() {

		login.loginUser("admin", "admin");
		dashboards.findTab("settings()").click();
		webdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		categoriesList = settings.getCategoriesTable();

		((JavascriptExecutor)webdriver).executeScript("arguments[0].scrollIntoView();", settings.getSaveNewCategoryButton());
		categoriesListFromSettings = settings.getCategoriesTable();
		
		settings.findTab("dashboards()").click();
		webdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		dashboards.getNewDashboardButton().click();
		categoriesListInDashboards = dashboards.getCategoriesListInDropdown();
		
		Assert.assertTrue(categoriesListInDashboards.containsAll(categoriesListFromSettings));
	}
	
	@After
	public void closeTheBrowser() {
		login.close();
	}
}
