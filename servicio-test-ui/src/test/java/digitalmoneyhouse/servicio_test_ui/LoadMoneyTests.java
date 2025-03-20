package digitalmoneyhouse.servicio_test_ui;

import digitalmoneyhouse.servicio_test_ui.page.LoadMoney;
import digitalmoneyhouse.servicio_test_ui.page.Login;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoadMoneyTests {
    private WebDriver driver;
    private Login login;
    private LoadMoney loadMoney;

    @BeforeEach
    public void setUp() {
        // C:\Users\Alejo\Documents\proyectofinal-dh-backend\servicio-test-ui\src\main\resources\chromedriver\chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Alejo\\Documents\\proyectofinal-dh-backend\\servicio-test-ui\\src\\main\\resources\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        login = new Login(driver);
        login.ingresarCorreo("alejotomasherrera@hotmail.com");
        login.ingresarContrasena("Alejo123#");
        login.clicIngresar();


    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoadMoney() {
        loadMoney = new LoadMoney(driver);

        driver.get("http://localhost:3000/load-money");


    }
}
