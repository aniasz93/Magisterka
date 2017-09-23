package seleniumTestsProjektMagisterski;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
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
		login.open(login.getProperUrl());
	}
	
	@Test
	public void addNewGameProperly() {
		login.loginUser("admin", "admin");
		dashboards.findTab("settings()");
		settings.getNewGameButton().click();

		System.out.println("yo:D");
	}
	
	@After
	public void closeTheBrowser() {
		login.close();
	}
}
