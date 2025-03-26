package digitalmoneyhouse.servicio_test_ui;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import digitalmoneyhouse.servicio_test_ui.extentReports.ExtentFactory;
import digitalmoneyhouse.servicio_test_ui.page.Dashboard;
import digitalmoneyhouse.servicio_test_ui.page.Login;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DashboardTest {
    private WebDriver driver;
    private Login login;
    private Dashboard dashboard;
    static ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/dashboard-report-extent.html");
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
    public void testDashboard() {
        test = extent.createTest("Verificar Dashboard", "Prueba para verificar la funcionalidad del Dashboard");
        test.log(Status.INFO, "Comienza el Test");

        driver.get("http://localhost:3000/");
        test.log(Status.INFO, "Navegando a la página principal (Dashboard)");

        dashboard = new Dashboard(driver);

        // Verificar elementos del Dashboard
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Dinero disponible')]")));
        boolean dineroDisponibleVisible = dashboard.isDineroDisponibleDisplayed();
        test.log(dineroDisponibleVisible ? Status.PASS : Status.FAIL, "El elemento 'Dinero Disponible' está visible.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(., 'Hola, Alejo Herrera')]")));
        boolean holaVisible = dashboard.isHolaDisplayed();
        test.log(holaVisible ? Status.PASS : Status.FAIL, "El elemento 'Hola' está visible.");

        // Verificar URL
        String mainMenuUrl = "http://localhost:3000/";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(mainMenuUrl, actualUrl, "La URL después del inicio de sesión no es la esperada.");
        test.log(Status.PASS, "La URL después del inicio de sesión es la esperada: " + actualUrl);
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
