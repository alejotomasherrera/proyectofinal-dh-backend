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

import java.time.Duration;

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

        // Iniciar sesión antes de cada prueba
        login = new Login(driver);
        driver.get("http://localhost:3000/login");
        login.ingresarCorreo("alejotomasherrera@hotmail.com");
        login.ingresarContrasena("Alejo123#");
        login.clicIngresar();
    }

    @Test
    public void testLoadMoney() {
        test = extent.createTest("Cargar dinero", "Prueba para verificar la funcionalidad de carga de dinero");
        test.log(Status.INFO, "Comienza el Test");

        driver.get("http://localhost:3000/load-money");
        test.log(Status.INFO, "Navegando a la página de carga de dinero");

        loadMoney = new LoadMoney(driver);
        // Aquí puedes agregar pasos adicionales para interactuar con la página de carga de dinero
        test.log(Status.PASS, "Se accedió correctamente a la página de carga de dinero");
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
