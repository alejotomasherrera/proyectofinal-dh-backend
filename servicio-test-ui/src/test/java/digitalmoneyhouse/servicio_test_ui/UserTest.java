package digitalmoneyhouse.servicio_test_ui;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import digitalmoneyhouse.servicio_test_ui.extentReports.ExtentFactory;
import digitalmoneyhouse.servicio_test_ui.page.Login;
import digitalmoneyhouse.servicio_test_ui.page.User;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private WebDriver driver;
    private Login login;
    private User user;
    static ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/user-report-extent.html");
    static ExtentReports extent;
    private ExtentTest test;

    @BeforeAll
    public static void crearReporte() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(sparkReporter);
    }

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Iniciar sesión antes de cada prueba
        driver.get("http://localhost:3000/login");
        login = new Login(driver);
        login.ingresarCorreo("alejotomasherrera@hotmail.com");
        login.ingresarContrasena("Alejo123#");
        login.clicIngresar();

        // Esperar hasta que la URL cambie a la página principal
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
    }

    @Test
    public void testUserProfile() {
        test = extent.createTest("Verificar Perfil de Usuario", "Prueba para verificar el alias y CVU del usuario");
        test.log(Status.INFO, "Comienza el Test");

        driver.get("http://localhost:3000/profile");
        test.log(Status.INFO, "Navegando a la página de perfil");

        user = new User(driver);

        // Verificar alias
        String expectedAlias = "estación.alegría.campo";
        String actualAlias = user.getAlias();
        assertEquals(expectedAlias, actualAlias, "El alias no es el esperado.");
        test.log(Status.PASS, "El alias es el esperado: " + actualAlias);

        // Verificar CVU
        String expectedCVU = "0000000000000002473415";
        String actualCVU = user.getCVU();
        assertEquals(expectedCVU, actualCVU, "El CVU no es el esperado.");
        test.log(Status.PASS, "El CVU es el esperado: " + actualCVU);

        test.log(Status.INFO, "Fin del Test");
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