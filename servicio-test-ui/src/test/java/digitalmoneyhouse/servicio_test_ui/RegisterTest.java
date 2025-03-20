package digitalmoneyhouse.servicio_test_ui;

import digitalmoneyhouse.servicio_test_ui.page.Register;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest {
    private WebDriver driver;
    private Register register;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Alejo\\Documents\\proyectofinal-dh-backend\\servicio-test-ui\\src\\main\\resources\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testRegister() {
        driver.get("http://localhost:3000/register");
        register = new Register(driver);
        register.ingresarNombre("Alejo");
        register.ingresarApellido("Herrera");
        register.ingresarCorreo("alejotomasherrera@hotmail.com");
        register.ingresarContrasena("Alejo123#");
        register.confirmarContrasena("Alejo123#");
        register.ingresarTelefono("1234567890");
        register.clicIngresar();

        // Verificar por url que esta en la pantalla de login
        String loginUrl = "http://localhost:3000/login";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(loginUrl, actualUrl, "La URL después del registro no es la esperada.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
