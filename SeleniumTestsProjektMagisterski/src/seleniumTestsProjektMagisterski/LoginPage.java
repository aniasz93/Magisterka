package seleniumTestsProjektMagisterski;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver webdriver;
	public LoginPage(WebDriver webdriver) {
		this.webdriver = webdriver;
	}
	
	@FindBy(id="username")
	private WebElement usernameField;
	
	@FindBy(id="password")
	private WebElement passwordField;
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	private WebElement loginButton;
	
	@FindBy(id="errorMessage")
	public WebElement errorMessageLabel;
	
	public void open(String url) {
		webdriver.get(url);
	}
	
	public void close() {
		webdriver.close();
	}
	
	public void loginUser(String username, String password) {
		this.usernameField.sendKeys(username);
		this.passwordField.sendKeys(password);
		this.loginButton.submit();
	}
}
