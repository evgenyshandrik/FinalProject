package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private By firstNameInput = By.name("customer_firstname");
    private By lastNameInput = By.name("customer_lastname");
    private By emailInput = By.id("email");
    private By passwordInput = By.id("passwd");
    private By addressInput = By.id("address1");
    private By cityInput = By.id("city");
    private By stateSelect = By.id("id_state");
    private By postCodeInput = By.id("postcode");
    private By countrySelect = By.id("id_country");
    private By phoneMobileInput = By.id("phone_mobile");
    private By aliasInput = By.id("alias");
    private By registerButton = By.id("submitAccount");

    private void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    private void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    private void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    private void enterAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    private void enterCity(String city) {
        driver.findElement(cityInput).sendKeys(city);
    }

    private void choseState(String state) {
        Select dropdown = new Select(driver.findElement(stateSelect));
        dropdown.selectByValue(state);
    }

    private void enterPostcode(String postcode) {
        driver.findElement(postCodeInput).sendKeys(postcode);
    }

    private void choseCountry(int country) {
        Select dropdown = new Select(driver.findElement(countrySelect));
        dropdown.selectByValue(String.valueOf(country));
    }

    private void enterPhoneMobile(String phoneMobile) {
        driver.findElement(phoneMobileInput).sendKeys(phoneMobile);
    }

    private void enterAlias(String alias) {
        driver.findElement(aliasInput).sendKeys(alias);
    }

    private void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step(value = "Регистрация пользователя - заполнение формы регистрации: {firstName} {lastName} {password} {address} {city} {state} {postCode} {country} {phoneMobile} {alias}")
    public void registration(String firstName, String lastName, String password, String address,
                             String city, String state, String postCode, int country, String phoneMobile, String alias) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPassword(password);
        enterAddress(address);
        enterCity(city);
        choseState(state);
        enterPostcode(postCode);
        choseCountry(country);
        enterPhoneMobile(phoneMobile);
        enterAlias(alias);
        clickRegisterButton();
    }
}
