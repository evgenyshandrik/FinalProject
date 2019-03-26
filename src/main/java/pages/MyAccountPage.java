package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    private By myAccountMenu = By.xpath("//div[@class='row addresses-lists']");
    private By myAccountLabel = By.xpath("//h1[@class='page-heading']");
    private By orderHistoryButton = By.xpath("//a[@title='Orders']");

    public WebElement getMyAccountMenu() {
        return driver.findElement(myAccountMenu);
    }

    public WebElement getMyAccountLabel() {
        return driver.findElement(myAccountLabel);
    }

    private WebElement getOrderHistoryButton() {
        return driver.findElement(orderHistoryButton);
    }

    public void clickOrderHistory() {
        new Actions(driver).moveToElement(getOrderHistoryButton()).click().build().perform();
    }

}
