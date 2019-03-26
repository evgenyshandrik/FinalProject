package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AuthenticationPage extends BasePage {

    public AuthenticationPage(WebDriver driver) {
        super(driver);
    }

    private By emailInput = By.id("email");
    private By passwordInput = By.id("passwd");
    private By signInButton = By.id("SubmitLogin");

    private By emailAddressForCreateInput = By.id("email_create");
    private By createAccountButton = By.xpath("//form[@id='create-account_form']//span[1]");

    private By errorLabel = By.xpath("//div[@id='create_account_error']/ol/li");
    private By errorAuthLabel = By.xpath("//div[@id='center_column']/div[@class='alert alert-danger']/ol/li");

    private By headingLabel = By.xpath("//h1[@class='page-heading']");

    public WebElement getErrorLabel() {
        return driver.findElement(errorLabel);
    }

    public WebElement getErrorAuthLabel() {
        return driver.findElement(errorAuthLabel);
    }

    public WebElement getHeadingLabel() {
        return driver.findElement(headingLabel);
    }

    private void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    private void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    private void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    private void enterEmailAddressForCreate(String email) {
        driver.findElement(emailAddressForCreateInput).sendKeys(email);
    }

    private void clickCreateAccountButton() {
        driver.findElement(createAccountButton).click();
    }

    @Step(value = "Ввод e-mail для регистрации нового пользователя: {email}")
    public void firstStepForRegistration(String email) {
        enterEmailAddressForCreate(email);
        clickCreateAccountButton();
    }

    @Step(value = "Авторизация пользователя: {email} {password}")
    public void signIn(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignInButton();
    }
}
