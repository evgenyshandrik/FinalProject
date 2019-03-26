import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BasePage;
import pages.CategoryPage;
import util.DriverFactory;

@Owner(value = "Evgen Shandrik")
public class CatalogTest {

    private WebDriver driver;
    private BasePage basePage;
    private CategoryPage categoryPage;
    private static final String catalogName = "T-SHIRTS ";

    @Parameters("browser")
    @BeforeMethod()
    public void initDriver(@Optional String browserName) {
        driver = DriverFactory.createDriver(browserName);

        basePage = new BasePage(driver);
        categoryPage = new CategoryPage(driver);
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    @Test(description = "E-6 Catalog T-SHIRT Test (1)")
    public void checkCatalogTShirtHardTest() {
        basePage.clickMenuTShirtHard();

        Assert.assertTrue(categoryPage.getHeadingProduct().getText().contentEquals(catalogName), "Header of catalog should be visible");
    }

    @Test(description = "E-6 Catalog T-SHIRT Test (2)")
    public void checkCatalogTShirtSimpleTest() {
        basePage.clickMenuTShirtSimple();

        Assert.assertTrue(categoryPage.getHeadingProduct().getText().contentEquals(catalogName), "Header of catalog should be visible");
    }
}
