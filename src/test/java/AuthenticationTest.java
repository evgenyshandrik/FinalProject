import asserts.AuthenticationAssert;
import asserts.MyAccountAssert;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.AuthenticationPage;
import pages.BasePage;
import pages.MyAccountPage;
import util.DriverFactory;

@Owner(value = "Evgen Shandrik")
public class AuthenticationTest {

    private WebDriver driver;
    private BasePage basePage;
    private MyAccountPage myAccountPage;
    private AuthenticationPage authenticationPage;

    @Parameters("browser")
    @BeforeMethod()
    public void initDriver(@Optional("Firefox") String browserName) {
        driver = DriverFactory.createDriver(browserName);

        basePage = new BasePage(driver);
        authenticationPage = new AuthenticationPage(driver);
        myAccountPage = new MyAccountPage(driver);
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    @Test(description = "MY-1 Verify the ability to register used email which was register")
    public void registrationUsedEmailTest() {
        basePage.clickSignIn();

        authenticationPage.firstStepForRegistration("1@test.test");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(authenticationPage.getErrorLabel().isDisplayed(), "Error message should be visible");
        softAssert.assertEquals(authenticationPage.getErrorLabel().getText(), AuthenticationAssert.textErrorRegistration,
                "Error message should be visible and equals to " + AuthenticationAssert.textErrorRegistration);

        softAssert.assertAll();
    }

    @Test(description = "MY-2 Successful Sign In")
    public void signInTest() {
        basePage.clickSignIn();

        authenticationPage.signIn("1@test.test", "admin_admin");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(myAccountPage.getMyAccountMenu().isDisplayed(),
                "Header element should be visible");
        softAssert.assertEquals(myAccountPage.getMyAccountLabel().getText(), MyAccountAssert.textMyAccount,
                "Header should be visible and equal to " + MyAccountAssert.textMyAccount);

        softAssert.assertAll();
    }

    @Test(description = "MY-3 Unsuccessful Sign In")
    public void unsuccessfulSignInTest() {
        basePage.clickSignIn();

        authenticationPage.signIn("1@test.test", "admin_admin1");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(authenticationPage.getErrorAuthLabel().isDisplayed(),
                "Error label should be visible");
        softAssert.assertEquals(authenticationPage.getErrorAuthLabel().getText(), AuthenticationAssert.textErrorAuth,
                "Error label should be equal to " + AuthenticationAssert.textErrorAuth);

        softAssert.assertAll();
    }

    @Test(description = "MY-4 Sign out")
    public void signOutTest() {
        basePage.clickSignIn();

        authenticationPage.signIn("1@test.test", "admin_admin");
        authenticationPage.clickSignOut();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(), AuthenticationAssert.textPageTitle,
                "PageTitle should be equals to " + AuthenticationAssert.textPageTitle);
        softAssert.assertTrue(authenticationPage.getHeadingLabel().isDisplayed(),
                "Heading should be visible");
        softAssert.assertEquals(authenticationPage.getHeadingLabel().getText(),
                AuthenticationAssert.textHeader, "Heading should be equals to " + AuthenticationAssert.textHeader);

        softAssert.assertAll();

    }
}
