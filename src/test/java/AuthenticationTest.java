import asserts.AuthenticationAssert;
import asserts.MyAccountAssert;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.AuthenticationPage;
import pages.MainPage;
import pages.MyAccountPage;
import util.DriverFactory;

@Owner(value = "Evgen Shandrik")
public class AuthenticationTest {

    private WebDriver driver;
    private MainPage mainPage;
    private AuthenticationPage authenticationPage;

    @Parameters("browser")
    @BeforeMethod()
    public void initDriver(@Optional("Firefox") String browserName) {
        driver = DriverFactory.createDriver(browserName);

        mainPage = new MainPage(driver);
        authenticationPage = new AuthenticationPage(driver);
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    @Test(description = "MY-1 Verify the ability to register used email which was register")
    public void registrationUsedEmailTest() {
        mainPage.clickSignIn();
        authenticationPage.firstStepForRegistration("1@test.test");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(authenticationPage.getErrorLabel().isDisplayed(), "Error message should be visible");
        softAssert.assertEquals(authenticationPage.getErrorLabel().getText(), AuthenticationAssert.textErrorRegistration,
                "Error message should be equals to " + AuthenticationAssert.textErrorRegistration);

        softAssert.assertAll();
    }

    @Test(description = "MY-2 Successful Sign In")
    public void signInTest() {
        mainPage.clickSignIn();
        authenticationPage.signIn("1@test.test", "admin_admin");

        MyAccountPage myAccountPage = new MyAccountPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(myAccountPage.getMyAccountMenu().isDisplayed(),
                "Header element should be visible");
        softAssert.assertEquals(myAccountPage.getMyAccountLabel().getText(), MyAccountAssert.textMyAccount,
                "Header should be equal to " + MyAccountAssert.textMyAccount);

        softAssert.assertAll();
    }

    @Test(description = "MY-3 Sign In with incorrect password")
    public void unsuccessfulSignInTest() {
        mainPage.clickSignIn();
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
        mainPage.clickSignIn();
        authenticationPage.signIn("1@test.test", "admin_admin");

        int countPanelBefore = authenticationPage.getPanelNavigator().size();

        authenticationPage.clickSignOut();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(authenticationPage.getPanelNavigator().size(), countPanelBefore,
                "Count blocks of panel navigator after log out should be different with count block before log out");

        softAssert.assertEquals(authenticationPage.getBlockOfNickname().getText(), "Sign in", "Block of panel should be equals Sign in");

        softAssert.assertAll();
    }
}
