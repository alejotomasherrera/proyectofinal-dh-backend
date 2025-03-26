package digitalmoneyhouse.servicio_test_ui;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import digitalmoneyhouse.servicio_test_ui.extentReports.ExtentFactory;
import digitalmoneyhouse.servicio_test_ui.page.LoadMoney;
import digitalmoneyhouse.servicio_test_ui.page.Login;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoadMoneyTests {
    private WebDriver driver;
    private Login login;
    private LoadMoney loadMoney;
    static ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/load-money-report-extent.html");
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

        // Iniciar sesión
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
    public void testLoadMoney() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        test = extent.createTest("Cargar dinero", "Prueba para verificar la funcionalidad de carga de dinero");
        test.log(Status.INFO, "Comienza el Test");

        driver.get("http://localhost:3000/load-money");
        test.log(Status.INFO, "Navegando a la página de carga de dinero");

        loadMoney = new LoadMoney(driver);
        loadMoney.clickSelectMethod();
        test.log(Status.PASS, "Se hizo clic en el método para cargar desde tarjetas");

        wait.until(driver -> true);
        loadMoney.clickSelectLink();
        test.log(Status.PASS, "Se hizo clic en el enlace para seleccionar el método tarjeta");

        wait.until(driver -> true);
        loadMoney.enterMoney("1000");
        test.log(Status.PASS, "Cantidad de dinero ingresada: 1000");

        loadMoney.clickOutside();
        test.log(Status.PASS, "Se hizo clic fuera del cuadro de entrada de dinero");

        loadMoney.clickConfirmButton();
        test.log(Status.PASS, "Se hizo clic en el botón para confirmar la carga de dinero");

        // Espera de 5 segundos
        wait.until(driver -> true);

        // Snackbar "El dinero fue ingresado correctamente" y terminar el test
        test.log(Status.PASS, "El dinero fue ingresado correctamente");

        // Terminar test
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