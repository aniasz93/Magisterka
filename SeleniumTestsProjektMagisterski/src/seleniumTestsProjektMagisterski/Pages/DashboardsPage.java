package seleniumTestsProjektMagisterski.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardsPage {

	WebDriver webdriver;
	public DashboardsPage(WebDriver webdriver) {
		this.webdriver = webdriver;
	}
	
	public String getProperUrl() {
		return "http://localhost:8080/login#!/dashboards";
	}
	
	public WebElement findTab(String tab) {
		return webdriver.findElement(By.cssSelector("button[ng-click='" + tab + "']"));
	}
	
	public WebElement getNewDashboardButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='$dashboards.addDashboardPopover.open()']"));
	}
	
	public WebElement getNewDashboardNameField() {
		return webdriver.findElement(By.cssSelector("input[ng-model='$dashboards.dashboardName']"));
	}
	
	public WebElement getNewGameDropdown() {
		return webdriver.findElement(By.cssSelector("select[ng-model='$dashboards.game']"));
	}
	
	public WebElement getNewCategoryDropdown() {
		return webdriver.findElement(By.cssSelector("input[ng-model='$dashboards.category']"));
	}
	
	public WebElement getCloseNewCategoryButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='$dashboards.addDashboardPopover.close()']"));
	}
	
	public WebElement getSaveNewCategoryButton() {
		return webdriver.findElement(By.cssSelector("button[ng-click='$dashboards.createDashboard(); $dashboards.addDashboardPopover.close()']"));
	}
}
