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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import by
import org.openqa.selenium.By;

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
    @Order(1)
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

        // Webdriver wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String mainMenuUrl = "http://localhost:3000/";
        wait.until(ExpectedConditions.urlToBe(mainMenuUrl));

        String actualUrl = driver.getCurrentUrl();
        assertEquals(mainMenuUrl, actualUrl, "La URL después del inicio de sesión no es la esperada.");
        test.log(Status.PASS, "La URL después del inicio de sesión es la esperada: " + actualUrl);
    }

    @Test
    @Order(2)
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

        // Webdriver wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Esperar hasta que el mensaje de error sea visible
        String mensajeError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tw-bg-primary.tw-p-4.tw-rounded"))).getText();

        assertEquals("Usuario no encontrado", mensajeError, "El mensaje de error no es el esperado.");
        test.log(Status.PASS, "El mensaje de error es el esperado: " + mensajeError);
    }

    //Testear logout exitoso primero iniciando sesion y luego cerrando sesion en el boton <button class="tw-flex tw-items-center tw-gap-x-2 tw-text-neutral-gray-100 hover:tw-text-primary">Cerrar Sesión</button> y verificando a los 5 segundos si la url es http://localhost:3000/login
    @Test
    @Order(3)
    public void testLogout_exitoso() {
        test = extent.createTest("Cerrar sesión exitoso", "Prueba para verificar un cierre de sesión exitoso");
        test.log(Status.INFO, "Comienza el Test");

        driver.get("http://localhost:3000/login");
        test.log(Status.INFO, "Navegando a la página de inicio de sesión");

        login = new Login(driver);
        login.ingresarCorreo("alejotomasherrera@hotmail.com");
        test.log(Status.PASS, "Correo ingresado: alejotomasherrera@hotmail.com");

        login.ingresarContrasena("Alejo123#");
        test.log(Status.PASS, "Contraseña ingresada: Alejo123#");

        login.clicIngresar();
        test.log(Status.INFO, "Se hizo clic en el botón de inicio de sesión");

        // Webdriver wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String mainMenuUrl = "http://localhost:3000/";
        wait.until(ExpectedConditions.urlToBe(mainMenuUrl));

        driver.findElement(By.xpath("//button[text()='Cerrar Sesión']")).click();
        test.log(Status.INFO, "Se hizo clic en el botón de cerrar sesión");

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String loginUrl = "http://localhost:3000/login";

        wait.until(ExpectedConditions.urlToBe(loginUrl));

        String actualUrl = driver.getCurrentUrl();
        assertEquals(loginUrl, actualUrl, "La URL después del cierre de sesión no es la esperada.");
        test.log(Status.PASS, "La URL después del cierre de sesión es la esperada: " + actualUrl);
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
