import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import util.DriverFactory;

@Owner(value = "Evgen Shandrik")
public class OrderTest {

    private WebDriver driver;
    private BasePage basePage;
    private MyAccountPage myAccountPage;
    private AuthenticationPage authenticationPage;
    private SearchPage searchPage;
    private ShoppingCartPage shoppingCartPage;
    private OrderPage orderPage;

    @Parameters("browser")
    @BeforeMethod()
    public void initDriver(@Optional("Chrome") String browserName) {
        driver = DriverFactory.createDriver(browserName);

        basePage = new BasePage(driver);
        myAccountPage = new MyAccountPage(driver);
        authenticationPage = new AuthenticationPage(driver);
        searchPage = new SearchPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        orderPage = new OrderPage(driver);
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    @Test(description = "E-7 Proceed To Checkout", dataProvider = "itemsData")
    public void doOrder(String itemOrder) {
        basePage.clickSignIn();

        authenticationPage.signIn("1@test.test", "admin_admin");

        basePage.search(itemOrder);

        searchPage.clickAddToCart();
        searchPage.clickProceedToCheckout();

        shoppingCartPage.findItemFromShoppingCart(itemOrder);

        String link = shoppingCartPage.doOrder();

        basePage.clickSignIn();

        myAccountPage.clickOrderHistory();

        Assert.assertTrue(orderPage.findReferenceOrder(link), "Order reference should be in order history");
    }

    @DataProvider
    public Object[][] itemsData() {
        return new Object[][]{
                {"Blouse"},
                {"Printed Summer Dress"},
                {"Faded Short Sleeve T-shirts"}
        };
    }

}
