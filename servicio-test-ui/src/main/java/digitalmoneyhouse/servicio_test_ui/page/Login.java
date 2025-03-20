package digitalmoneyhouse.servicio_test_ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    private WebDriver driver;
    private WebDriverWait wait;

    private By email = By.id("outlined-adornment-email");

    private By password = By.id("outlined-adornment-password");

    private By ingresar = By.xpath("//button[@type='submit']");

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void ingresarCorreo(String correo) {
        driver.findElement(email).sendKeys(correo);
    }

    public void ingresarContrasena(String contrasena) {
        driver.findElement(password).sendKeys(contrasena);
    }

    public void clicIngresar() {
        driver.findElement(ingresar).click();
    }

    public String obtenerMensajeError() {
        return driver.findElement(By.id("error-message")).getText();
    }
}
