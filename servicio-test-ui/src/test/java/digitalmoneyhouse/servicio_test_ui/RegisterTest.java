package digitalmoneyhouse.servicio_test_ui;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import digitalmoneyhouse.servicio_test_ui.extentReports.ExtentFactory;
import digitalmoneyhouse.servicio_test_ui.page.Register;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest {
    private WebDriver driver;
    private Register register;
    static ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/registro-report-extent.html");
    static ExtentReports extent;
    private ExtentTest test;

    @BeforeAll
    public static void crearReporte() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(sparkReporter);
    }

    @BeforeEach
    public void setUp() {
        // Usar una ruta relativa para el chromedriver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testRegister() {
        test = extent.createTest("Registro de usuario", "Prueba para verificar el registro de un usuario");
        test.log(Status.INFO, "Comienza el Test");

        driver.get("http://localhost:3000/register");
        test.log(Status.INFO, "Navegando a la página de registro");

        register = new Register(driver);
        register.ingresarNombre("Alejo");
        test.log(Status.PASS, "Nombre ingresado: Alejo");

        register.ingresarApellido("Herrera");
        test.log(Status.PASS, "Apellido ingresado: Herrera");

        register.ingresarCorreo("alejotomasherrera@hotmail.com");
        test.log(Status.PASS, "Correo ingresado: alejotomasherrera@hotmail.com");

        register.ingresarContrasena("Alejo123#");
        test.log(Status.PASS, "Contraseña ingresada");

        register.confirmarContrasena("Alejo123#");
        test.log(Status.PASS, "Confirmación de contraseña ingresada");

        register.ingresarTelefono("1234567890");
        test.log(Status.PASS, "Teléfono ingresado: 1234567890");

        register.clicIngresar();
        test.log(Status.INFO, "Se hizo clic en el botón de registro");

        String loginUrl = "http://localhost:3000/login";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(loginUrl, actualUrl, "La URL después del registro no es la esperada.");
        test.log(Status.PASS, "La URL después del registro es la esperada: " + actualUrl);
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        if (driver != null) {
            if (testInfo.getTags().contains("failed")) {
                test.log(Status.FAIL, "El test falló.");
            }
            driver.quit();
        }
    }

    @AfterAll
    public static void reporte() {
        extent.flush();
    }
}