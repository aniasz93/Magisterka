package seleniumTestsProjektMagisterski.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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
	
//	public WebElement getNewGameDropdown() {
//		return webdriver.findElement(By.cssSelector("select[ng-model='$dashboards.game']"));
//	}
	
	@FindBy(id="gametype")
	public WebElement getNewGameDropdown;
	
	public List<WebElement> getGamesListInDropdown() {
		Select select = new Select(getNewGameDropdown);
		return select.getOptions();
	}
	
	@FindBy(id="category")
	public WebElement getNewCategoryDropdown;
	
	public List<WebElement> getCategoriesListInDropdown() {
		Select select = new Select(getNewCategoryDropdown);
		return select.getOptions();
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
	
	public List<WebElement> getDashboardsTable() {		
		return webdriver.findElements(By.cssSelector("div[ng-init='$dashboards.list()'] > table > tbody > tr"));
	}
	
	public WebElement getFirstDashboard(List<WebElement> list) {
		return list.get(0);
	}
}
