package kg.cdt.automation.framework.listeners;

import io.qameta.allure.Allure;
import kg.cdt.automation.framework.base.BaseTest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.*;

import java.io.File;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class TestListener implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = BaseTest.getDriver();

        if (driver != null) {
            try {
                // === Формируем имя файла ===
                String testName = context.getDisplayName().replaceAll("[^a-zA-Z0-9-_]", "_");
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                Path screenshotsDir = Path.of("target", "screenshots");
                Files.createDirectories(screenshotsDir);

                // === Делаем скриншот ===
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Path destPath = screenshotsDir.resolve(testName + "_" + timestamp + ".png");
                Files.copy(srcFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);

                // === Добавляем в Allure ===
                byte[] screenshotBytes = Files.readAllBytes(destPath);
                Allure.getLifecycle().addAttachment(
                        "📸 Screenshot on failure",
                        "image/png",
                        "png",
                        screenshotBytes
                );

                // === Причина ошибки ===
                Allure.addAttachment("Failure reason", cause.toString());

                System.out.println("✅ Screenshot saved to: " + destPath);

            } catch (Exception e) {
                System.out.println("⚠️ Could not take screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) { /* ничего не делаем */ }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) { /* ничего не делаем */ }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) { /* ничего не делаем */ }
}
