package kg.cdt.automation.framework.pages;


import io.qameta.allure.Allure;
import kg.cdt.automation.framework.base.BasePage;
import kg.cdt.automation.framework.model.LoginData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(id = "basic_username")
    private WebElement loginField;

    @FindBy(id = "basic_password")
    private WebElement passwordField;

    @FindBy(className = "login_input_button__vUAFE") // кнопка Login
    private WebElement loginButton;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    /**
     * Выполнить вход в систему
     */
    public void login(LoginData data) {
        Allure.step("Ввод логина: " + data.getLogin(), () -> {
            logger.info("Typing login: {}", data.getLogin());
            type(loginField, data.getLogin());
        });

        Allure.step("Ввод пароля: " + data.getPassword(), () -> {
            logger.info("Typing password: {}", "*".repeat(data.getPassword().length()));
            type(passwordField, data.getPassword());
        });

        Allure.step("Нажимаем кнопку Login", () -> {
            logger.info("Clicking Login button");
            click(loginButton);
        });
    }
}