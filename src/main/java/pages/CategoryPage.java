package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryPage extends BasePage {

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    private By headingProduct = By.className("cat-name");

    public WebElement getHeadingProduct() {
        return driver.findElement(headingProduct);
    }
}
