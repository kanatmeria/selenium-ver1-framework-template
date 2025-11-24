package kg.cdt.automation.framework.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import kg.cdt.automation.framework.base.BaseTest;
import kg.cdt.automation.framework.model.LoginData;
import kg.cdt.automation.framework.pages.LoginPage;
import kg.cdt.automation.framework.utils.TestDataReader;
import org.junit.jupiter.api.Test;

@Epic("Authentication Tests")
@Feature("Login")
public class LoginPageTest extends BaseTest {

    @Test
    @Description("Login with admin credentials from JSON")
    public void adminLoginTest() {
        LoginData adminData = TestDataReader.getLoginData("admin");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(adminData);

        // Проверка успешного логина (например, появление элемента после входа)
        // Здесь пример — проверяем наличие logout кнопки или другого элемента
        // Assertions.assertTrue(homePage.isLogoutButtonVisible(), "Logout button should be visible");
    }

    @Test
    @Description("Login with user credentials from JSON")
    public void userLoginTest() {
        LoginData userData = TestDataReader.getLoginData("user");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(userData);

        // Проверка успешного входа
    }
}
