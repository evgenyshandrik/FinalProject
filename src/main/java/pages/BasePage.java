package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

    private By womenMainMenu = By.xpath("//a[@title='Women']");
    private By tShirtsItemMenu = By.xpath("//li[@class='sfHover']//a[@title='T-shirts']");
    private By tShirtsMainMenu = By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[3]");

    public void clickContactUs() {
        driver.findElement(contactUsButton).click();
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }

    public void clickSignOut() {
        new Actions(driver).moveToElement(getSignOut()).click().build().perform();
    }

    private WebElement getSignOut() {
        return driver.findElement(signOutButton);
    }

    private WebElement getWomenMenu() {
        return driver.findElement(womenMainMenu);
    }

    private WebElement getTShirtsItemMenu() {
        return driver.findElement(tShirtsItemMenu);
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
    public void clickMenuTShirtSimple() {
        driver.findElement(tShirtsMainMenu).click();
    }

    @Step(value = "Нажатие на подменю T-Shirt")
    public void clickMenuTShirtHard() {
        new Actions(driver).moveToElement(getWomenMenu()).build().perform();
        new Actions(driver).moveToElement(getTShirtsItemMenu()).click().build().perform();
    }


}

