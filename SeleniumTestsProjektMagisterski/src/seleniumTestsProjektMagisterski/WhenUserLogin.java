package seleniumTestsProjektMagisterski;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import seleniumTestsProjektMagisterski.Pages.DashboardsPage;
import seleniumTestsProjektMagisterski.Pages.LoginPage;

public class WhenUserLogin {

	private WebDriver webdriver;
	private LoginPage login;
	private DashboardsPage dashboards = new DashboardsPage(webdriver);
	
//	public WhenUserLogIn(WebDriver webdriver, String url) {
//		this.webdriver = webdriver;
//		this.loginPage = new LoginPage(this.webdriver);
//		this.url = url;
//	}
	
	@Before
	public void openTheBrowser() {
		//System.setProperty("webdriver.chrome.driver", "D:\\Uczelnia\\UAM\\Magisterka\\Projekt magisterski\\chromedriver_win32\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "D:\\Uczelnia\\UAM\\Magisterka\\Projekt magisterski\\geckodriver-v0.13.0-win64\\geckodriver.exe");
		webdriver = new FirefoxDriver();
		login = PageFactory.initElements(webdriver, LoginPage.class);
		login.open(login.getProperUrl());
	}
	
	@Test
	public void loginValidUser() {
		login.loginUser("admin", "admin");
		
		Assert.assertEquals(dashboards.getProperUrl(), webdriver.getCurrentUrl());
		System.out.println(webdriver.getCurrentUrl());
	}
	
	@Test
	public void loginInvalidUser() {
		login.loginUser("test", "test");
		
		Assert.assertTrue(login.errorMessageLabel.isDisplayed());
	}
	
	@Test
	public void loginWithoutCredentials() {
		Assert.assertTrue(!login.loginButton.isEnabled());
	}
	
	@Test
	public void loginOnlyWithUsername() {
		login.loginUser("admin", "");
		
		Assert.assertTrue(!login.loginButton.isEnabled());
	}
	
	@Test
	public void loginOnlyWithPassword() {
		login.loginUser("", "admin");
		
		Assert.assertTrue(!login.loginButton.isEnabled());
	}
	
	@After
	public void closeTheBrowser() {
		login.close();
	}
}
