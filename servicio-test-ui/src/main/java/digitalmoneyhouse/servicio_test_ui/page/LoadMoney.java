package digitalmoneyhouse.servicio_test_ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoadMoney {
    private WebDriver driver;

    private By selectMethod = By.xpath("//div[contains(@class, 'tw-flex tw-items-center tw-gap-x-4') and .//p[text()='Seleccionar tarjeta']]");
    private By selectButton = By.xpath("//button[contains(@class, 'tw-text-primary') and text()='Seleccionar']");
    private By moneyInput = By.id("outlined-adornment-money");
    private By confirmButton = By.xpath("//button[contains(text(), 'Confirmar')]");
    private By availableBalance = By.id("available-balance");
    private By outsideClickArea = By.xpath("//body");
    private By snackbar = By.xpath("//div[contains(@class, 'tw-snackbar') and contains(text(), 'El dinero fue ingresado correctamente')]");

    public LoadMoney(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMethod() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement methodElement = wait.until(ExpectedConditions.elementToBeClickable(selectMethod));
        methodElement.click();
    }

    public void clickSelectLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement selectBtn = wait.until(ExpectedConditions.elementToBeClickable(selectButton));
        selectBtn.click();
    }

    public void enterMoney(String money) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement moneyField = wait.until(ExpectedConditions.visibilityOfElementLocated(moneyInput));
        moneyField.sendKeys(money);
    }

    public void clickConfirmButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmBtn.click();
    }

    public String getAvailableBalance() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement balanceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(availableBalance));
        return balanceElement.getText();
    }

    public void clickOutside() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement outsideArea = wait.until(ExpectedConditions.elementToBeClickable(outsideClickArea));
        outsideArea.click();
    }

    // Verificar en snackbar si dice el dinero fue ingresado correctamente
    public String getSnackbarMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased timeout
        WebElement snackbarElement = wait.until(ExpectedConditions.visibilityOfElementLocated(snackbar));
        return snackbarElement.getText();
    }
}