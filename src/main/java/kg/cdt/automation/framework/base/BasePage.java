package kg.cdt.automation.framework.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger logger = LogManager.getLogger(getClass());

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ===============================
    // 🔹 WAIT HELPERS
    // ===============================

    public WebElement waitForVisible(By locator) {
        logger.debug("Waiting for visibility of WebElement: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForVisible(WebElement element) {
        logger.debug("Waiting for visibility of WebElement: {}", element);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickable(By locator) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForClickable(WebElement element) {
        logger.debug("Waiting for WebElement to be clickable: {}", element);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // ===============================
    // 🔹 DISPLAY CHECKS
    // ===============================

    public boolean isDisplayed(By locator) {
        try {
            boolean displayed = driver.findElement(locator).isDisplayed();
            logger.debug("Element {}  is displayed: {}", locator, displayed);
            return displayed;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            logger.warn("Element {} not found or stale: {}", locator, e.getMessage());
            return false;
        }
    }

    public boolean isDisplayed(WebElement element) {
        try {
            boolean displayed = element.isDisplayed();
            logger.debug("WebElement is displayed: {}", displayed);
            return displayed;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            logger.warn("WebElement not found or stale: {}", e.getMessage());
            return false;
        }
    }

    // ===============================
    // 🔹 CLICK ACTIONS
    // ===============================

    public void click(By locator) {
        logger.info("Clicking on element: {}", locator);
        waitForClickable(locator).click();
    }

    public void click(WebElement element) {
        logger.info("Clicking on Webelement: {}", element);
        waitForClickable(element).click();
    }

    // ===============================
    // 🔹 TYPE (SEND KEYS)
    // ===============================

    public void type(By locator, String text) {
        logger.info("Typing '{}' info element: {}", text, locator);
        WebElement element = waitForClickable(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void type(WebElement element, String text) {
        logger.info("Typing '{}' into WebElement: {}", text, element);
        WebElement el = waitForClickable(element);
        el.clear();
        el.sendKeys(text);
    }

    // ===============================
    // 🔹 TEXT GETTERS
    // ===============================

    public String getText(By locator) {
        String text = waitForVisible(locator).getText();
        logger.debug("Text from {}: '{}'", locator, text);
        return text;
    }

    public String getText(WebElement element) {
        String text = waitForVisible(element).getText();
        logger.debug("Text from WebElement: '{}'", text);
        return text;
    }

    // ===============================
    // 🔹 UTILITY HELPERS
    // ===============================

    public void scrollIntoView(WebElement element) {
        logger.debug("Scrolling into view: {}", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollIntoView(By locator) {
        WebElement element = driver.findElement(locator);
        scrollIntoView(element);
    }

    public void jsClick(WebElement element) {
        logger.info("Clicking via JS: {}", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void jsClick(By locator) {
        WebElement element = driver.findElement(locator);
        jsClick(element);
    }

    public String getAttribute(WebElement element, String attribute) {
        String value = waitForVisible(element).getAttribute(attribute);
        logger.debug("Attribute '{}' value: '{}'", attribute, value);
        return value;    }

    public String getAttribute(By locator, String attribute) {
        return waitForVisible(locator).getAttribute(attribute);
    }


    public boolean waitForAttributeContains(WebElement element, String attribute, String value) {
        try {
            return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
        } catch (TimeoutException e) {
            System.out.printf("⚠️ Timeout waiting for attribute '%s' to contain '%s'%n", attribute, value);
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("⚠️ Element not found while waiting for attribute: " + attribute);
            return false;
        }
    }

    public boolean waitForAttributeToBe(WebElement element, String attribute, String exactValue) {
        try {
            return wait.until(ExpectedConditions.attributeToBe(element, attribute, exactValue));
        } catch (TimeoutException e) {
            System.out.printf("⚠️ Timeout waiting for attribute '%s' to be '%s'%n", attribute, exactValue);
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("⚠️ Element not found while waiting for attribute: " + attribute);
            return false;
        }
    }


    // ===============================
// 🔹 URL HELPERS
// ===============================

    /**
     * Wait until the current URL contains a specific substring.
     */
    public boolean waitForUrlContains(String partialUrl) {
        logger.debug("Waiting for URL to contain: {}", partialUrl);

        try {
            return wait.until(ExpectedConditions.urlContains(partialUrl));
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for URL to contain '{}'", partialUrl);
            return false;
        }
    }

    /**
     * Wait until the current URL exactly matches the given URL.
     */
    public boolean waitForUrlToBe(String expectedUrl) {
        try {
            return wait.until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (TimeoutException e) {
            System.out.println("⚠️ Timeout waiting for URL to be: " + expectedUrl);
            return false;
        }
    }

    /**
     * Get the current page URL.
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.debug("Current URL: {}", url);
        return url;
    }

    public void waitForSeconds(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public static String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = "target/screenshots/" + testName + "_" + timestamp + ".png";

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.createDir(new File("target/screenshots"));
            FileHandler.copy(srcFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
}
