package digitalmoneyhouse.servicio_test_ui;

import digitalmoneyhouse.servicio_test_ui.page.Login;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
// Utilizzar junit

public class LoginTest {
    private WebDriver driver;
    private Login login;

    @BeforeEach
    public void setUp() {
        // C:\Users\Alejo\Documents\proyectofinal-dh-backend\servicio-test-ui\src\main\resources\chromedriver\chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Alejo\\Documents\\proyectofinal-dh-backend\\servicio-test-ui\\src\\main\\resources\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testLogin_exitoso() {
        driver.get("http://localhost:3000/login");
        login = new Login(driver);
        login.ingresarCorreo("alejotomasherrera@hotmail.com");
        login.ingresarContrasena("Alejo123#");
        login.clicIngresar();

        // Verificar por url que esta en la pantalla principal
        String mainMenuUrl = "http://localhost:3000/";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(mainMenuUrl, actualUrl, "La URL después del inicio de sesión no es la esperada.");
    }

    @Test
    public void testLogin_fallido() {
        driver.get("http://localhost:3000/login");
        login = new Login(driver);
        login.ingresarCorreo("ale@hotmail.com");
        login.ingresarContrasena("Alejo123#");
        login.clicIngresar();
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
