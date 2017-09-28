package seleniumTestsProjektMagisterski;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import seleniumTestsProjektMagisterski.Pages.DashboardsPage;
import seleniumTestsProjektMagisterski.Pages.LoginPage;

public class WhenUserAddsNewMatches {

	private WebDriver webdriver;
	public String browser = "Chrome";
	private LoginPage login = new LoginPage(webdriver);
	private DashboardsPage dashboards = new DashboardsPage(webdriver);
	
	List<WebElement> matchesList;
	String modus;
	Select select;
	int matchesNumb;
	int newMatchesNumb;
	
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
	public void addsNewMatchProperly() {
		modus = "3";
		matchesNumb = 0;
		newMatchesNumb = 0;

		try {
			login.loginUser("admin", "admin");
	
			Thread.sleep(500);
			
			dashboards.getFirstRowInDashoardsTable().click();

			Thread.sleep(500);
			matchesList = dashboards.getMatchesTable();
			Thread.sleep(500);
			matchesNumb = matchesList.size();
			
			((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView(true);", dashboards.getNewMatchButton());
			Thread.sleep(500); 
			dashboards.getNewMatchButton().click();
			dashboards.newModusField.sendKeys(modus);
			
			select = new Select(dashboards.playerADropdown);
			//select.deselectAll();
			select.selectByIndex(1);
			String selectedPlayerA = select.getFirstSelectedOption().getText();
			
			select = new Select(dashboards.playerBDropdown);
			//select.deselectAll();
			select.selectByIndex(2);
			String selectedPlayerB = select.getFirstSelectedOption().getText();
			
			dashboards.getSaveNewMatchButton().click();
			
			webdriver.navigate().refresh();
			dashboards.getFirstRowInDashoardsTable().click();
			((JavascriptExecutor)webdriver).executeScript("arguments[0].scrollHeight;", dashboards.getMatchesTable());

			Thread.sleep(500);
			matchesList = dashboards.getMatchesTable();
			Thread.sleep(500);
			newMatchesNumb = matchesList.size();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Assert.assertEquals(newMatchesNumb, matchesNumb + 1);
	}
	
	@After
	public void closeTheBrowser() {
		login.close();
	}
}
