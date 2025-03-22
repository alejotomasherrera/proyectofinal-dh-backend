package digitalmoneyhouse.servicio_test_ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dashboard {
    private WebDriver driver;

    private By dineroDisponiblelbl = By.xpath("//p[contains(text(), 'Dinero disponible')]");

    private By holaLbl = By.xpath("//button[contains(text(), 'Hola')]");

    public Dashboard(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDineroDisponibleDisplayed() {
        return driver.findElement(dineroDisponiblelbl).isDisplayed();
    }

    public boolean isHolaDisplayed() {
        return driver.findElement(holaLbl).isDisplayed();
    }

    // catch error
    public String obtenerMensajeError() {
        return driver.findElement(By.id("error-message")).getText();
    }
    // <p class="tw-text-xl tw-font-bold">$&nbsp;1.000</p>
    // Funcion para detectar si el dinero disponible es correcto recibido por parametro
    public boolean verificarDineroDisponible(String dinero) {
        return driver.findElement(dineroDisponiblelbl).getText().equals(dinero);
    }
}
