package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryPage extends BasePage {

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    private By headingProduct = By.xpath("//span[@class='cat-name']");

    public WebElement getHeadingProduct(){
        return driver.findElement(headingProduct);
    }
}