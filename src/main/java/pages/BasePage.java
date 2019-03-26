package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    private By contactUsButton = By.xpath("//div[@id='contact-link']/a");
    private By signInButton = By.className("header_user_info");
    private By searchInput = By.id("search_query_top");
    private By searchButton = By.name("submit_search");
    private By signOutButton = By.xpath("//ul[@class='bullet']/li[5]/a");
    private By panelNavigator = By.xpath("//div[@class='nav']//div");
    private By blockOfNickname = By.xpath("//div[@class='nav']//div[@class='row']//div[1]");
    private By tShirtsMainMenu = By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[3]");

    public WebElement getBlockOfNickname() {
        return driver.findElement(blockOfNickname);
    }

    public List<WebElement> getPanelNavigator() {
        return driver.findElements(panelNavigator);
    }

    public void clickContactUs() {
        driver.findElement(contactUsButton).click();
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }

    public void clickSignOut() {
        WebElement myElement = driver.findElement(signOutButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
        myElement.click();
    }

    private void enterTextForSearch(String searchValue) {
        new Actions(driver).moveToElement(driver.findElement(searchInput)).click().sendKeys(searchValue).build().perform();
    }

    private void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    @Step(value = "Поиск товара: {itemName}")
    public void search(String itemName) {
        enterTextForSearch(itemName);
        clickSearchButton();
    }

    @Step(value = "Нажатие на меню T-Shirt")
    public void clickMenuTShirt() {
        driver.findElement(tShirtsMainMenu).click();
    }
}

