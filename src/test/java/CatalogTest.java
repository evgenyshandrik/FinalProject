import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CategoryPage;
import pages.MainPage;
import util.DriverFactory;

@Owner(value = "Evgen Shandrik")
public class CatalogTest {

    private WebDriver driver;
    private MainPage mainPage;
    private CategoryPage categoryPage;

    @Parameters("browser")
    @BeforeMethod()
    public void initDriver(@Optional("Chrome") String browserName) {
        driver = DriverFactory.createDriver(browserName);

        mainPage = new MainPage(driver);
        categoryPage = new CategoryPage(driver);
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    @Test(description = "E-6 Catalog T-SHIRT Test")
    public void checkCatalogTShirt() {
        mainPage.clickMenuTShirt();

        Assert.assertTrue(categoryPage.getHeadingProduct().getText().contentEquals("T-SHIRTS "), "Header of catalog should be visible");
    }
}
