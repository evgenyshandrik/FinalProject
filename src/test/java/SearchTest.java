import asserts.ShoppingCartAssert;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import util.DriverFactory;

@Owner(value = "Evgen Shandrik")
public class SearchTest {

    private WebDriver driver;
    private BasePage basePage;
    private SearchPage searchPage;

    @Parameters("browser")
    @BeforeMethod()
    public void initDriver(@Optional("Chrome") String browserName) {
        driver = DriverFactory.createDriver(browserName);

        basePage = new BasePage(driver);
        searchPage = new SearchPage(driver);
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    @Test(description = "E-4 Verify the ability to search items")
    public void searchTest() {
        String searchElement = "Blouse";
        basePage.search(searchElement);

        Assert.assertTrue(searchPage.getResultSearchElementName().getText().contains(searchElement),
                "Name of the resulting element should be equal to " + searchElement);
    }

    @Test(description = "E-5 Verify the ability to add and delete items from cart", dataProvider = "itemsData")
    public void checkShoppingCart(String searchItem) {
        basePage.search(searchItem);

        searchPage.clickAddToCart();
        searchPage.clickProceedToCheckout();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        WebElement element = shoppingCartPage.findItemFromShoppingCart(searchItem);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(element, "Element should be display at shopping cart");

        shoppingCartPage.deleteItemFromCart(searchItem);

        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartPage.getByMessageEmpty()));

        softAssert.assertEquals(shoppingCartPage.getElementMessageEmpty().getText(), ShoppingCartAssert.textMessageWarning, "Element should be delete from shopping cart");

        softAssert.assertAll();
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
