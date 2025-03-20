package digitalmoneyhouse.servicio_test_ui.page;

// Test page for LoadMoney

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoadMoney {
    private WebDriver driver;
    private By selectLink = By.id("select-card-link");
    private By moneyInput = By.id("outlined-adornment-money");
    private By confirmButton = By.id("confirm-button");
    private By availableBalance = By.id("available-balance");

    public LoadMoney(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectLink() {
        driver.findElement(selectLink).click();
    }

    public void enterMoney(String money) {
        driver.findElement(moneyInput).sendKeys(money);
    }

    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
    }

    public String getAvailableBalance() {
        return driver.findElement(availableBalance).getText();
    }

}
