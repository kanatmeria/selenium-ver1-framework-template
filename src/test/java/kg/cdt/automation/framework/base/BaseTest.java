//
//package org.example.base;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.jupiter.api.*;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public abstract class BaseTest {
//
//    protected WebDriver driver;
//    protected WebDriverWait wait;
//    protected static final Logger logger = Logger.getLogger(BaseTest.class.getName());
//
//    protected void initDriver(String browser) {
//        final String browserName = browser.toLowerCase();
//        logger.info(() -> "Initializing browser: " + browserName);
//
//        try {
//            switch (browserName) {
//                case "firefox":
//                    WebDriverManager.firefoxdriver().setup();
//                    FirefoxOptions optionsFirefox = new FirefoxOptions();
//                    optionsFirefox.setBinary("C:\\Users\\User\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
//                    driver = new FirefoxDriver(optionsFirefox);
//                    break;
//
//                case "edge":
//                    WebDriverManager.edgedriver().setup();
//                    driver = new EdgeDriver();
//                    break;
//
//                case "chrome":
//                default:
//                    WebDriverManager.chromedriver().setup();
//                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("--start-maximized");
//                    options.addArguments("--disable-notifications");
//                    options.addArguments("--remote-allow-origins=*");
//                    driver = new ChromeDriver(options);
//                    break;
//            }
//
//            driver.manage().window().maximize();
//            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "Failed to initialize browser: " + browser, e);
//            throw e;
//        }
//    }
//
//    @BeforeAll
//    void beforeAllTests() {
//        logger.info("===== Test Suite Started =====");
//    }
//
//    @BeforeEach
//    void beforeEachTest() {
//        String browser = System.getProperty("browser", "chrome"); // default to Chrome
//        initDriver(browser);
//    }
//
//    @AfterEach
//    void tearDown(TestInfo testInfo) {
//        if (driver != null) {
//            logger.info(() -> "Closing browser after test: " + testInfo.getDisplayName());
//            driver.quit();
//        }
//    }
//
//    @AfterAll
//    void afterAllTests() {
//        logger.info("===== Test Suite Finished =====");
//    }
//}

//
//package org.example.base;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.example.utils.ConfigReader;
//import org.junit.jupiter.api.*;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public abstract class BaseTest {
//
//    protected WebDriver driver;
//    protected WebDriverWait wait;
//    protected static final Logger logger = Logger.getLogger(BaseTest.class.getName());
//    private boolean testFailed = false; // Флаг, чтобы знать — тест упал или нет
//
//
//    protected void initDriver(String browser) {
//        final String browserName = browser.toLowerCase();
//        logger.info(() -> "Initializing browser: " + browserName);
//
//        try {
//            switch (browserName) {
//                case "firefox":
//                    WebDriverManager.firefoxdriver().setup();
//                    FirefoxOptions optionsFirefox = new FirefoxOptions();
//                    optionsFirefox.setBinary("C:\\Users\\User\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
//                    driver = new FirefoxDriver(optionsFirefox);
//                    break;
//
//                case "edge":
//                    WebDriverManager.edgedriver().setup();
//                    driver = new EdgeDriver();
//                    break;
//
//                case "chrome":
//                default:
//                    WebDriverManager.chromedriver().setup();
//                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("--start-maximized");
//                    options.addArguments("--disable-notifications");
//                    options.addArguments("--remote-allow-origins=*");
//                    driver = new ChromeDriver(options);
//                    break;
//            }
//
//            driver.manage().window().maximize();
//            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//            // Automatically open base URL from config
//            String baseUrl = ConfigReader.get("base.url");
//            if (baseUrl != null && !baseUrl.isEmpty()) {
//                driver.get(baseUrl);
//                logger.info(() -> "Navigated to: " + baseUrl);
//            }
//
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "Failed to initialize browser: " + browser, e);
//            throw e;
//        }
//    }
//
//    @BeforeAll
//    void beforeAllTests() {
//        logger.info("===== Test Suite Started =====");
//        logger.info("Environment: " + ConfigReader.getEnv());
//    }
//
//    @BeforeEach
//    void beforeEachTest() {
//        String browser = System.getProperty("browser", ConfigReader.get("browser"));
//        if (browser == null || browser.isEmpty()) {
//            browser = "chrome"; // fallback
//        }
//        initDriver(browser);
//
//    }
//
//    @AfterEach
//    void tearDown(TestInfo testInfo) {
//        if (driver != null) {
//            logger.info(() -> "Closing browser after test: " + testInfo.getDisplayName());
//            driver.quit();
//        }
//    }
//
//    @AfterAll
//    void afterAllTests() {
//        logger.info("===== Test Suite Finished =====");
//    }
//}

package kg.cdt.automation.framework.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import kg.cdt.automation.framework.utils.ConfigReader;
import kg.cdt.automation.framework.listeners.TestListener;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

@ExtendWith(TestListener.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class BaseTest {

    protected static WebDriver driver; // статический, чтобы TestListener видел
    protected WebDriverWait wait;
    protected static final Logger logger = Logger.getLogger(BaseTest.class.getName());

    // --- Доступ для TestListener ---
    public static WebDriver getDriver() {
        return driver;
    }

    protected void initDriver(String browser) {
        String browserName = browser.toLowerCase();
        logger.info(() -> "Initializing browser: " + browserName);

        switch (browserName) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary("C:\\Users\\User\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--window-size=1920,1080");
//                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--remote-allow-origins=*");
                // chromeOptions.addArguments("--headless=new"); // включить для ускорения без GUI
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // увеличенный таймаут для динамики
    }

    @BeforeAll
    void beforeAllTests() {
        logger.info("===== Test Suite Started =====");
        logger.info("Environment: " + ConfigReader.getEnv());

        String browser = System.getProperty("browser", ConfigReader.get("browser"));
        if (browser == null || browser.isEmpty()) browser = "chrome";
        initDriver(browser);

        // Открываем базовую страницу один раз
        String baseUrl = ConfigReader.get("base.url");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            driver.get(baseUrl);
            logger.info(() -> "Navigated to: " + baseUrl);
        }
    }

    @BeforeEach
    void beforeEachTest(TestInfo testInfo) {
        // Для каждого теста просто обновляем базовую страницу
        String baseUrl = ConfigReader.get("base.url");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            driver.get(baseUrl);
        }
        logger.info(() -> "🧪 Starting test: " + testInfo.getDisplayName());
    }

    @AfterAll
    void afterAllTests() {
        if (driver != null) {
            driver.quit();
            logger.info("===== Browser closed, Test Suite Finished =====");
        }
    }
}
