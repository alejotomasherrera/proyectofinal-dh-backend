package digitalmoneyhouse.servicio_test_ui;

import digitalmoneyhouse.servicio_test_ui.page.Cards;
import digitalmoneyhouse.servicio_test_ui.page.Login;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {
    private Cards cards;
    private WebDriver driver;
    private Login login;

    @BeforeEach
    public void setUp() {
        // C:\Users\Alejo\Documents\proyectofinal-dh-backend\servicio-test-ui\src\main\resources\chromedriver\chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Alejo\\Documents\\proyectofinal-dh-backend\\servicio-test-ui\\src\\main\\resources\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Loguear previamente
        driver.get("http://localhost:3000/login");
        login = new Login(driver);
        login.ingresarCorreo("alejotomasherrera@hotmail.com");
        login.ingresarContrasena("Alejo123#");
        login.clicIngresar();
    }

    @Test
    public void testAddCard() {
        driver.get("http://localhost:3000/cards");
        cards = new Cards(driver);
        cards.clickCardsLink();
        cards.clickAddCardButton();
        cards.enterCardNumber("1234567890123456");
        cards.enterCardName("ALEJO TOMAS HERRERA");
        cards.enterCardExpiry("1225");
        cards.enterCardCVC("123");
        cards.submitCard();
        int fourDigits = 3456;
        assertTrue(cardAdded(fourDigits), "La tarjeta no se ha agregado a la lista.");
    }

    public boolean cardAdded(int fourDigits) {
        By cardAdded =By.id("card-last-four-" + fourDigits);

        List<WebElement> cards = driver.findElements(cardAdded);
        if(cards.isEmpty()) {
            assertTrue(false, "La tarjeta no se ha agregado correctamente");
        } else {
            assertTrue(true, "La tarjeta se ha agregado correctamente");
        }
        return !cards.isEmpty();

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
