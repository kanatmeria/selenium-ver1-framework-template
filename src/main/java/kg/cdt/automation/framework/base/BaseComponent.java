package kg.cdt.automation.framework.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseComponent {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebElement root;

    public BaseComponent(WebDriver driver, WebDriverWait wait, WebElement root) {
        this.driver = driver;
        this.wait = wait;
        this.root = root;
    }

    protected WebElement find(By locator) {
        return root.findElement(locator);
    }

    //    protected String getText(By locator) {
//        return find(locator).getText();
//    }
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }


    protected void click(By locator) {
        find(locator).click();
    }
}
