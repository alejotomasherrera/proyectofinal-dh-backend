package digitalmoneyhouse.servicio_test_ui;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import digitalmoneyhouse.servicio_test_ui.extentReports.ExtentFactory;
import digitalmoneyhouse.servicio_test_ui.page.Login;
import digitalmoneyhouse.servicio_test_ui.page.Transf;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class TransferencyTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private Login login;
    private Transf transferency;
    static ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/transferencia-report-extent.html");
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
    public void testTransferency() {
        test = extent.createTest("Transferencia de dinero", "Prueba para verificar la transferencia de dinero");
        test.log(Status.INFO, "Comienza el Test");

        driver.get("http://localhost:3000/send-money");
        test.log(Status.INFO, "Navegando a la página de transferencia");

        // Metodo nueva cuenta aplicando await de 5 segundos
        transferency = new Transf(driver);
        transferency.clickNuevaCuenta();
        test.log(Status.INFO, "Click en nueva cuenta");
        wait.until(driver -> true);
        // Ingresar destino
        transferency.ingresarDestino("aire.naturaleza.playa");
        test.log(Status.INFO, "Ingresar destino");

        // CLick outside
        transferency.clickOutside();
        test.log(Status.INFO, "Click outside");

        // Click continuar
        transferency.clickContinuar();
        test.log(Status.INFO, "Click continuar");
        wait.until(driver -> true);
        // Ingresar monto
        transferency.ingresarMonto("1000");
        test.log(Status.INFO, "Ingresar monto");
        transferency.clickOutside();

        // Click continuar 2
        transferency.clickContinuar2();
        test.log(Status.INFO, "Click continuar 2");
        wait.until(driver -> true);
        // Click transferir
        transferency.clickTransferir();
        test.log(Status.INFO, "Click transferir");
        wait.until(driver -> true);
        // Click listo continuar 3 final
        transferency.clickListoContinuar3Final();
        test.log(Status.INFO, "Click listo continuar 3 final");

        // Espera de 5 segundos y terminar test
        wait.until(driver -> true);
        test.log(Status.PASS, "Transferencia de dinero realizada correctamente");
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
