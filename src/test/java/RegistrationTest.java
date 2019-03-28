import asserts.MyAccountAssert;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import util.DriverFactory;
import util.Randomizer;

@Owner(value = "Evgen Shandrik")
public class RegistrationTest {

    private WebDriver driver;
    private MainPage mainPage;
    private MyAccountPage myAccountPage;
    private AuthenticationPage authenticationPage;
    private RegistrationPage registrationPage;

    @Parameters("browser")
    @BeforeMethod()
    public void initDriver(@Optional("Chrome") String browserName) {
        driver = DriverFactory.createDriver(browserName);

        mainPage = new MainPage(driver);
        authenticationPage = new AuthenticationPage(driver);
        registrationPage = new RegistrationPage(driver);
        myAccountPage = new MyAccountPage(driver);
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    @Test(description = "E-3 Verify the ability to register")
    public void registrationSuccessfulTest() {
        mainPage.clickSignIn();
        authenticationPage.firstStepForRegistration(Randomizer.randomEmail(2, 10));

        registrationPage.registration(Randomizer.randomFirstName(), Randomizer.randomLastName(),
                Randomizer.randomString(6, 8, true), Randomizer.randomAddress(), Randomizer.randomString(6, 10, false),
                Randomizer.randomInt(1, 53).toString(), Randomizer.randomInt(10000, 99999).toString(), 21, Randomizer.randomPhone(), Randomizer.randomString(5, 10, true));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(myAccountPage.getMyAccountMenu().isDisplayed(), "Header element should be visible");
        softAssert.assertEquals(myAccountPage.getMyAccountLabel().getText(), MyAccountAssert.textMyAccount, "Header should be visible and equal to " + MyAccountAssert.textMyAccount);

        softAssert.assertAll();
    }
}
