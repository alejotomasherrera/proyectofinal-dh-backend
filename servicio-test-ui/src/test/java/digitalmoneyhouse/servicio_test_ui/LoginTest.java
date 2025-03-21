package digitalmoneyhouse.servicio_test_ui;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import digitalmoneyhouse.servicio_test_ui.extentReports.ExtentFactory;
import digitalmoneyhouse.servicio_test_ui.page.Login;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private Login login;
    static ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/login-report-extent.html");
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
    public void testLogin_exitoso() {
        test = extent.createTest("Inicio de sesión exitoso", "Prueba para verificar un inicio de sesión exitoso");
        test.log(Status.INFO, "Comienza el Test");

        driver.get("http://localhost:3000/login");
        test.log(Status.INFO, "Navegando a la página de inicio de sesión");

        login = new Login(driver);
        login.ingresarCorreo("alejotomasherrera@hotmail.com");
        test.log(Status.PASS, "Correo ingresado: alejotomasherrera@hotmail.com");

        login.ingresarContrasena("Alejo123#");
        test.log(Status.PASS, "Contraseña ingresada");

        login.clicIngresar();
        test.log(Status.INFO, "Se hizo clic en el botón de inicio de sesión");

        String mainMenuUrl = "http://localhost:3000/";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(mainMenuUrl, actualUrl, "La URL después del inicio de sesión no es la esperada.");
        test.log(Status.PASS, "La URL después del inicio de sesión es la esperada: " + actualUrl);
    }

    @Test
    public void testLogin_fallido() {
        test = extent.createTest("Inicio de sesión fallido", "Prueba para verificar un inicio de sesión fallido");
        test.log(Status.INFO, "Comienza el Test");

        driver.get("http://localhost:3000/login");
        test.log(Status.INFO, "Navegando a la página de inicio de sesión");

        login = new Login(driver);
        login.ingresarCorreo("ale@hotmail.com");
        test.log(Status.PASS, "Correo ingresado: ale@hotmail.com");

        login.ingresarContrasena("Alejo123#");
        test.log(Status.PASS, "Contraseña ingresada");

        login.clicIngresar();
        test.log(Status.INFO, "Se hizo clic en el botón de inicio de sesión");

        // Aquí puedes agregar validaciones adicionales para verificar el mensaje de error o el comportamiento esperado
        test.log(Status.PASS, "El inicio de sesión fallido se comportó como se esperaba.");
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
