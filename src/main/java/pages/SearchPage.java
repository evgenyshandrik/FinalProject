package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    private By resultSearchElementName = By.xpath("//div[@class='product-container']/div[@class='right-block']/h5");
    private By addToCartButton = By.xpath("//div[@class='right-block']/div[@class='button-container']/a[@class='button ajax_add_to_cart_button btn btn-default']");
    private By proceedToCheckoutButton = By.xpath("//div[@class='button-container']//a[@class='btn btn-default button button-medium']");
    private By resultSearchElementImage = By.xpath("//div[@class='product-image-container']//a[@class='product_img_link']");

    public WebElement getResultSearchElementName() {
        return driver.findElement(resultSearchElementName);
    }

    private WebElement getAddToCartButton() {
        return driver.findElement(addToCartButton);
    }

    private WebElement getResultSearchElementImage() {
        return driver.findElement(resultSearchElementImage);
    }

    @Step(value = "Добавление товара в корзину")
    public void clickAddToCart() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", getResultSearchElementImage());
        new Actions(driver).moveToElement(getResultSearchElementImage()).build().perform();
        new Actions(driver).moveToElement(getAddToCartButton()).click().build().perform();
    }

    @Step(value = "Переход к корзине из модального окна товара")
    public void clickProceedToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(proceedToCheckoutButton));

        driver.findElement(proceedToCheckoutButton).click();
    }
}