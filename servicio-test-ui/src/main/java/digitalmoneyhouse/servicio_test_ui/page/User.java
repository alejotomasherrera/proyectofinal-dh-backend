package digitalmoneyhouse.servicio_test_ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class User {
    private WebDriver driver;

    private By aliasLocator = By.xpath("//p[text()='estación.alegría.campo']");
    private By cvuLocator = By.xpath("//p[text()='0000000000000002473415']");

    public User(WebDriver driver) {
        this.driver = driver;
    }

    public String getAlias() {
        return driver.findElement(aliasLocator).getText();
    }

    public String getCVU() {
        return driver.findElement(cvuLocator).getText();
    }
}