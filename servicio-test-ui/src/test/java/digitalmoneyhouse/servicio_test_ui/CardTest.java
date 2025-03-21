package digitalmoneyhouse.servicio_test_ui;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import digitalmoneyhouse.servicio_test_ui.extentReports.ExtentFactory;
import digitalmoneyhouse.servicio_test_ui.page.Cards;
import digitalmoneyhouse.servicio_test_ui.page.Login;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {
    private Cards cards;
    private WebDriver driver;
    private Login login;
    static ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/card-report-extent.html");
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
    }

    @Test
    public void testAddCard() {
        test = extent.createTest("Agregar Tarjeta", "Prueba para verificar la funcionalidad de agregar una tarjeta");
        test.log(Status.INFO, "Comienza el Test");

        driver.get("http://localhost:3000/cards");
        test.log(Status.INFO, "Navegando a la página de tarjetas");

        cards = new Cards(driver);
        cards.clickCardsLink();
        test.log(Status.PASS, "Se hizo clic en el enlace de tarjetas");

        cards.clickAddCardButton();
        test.log(Status.PASS, "Se hizo clic en el botón para agregar tarjeta");

        cards.enterCardNumber("1234567890123456");
        test.log(Status.PASS, "Número de tarjeta ingresado: 1234567890123456");

        cards.enterCardName("ALEJO TOMAS HERRERA");
        test.log(Status.PASS, "Nombre de la tarjeta ingresado: ALEJO TOMAS HERRERA");

        cards.enterCardExpiry("1225");
        test.log(Status.PASS, "Fecha de expiración ingresada: 1225");

        cards.enterCardCVC("123");
        test.log(Status.PASS, "CVC ingresado: 123");

        cards.submitCard();
        test.log(Status.INFO, "Se hizo clic en el botón para enviar la tarjeta");

        int fourDigits = 3456;
        boolean cardAdded = cardAdded(fourDigits);
        test.log(cardAdded ? Status.PASS : Status.FAIL, "La tarjeta con los últimos cuatro dígitos " + fourDigits + " fue agregada correctamente.");
    }

    public boolean cardAdded(int fourDigits) {
        By cardAdded = By.id("card-last-four-" + fourDigits);
        List<WebElement> cards = driver.findElements(cardAdded);
        return !cards.isEmpty();
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
