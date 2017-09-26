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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import seleniumTestsProjektMagisterski.Pages.DashboardsPage;
import seleniumTestsProjektMagisterski.Pages.LoginPage;
import seleniumTestsProjektMagisterski.Pages.SettingsPage;

public class WhenUserAddsNewCategoriesInSettings {

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
	public void addNewGategoryProperly() {
		int categoriesNumb = 0;
		int newCategoriesNumb = 0;
		String categoryName = "Category the first";
		String categoryDescription = "Category description is what it is";
		List<WebElement> categoriesList;
		
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
		int categoriesNumb = 0;
		int newCategoriesNumb = 0;
		String categoryName = "";
		String categoryDescription = "";
		List<WebElement> categoriesList;
		
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
