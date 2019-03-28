package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    private By listItemTable = By.xpath("//table//tbody//tr");
    private By descriptionItemRow = By.xpath(".//p[@class='product-name']");
    private By deleteItemRow = By.xpath(".//i[@class='icon-trash']");
    private By messageEmpty = By.xpath("//p[@class='alert alert-warning']");
    private By nextStepSummaryButton = By.xpath("//p[@class='cart_navigation clearfix']//a[@title='Proceed to checkout']");
    private By nextStepAddressButton = By.name("processAddress");
    private By termsOfServiceCheckbox = By.id("uniform-cgv");
    private By nextStepShippingButton = By.name("processCarrier");
    private By payByBankWireDiv = By.xpath("//div[@class='paiement_block']//div[1]//div[1]");
    private By donePaymentButton = By.xpath("//p[@id='cart_navigation']//button[@type='submit']");
    private By infoAfterSuccessfulOrderDiv = By.className("box");

    public By getByMessageEmpty() {
        return messageEmpty;
    }

    public WebElement getElementMessageEmpty() {
        return driver.findElement(getByMessageEmpty());
    }

    private void clickNextStepSummaryButton() {
        driver.findElement(nextStepSummaryButton).click();
    }

    private void clickNextStepAddressButton() {
        driver.findElement(nextStepAddressButton).click();
    }

    private void clickTermsOfServiceCheckbox() {
        driver.findElement(termsOfServiceCheckbox).click();
    }

    private void clickNextStepShippingButton() {
        driver.findElement(nextStepShippingButton).click();
    }

    private void clickPayByBankWireDiv() {
        driver.findElement(payByBankWireDiv).click();
    }

    private void clickDonePaymentButton() {
        driver.findElement(donePaymentButton).click();
    }

    private WebElement getInfoAfterSuccessfulOrderDiv() {
        return driver.findElement(infoAfterSuccessfulOrderDiv);
    }

    @Step(value = "Получить список товаров в корзине")
    private List<WebElement> getListItem() {
        return driver.findElements(listItemTable);
    }

    @Step(value = "Поиск товара в корзине: {itemName}")
    public WebElement findItemFromShoppingCart(String itemName) {
        new Actions(driver).moveToElement(getListItem().get(0)).build().perform();
        List<WebElement> listItem = getListItem();

        for (WebElement item : listItem) {
            if (item.findElement(descriptionItemRow).getText().contains(itemName)) {
                return item;
            }
        }
        return null;
    }

    @Step(value = "Удаление товара из корзины: {itemName}")
    public void deleteItemFromCart(String itemName) {
        WebElement itemForDelete = findItemFromShoppingCart(itemName);
        itemForDelete.findElement(deleteItemRow).click();
    }

    @Step(value = "Оформление заказа")
    public String doOrder() {
        clickNextStepSummaryButton();
        clickNextStepAddressButton();
        clickTermsOfServiceCheckbox();
        clickNextStepShippingButton();
        clickPayByBankWireDiv();
        clickDonePaymentButton();

        return getOrderLink(getInfoAfterSuccessfulOrderDiv().getText());
    }

    private String getOrderLink(String text) {
        String[] tmp = text.split("order reference ");
        String[] linkOrder = tmp[1].split(" ");
        return linkOrder[0];
    }
}