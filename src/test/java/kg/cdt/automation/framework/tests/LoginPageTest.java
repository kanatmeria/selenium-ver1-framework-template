package kg.cdt.automation.framework.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import kg.cdt.automation.framework.base.BaseTest;
import kg.cdt.automation.framework.model.LoginData;
import kg.cdt.automation.framework.pages.LoginPage;
import kg.cdt.automation.framework.utils.ConfigReader;
import kg.cdt.automation.framework.utils.TestDataReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Epic("Authentication Tests")
@Feature("Login")
public class LoginPageTest extends BaseTest {
    LoginData adminData = TestDataReader.getLoginData("admin");
    LoginData userData  = TestDataReader.getLoginData("user");

    private void performLoginAndCheck(LoginData data) {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(data);
    }

    @Test
    @Description("Login with admin credentials from JSON")
    public void adminLoginTest() {
//        LoginPage loginPage = new LoginPage(driver, wait);
//        loginPage.login(adminData);
        performLoginAndCheck(adminData);

    }

    @Test
    @Description("Login with user credentials from JSON")
    public void userLoginTest() {

//        LoginPage loginPage = new LoginPage(driver, wait);
//        loginPage.login(userData);
//        // Проверка успешного входа
        performLoginAndCheck(userData);
    }
}
