package seleniumTestsProjektMagisterski;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;

public class WhenUserLogin {

	private WebDriver webdriver;
	private LoginPage loginPage;
	String mainpage = "http://localhost:8080/login#!/";
	
//	public WhenUserLogIn(WebDriver webdriver, String url) {
//		this.webdriver = webdriver;
//		this.loginPage = new LoginPage(this.webdriver);
//		this.url = url;
//	}
	
	@Before
	public void openTheBrowser() {
		System.setProperty("webdriver.chrome.driver", "D:\\Uczelnia\\UAM\\Magisterka\\Projekt magisterski\\chromedriver_win32\\chromedriver.exe");
		webdriver = new ChromeDriver();
		loginPage = PageFactory.initElements(webdriver, LoginPage.class);//new LoginPage(webdriver);
		loginPage.open(mainpage);
	}
	
	@Test
	public void loginValidUser() {
		loginPage.loginUser("admin", "admin");
		
		Assert.assertEquals("http://localhost:8080/login#!/dashboards", webdriver.getCurrentUrl());
		System.out.println(webdriver.getCurrentUrl());
	}
	
	@Test
	public void loginInvalidUser() {
		loginPage.loginUser("test", "test");
		
		Assert.assertTrue(loginPage.errorMessageLabel.isDisplayed());
	}
	
	@Test
	public void loginWithoutCredentials() {
		loginPage.loginUser("", "");
		
		Assert.assertTrue(!loginPage.loginButton.isEnabled());
	}
	
	@After
	public void closeTheBrowser() {
		loginPage.close();
	}
}
