package digitalmoneyhouse.servicio_test_ui;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import digitalmoneyhouse.servicio_test_ui.extentReports.ExtentFactory;
import digitalmoneyhouse.servicio_test_ui.page.Login;
import digitalmoneyhouse.servicio_test_ui.page.Transf;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



import java.time.Duration;

public class TransferencyTest {
    private WebDriver driver;
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
        // Usar una ruta relativa para el chromedriver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testTransferency() {
        test = extent.createTest("Transferencia de dinero", "Prueba para verificar la transferencia de dinero");
        test.log(Status.INFO, "Comienza el Test");

        driver.get("http://localhost:3000/transferency");
        test.log(Status.INFO, "Navegando a la página de transferencia");

        login = new Login(driver);
        login.ingresarCorreo("alejotomasherrera@hotmail.com");
        test.log(Status.PASS, "Correo ingresado: alejotomasherrera@hotmail.com");

        login.ingresarContrasena("Alejo123#");
        test.log(Status.PASS, "Contraseña ingresada");

        login.clicIngresar();
        test.log(Status.PASS, "Ingresando al sistema");

    } 
}
