package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderPage extends BasePage {

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    private By orderHistoryTable = By.xpath("//table[@id='order-list']//tbody");
    private By orderReferenceRow = By.xpath("//tr//td[@class='history_link bold footable-first-column']");

    private List<WebElement> getOrderHistory() {
        return driver.findElements(orderHistoryTable);
    }

    @Step(value = "Поиск заказа: {numberOrder}")
    public Boolean findReferenceOrder(String numberOrder) {
        List<WebElement> listItem = getOrderHistory();

        for (WebElement item : listItem) {
            if (item.findElement(orderReferenceRow).getText().contains(numberOrder)) {
                return true;
            }
        }
        return false;
    }
}
