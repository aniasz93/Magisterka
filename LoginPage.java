package seleniumTestsProjektMagisterski;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	WebDriver webdriver;
	public LoginPage(WebDriver webdriver) {
		this.webdriver = webdriver;
	}
	
	public WebElement getUsernameField() {
		return webdriver.findElement(By.id("username"));
	}
	
	public WebElement getPasswordField() {
		return webdriver.findElement(By.id("password"));
	}
	
	public WebElement clickLoginButton() {
		return webdriver.findElement(By.xpath("//button[@class='btn btn-primary']"));
	}
	
	public String getCurrentUrl() {
		return webdriver.getCurrentUrl();
	}
	
	public void open(String url) {
		webdriver.get(url);
	}
	
	public void close() {
		webdriver.close();
	}
	
	public void loginUser(String username, String password) {
		this.getUsernameField().sendKeys(username);
		this.getPasswordField().sendKeys(password);
		this.clickLoginButton().submit();
	}
}
