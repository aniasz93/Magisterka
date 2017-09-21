package seleniumTestsProjektMagisterski;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class WhenUserLogIn {

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
		loginPage = new LoginPage(webdriver);
		loginPage.open(mainpage);
	}
	
	@Test
	public void logInValidUser() {
		loginPage.loginUser("admin", "admin");
		
		Assert.assertEquals("http://localhost:8080/login#!/dashboards", loginPage.getCurrentUrl());
		System.out.println(loginPage.getCurrentUrl());
	}
	
	@After
	public void closeTheBrowser() {
		loginPage.close();
	}
}
