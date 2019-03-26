package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ContactPage extends BasePage {

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    private By subjectHeadingSelect = By.id("id_contact");
    private By emailAddressInput = By.id("email");
    private By orderReferenceInput = By.id("id_order");
    private By attachFileInput = By.name("fileUpload");
    private By messageInput = By.id("message");
    private By sendButton = By.id("submitMessage");

    private By labelSuccess = By.xpath("//p[@class='alert alert-success']");
    private By labelErrorMessage = By.xpath("//div[@class='alert alert-danger']//ol//li");

    public WebElement getLabelSuccess() {
        return driver.findElement(labelSuccess);
    }

    public WebElement getLabelErrorMessage() {
        return driver.findElement(labelErrorMessage);
    }

    private void choseSubject(int subject) {
        Select dropdown = new Select(driver.findElement(subjectHeadingSelect));
        dropdown.selectByValue(String.valueOf(subject));
    }

    private void enterEmail(String email) {
        driver.findElement(emailAddressInput).sendKeys(email);
    }

    private void enterOrder(String order) {
        driver.findElement(orderReferenceInput).sendKeys(order);
    }

    private void attachFile(String filePath) {
        driver.findElement(attachFileInput).sendKeys(filePath);
    }

    private void enterMessage(String message) {
        driver.findElement(messageInput).sendKeys(message);
    }

    private void clickSendButton() {
        driver.findElement(sendButton).click();
    }

    @Step(value = "Отправка feedback: {subjectHeading} {email} {order} {pathFile} {message}")
    public void sendFeedback(int subjectHeading, String email, String order, String pathFile, String message) {
        choseSubject(subjectHeading);
        enterEmail(email);
        enterOrder(order);
        attachFile(pathFile);
        enterMessage(message);
        clickSendButton();
    }
}
