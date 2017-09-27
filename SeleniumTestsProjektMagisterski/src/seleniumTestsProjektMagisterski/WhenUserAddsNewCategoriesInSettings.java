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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import seleniumTestsProjektMagisterski.Pages.DashboardsPage;
import seleniumTestsProjektMagisterski.Pages.LoginPage;
import seleniumTestsProjektMagisterski.Pages.SettingsPage;

public class WhenUserAddsNewCategoriesInSettings {

	private WebDriver webdriver;
	public String browser = "Chrome";
	private SettingsPage settings = new SettingsPage(webdriver);
	private LoginPage login = new LoginPage(webdriver);
	private DashboardsPage dashboards = new DashboardsPage(webdriver);
	

	int categoriesNumb;
	int newCategoriesNumb;
	String categoryName;
	String categoryDescription;
	List<WebElement> categoriesList;
	
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
	public void addNewGategoryProperly() {
		categoriesNumb = 0;
		newCategoriesNumb = 0;
		categoryName = "Category the first";
		categoryDescription = "Category description is what it is";
		
		login.loginUser("admin", "admin");
		dashboards.findTab("settings()").click();
		webdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		((JavascriptExecutor)webdriver).executeScript("arguments[0].scrollIntoView();", settings.getNewCategoryButton());

		webdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		categoriesNumb = settings.getCategoriesTable().size();

		
		settings.getNewCategoryButton().click();
		settings.getNewCategoryNameField().sendKeys(categoryName);
		settings.getNewCategoryDescriptionField().sendKeys(categoryDescription);
		settings.getSaveNewCategoryButton().click();
		
		Assert.assertTrue(settings.getSuccessfulCategorySavingMessage().isDisplayed());
		
		webdriver.navigate().refresh();
		categoriesList = settings.getCategoriesTable();
		newCategoriesNumb = categoriesList.size();
		
		Assert.assertEquals(newCategoriesNumb, categoriesNumb + 1);
		Assert.assertEquals(settings.getLastGame(categoriesList).getText(), categoryName + " " + categoryDescription);
	}
	
	@Test
	public void addEmptyCategory() {
		categoriesNumb = 0;
		newCategoriesNumb = 0;
		categoryName = "";
		categoryDescription = "";
		
		login.loginUser("admin", "admin");
		dashboards.findTab("settings()").click();
		webdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		((JavascriptExecutor)webdriver).executeScript("arguments[0].scrollIntoView();", settings.getNewCategoryButton());

		webdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		categoriesNumb = settings.getGamesTable().size();
		
		settings.getNewGameButton().click();
		settings.getNewGameNameField().sendKeys(categoryName);
		settings.getNewGameDescriptionField().sendKeys(categoryDescription);
		settings.getSaveNewGameButton().click();

		Assert.assertTrue(settings.cannotSaveNewCategoryLabel.isDisplayed());
		
		webdriver.navigate().refresh();
		categoriesList = settings.getGamesTable();
		newCategoriesNumb = categoriesList.size();
		
		Assert.assertEquals(newCategoriesNumb, categoriesNumb);
	}
	
	@After
	public void closeTheBrowser() {
		login.close();
	}
}
