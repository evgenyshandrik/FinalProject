import asserts.ContactAssert;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ContactPage;
import pages.MainPage;
import util.DriverFactory;

import java.io.File;

@Owner(value = "Evgen Shandrik")
public class ContactTest {

    private WebDriver driver;
    private MainPage mainPage;
    private ContactPage contactPage;
    private File file;

    @Parameters("browser")
    @BeforeMethod()
    public void initDriver(@Optional("Chrome") String browserName) {
        driver = DriverFactory.createDriver(browserName);

        file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\test.txt");

        mainPage = new MainPage(driver);
        contactPage = new ContactPage(driver);
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    @Test(description = "E-1 Verify that contact us form sends successfully")
    public void sendSuccessfullyFeedbackTest() {
        mainPage.clickContactUs();
        contactPage.sendFeedback(1, "1@test.test", "order", file.getPath(), "Message");

        Assert.assertEquals(contactPage.getLabelSuccess().getText(), ContactAssert.textMessageSuccess, "Successful message should be visible");
    }

    @Test(description = "E-2 Verify that error message appears if Message area is empty")
    public void emptyMessageFeedbackTest() {
        mainPage.clickContactUs();
        contactPage.sendFeedback(1, "1@test.test", "order", file.getPath(), "");

        Assert.assertEquals(contactPage.getLabelErrorMessage().getText(), ContactAssert.textErrorMessage, "Error message should be visible");
    }
}
