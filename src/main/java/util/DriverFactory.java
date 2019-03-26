package util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    static {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\driver\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\driver\\geckodriver.exe");
    }

    public DriverFactory() {
    }

    public static WebDriver createDriver(String browserName) {
        WebDriver driver;
        if (browserName.contentEquals("Chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com");

        return driver;
    }


//    public static WebDriver create(Browser type) {
//        WebDriver driver;
//        switch (type) {
//            case CHROME:
//                driver = new ChromeDriver();
//                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//                driver.manage().window().maximize();
//                driver.get(URL);
//
//                return driver;
//
//            case FIREFOX:
//                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//                desiredCapabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//
//                driver = new FirefoxDriver(desiredCapabilities);
//
//                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//                driver.manage().window().maximize();
//                driver.get(URL);
//
//                return driver;
//
//            /*case IE:
//                DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
//                ieCapabilities.setCapability(
//                        InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
//                        true
//                );
//                driver = new InternetExplorerDriver(ieCapabilities);
//                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                driver.manage().window().maximize();
//                driver.get(URL);
//
//                return driver;*/
//            default: {
//                throw new IllegalStateException("Invalid type browser");
//            }
//        }
//    }

    // Ожидание появления элемента
    public static void waitElement(WebDriver driver, By element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

        wait.until(ExpectedConditions.elementToBeClickable(element));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    // глобальные ожидания
    public static void addWaitScripts(WebDriver webDriver, int time) {
        waitForAngularToLoad(webDriver, time);
        waitForPrototypeToLoad(webDriver, time);
        waitFullPageLoading(webDriver, (long) time);
    }

    private static void waitFullPageLoading(WebDriver driver, Long time) {
        new WebDriverWait(driver, time, 200).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete");
            }
        });
    }

    private static void waitForPrototypeToLoad(WebDriver driver, int waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds, 2000L);

        // wait for jQuery to load
        ExpectedCondition<Boolean> libraryLoad = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                try {
                    return ((Boolean) ((JavascriptExecutor) driver).executeScript("return Ajax.activeRequestCount == 0;"));
                } catch (Exception e) {
                    // Prototype  not found
                    return true;
                }
            }
        };

        // wait for browser readystate complete; it is arguable if selenium does this all the time
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState;")
                        .toString().equals("complete");
            }
        };

        if (wait.until(libraryLoad)) {
            wait.until(jsLoad);
        }
    }

    private static void waitForAngularToLoad(WebDriver driver, int waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds, 2000L);

        ExpectedCondition<Boolean> libraryLoad = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                try {
                    return ((Boolean) ((JavascriptExecutor) driver).executeScript(
                            "return angular.element(document.body).injector().get(\'$http\').pendingRequests.length == 0;"
                    ));
                } catch (Exception e) {
                    // Angular not found
                    return true;
                }
            }
        };

        // wait for browser readystate complete; it is arguable if selenium does this all the time
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState;")
                        .toString().equals("complete");
            }
        };

        if (wait.until(libraryLoad)) {
            wait.until(jsLoad);
        }
    }
}
