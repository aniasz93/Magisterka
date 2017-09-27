package seleniumTestsProjektMagisterski;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;
import seleniumTestsProjektMagisterski.Pages.DashboardsPage;
import seleniumTestsProjektMagisterski.Pages.LoginPage;
import seleniumTestsProjektMagisterski.Pages.SettingsPage;

public class WhenUserAddsNewDashboards {

	private WebDriver webdriver;
	public String browser = "Chrome";
	private LoginPage login = new LoginPage(webdriver);
	private DashboardsPage dashboards = new DashboardsPage(webdriver);
	private SettingsPage settings = new SettingsPage(webdriver);

	String dashboardName = "Training";
	Select select;
	int dashboardsNumb;
	int newDashboardsNumb;
	List<WebElement> dashboardsList;
	WebElement dashboardGame;
	WebElement dashboardCategory;
	
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
		login.open(login.getProperUrl());
	}
	
	@Test
	public void AddNewDashboardProperly() {
		
		login.loginUser("admin", "admin");
		dashboards.getNewDashboardButton().click();
		dashboards.getNewDashboardNameField().sendKeys(dashboardName);
		

		webdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		dashboardsNumb = dashboards.getDashboardsTable().size();
		
		select = new Select(dashboards.getNewGameDropdown);
		//select.deselectAll();
		select.selectByIndex(1);
		String selectedGame = select.getFirstSelectedOption().getText();
		
		select = new Select(dashboards.getNewCategoryDropdown);
		//select.deselectAll();
		select.selectByIndex(1);
		String selectedCategory = select.getFirstSelectedOption().getText();
		dashboards.getSaveNewCategoryButton().click();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date = new Date();
		String dateStr = dateFormat.format(date);
		
		webdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		webdriver.navigate().refresh();
		dashboardsList = dashboards.getDashboardsTable();
		newDashboardsNumb = dashboardsList.size();
		
		Assert.assertEquals(newDashboardsNumb, dashboardsNumb + 1);
		Assert.assertEquals(dashboards.getFirstDashboard(dashboardsList).getText(), dashboardName + " " + selectedGame + " " + selectedCategory + " " + dateStr + "0");
	}
	
	@After
	public void closeTheBrowser() {
		login.close();
	}
}
