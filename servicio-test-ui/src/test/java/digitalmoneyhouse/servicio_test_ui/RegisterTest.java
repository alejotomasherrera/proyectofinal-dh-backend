package digitalmoneyhouse.servicio_test_ui;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import digitalmoneyhouse.servicio_test_ui.extentReports.ExtentFactory;
import digitalmoneyhouse.servicio_test_ui.page.Register;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest {
    private WebDriver driver;
    private WebDriverWait wait;
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
    @Order(1)
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

        register.ingresarDNI("43760181");

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

        // Webdriver wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String loginUrl = "http://localhost:3000/login";

        // Esperar unos segundos antes de verificar la URL
        wait.until(ExpectedConditions.urlToBe(loginUrl));
        String actualUrl = driver.getCurrentUrl();
        assertEquals(loginUrl, actualUrl);

        test.log(Status.PASS, "La URL después del registro es la esperada: " + actualUrl);
    }

    // Registro de usuario invalido con campo telefono vacio
    @Test
    @Order(2)
    public void testRegisterInvalido() {
        test = extent.createTest("Registro de usuario inválido", "Prueba para verificar el registro de un usuario inválido");
        test.log(Status.INFO, "Comienza el Test");

        driver.get("http://localhost:3000/register");
        test.log(Status.INFO, "Navegando a la página de registro");

        register = new Register(driver);
        register.ingresarNombre("Alejo");
        test.log(Status.PASS, "Nombre ingresado: Alejo");

        register.ingresarApellido("Herrera");
        test.log(Status.PASS, "Apellido ingresado: Herrera");

        register.ingresarDNI("43760181");
        test.log(Status.PASS, "DNI ingresado: 43760181");

        register.ingresarCorreo("alejotomasherrera@hotmail.com");
        test.log(Status.PASS, "Correo ingresado: alejotomasherrera@hotmail.com");

        register.ingresarContrasena("Alejo123#");
        test.log(Status.PASS, "Contraseña ingresada");

        register.confirmarContrasena("Alejo123#");
        test.log(Status.PASS, "Confirmación de contraseña ingresada");

        // No ingresar el teléfono para que el formulario esté incompleto

        // Webdriver wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Verificar que el botón no esté habilitado
        boolean isButtonDisabled = wait.until(ExpectedConditions.attributeToBe(By.xpath("//button[@type='submit']"), "disabled", "true"));
        assertEquals(true, isButtonDisabled, "El botón de registro no debería estar habilitado");

        test.log(Status.PASS, "El botón de registro no está habilitado como se esperaba");
    }


    @Test
    @Order(3)
    public void testRegisterUsuarioExistente() {
        test = extent.createTest("Registro de usuario existente", "Prueba para verificar el registro de un usuario existente");
        test.log(Status.INFO, "Comienza el Test");

        driver.get("http://localhost:3000/register");
        test.log(Status.INFO, "Navegando a la página de registro");

        register = new Register(driver);
        register.ingresarNombre("Alejo");
        test.log(Status.PASS, "Nombre ingresado: Alejo");

        register.ingresarApellido("Herrera");
        test.log(Status.PASS, "Apellido ingresado: Herrera");

        register.ingresarDNI("43760181");

        register.ingresarCorreo("alejotomasherrera@hotmail.com");
        test.log(Status.PASS, "Correo ingresado: alejotomasherrera@hotmail.com");

        register.ingresarContrasena("Alejo1234#");
        test.log(Status.PASS, "Contraseña ingresada");

        register.confirmarContrasena("Alejo1234#");
        test.log(Status.PASS, "Confirmación de contraseña ingresada");

        register.ingresarTelefono("1234567890");
        test.log(Status.PASS, "Teléfono ingresado: 1234567890");

        register.clicIngresar();
        test.log(Status.INFO, "Se hizo clic en el botón de registro");

        // Webdriver wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Esperar hasta que aparezca el mensaje de error "El usuario ya existe"
        String errorMessage = "El usuario ya existe";
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".tw-bg-error.tw-p-4.tw-rounded"), errorMessage));

        // Verificar que el mensaje de error es el esperado
        String actualErrorMessage = driver.findElement(By.cssSelector(".tw-bg-error.tw-p-4.tw-rounded")).getText();
        assertEquals(errorMessage, actualErrorMessage, "El mensaje de error no es el esperado");

        test.log(Status.PASS, "El mensaje de error es el esperado: " + actualErrorMessage);
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