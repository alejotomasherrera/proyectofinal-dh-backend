package digitalmoneyhouse.servicio_test_ui;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BackendTest {

    private static String baseUrl = "http://localhost:8081";
    private static String token;
    private static ExtentReports extent;
    private static ExtentTest test;
    static ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/card-report-extent.html");
    @BeforeAll
    public static void setUp() {
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        RestAssured.baseURI = baseUrl;
        test = extent.createTest("Login Test", "Obtener token de autenticación");

        Response response = given()
                .contentType("application/json")
                .body("{\"email\":\"alejotomasherrera@hotmail.com\", \"password\":\"Alejo123#\"}")
                .post("/auth-server/api/login");

        test.log(Status.INFO, "Enviando solicitud de login");
        test.log(Status.INFO, "Respuesta: " + response.getBody().asString());

        if (response.getBody().asString().isEmpty()) {
            test.log(Status.FAIL, "Respuesta vacía al intentar autenticar");
            throw new IllegalStateException("Login response body is empty");
        }

        token = response.jsonPath().getString("accessToken");
        test.log(Status.PASS, "Token obtenido exitosamente");
    }

    @Test
    @Order(1)
    public void testCreateUser() {
        test = extent.createTest("Crear Usuario", "Prueba de creación de usuario");
        String userJson = "{\"firstName\":\"John\", \"lastName\":\"Doe\", \"email\":\"alejotomasherrera@hotmail.com\", \"password\":\"Alejo123#\"}";

        Response response = given()
                .contentType("application/json")
                .body(userJson)
                .post("/users-server/api/register");

        test.log(Status.INFO, "Enviando solicitud para crear usuario");
        test.log(Status.INFO, "Respuesta: " + response.getBody().asString());
        response.then().statusCode(201);
        test.log(Status.PASS, "Usuario creado exitosamente");
    }

    @Test
    @Order(2)
    public void testGetUser() {
        test = extent.createTest("Obtener Usuario", "Prueba de obtención de usuario");
        String userId = "1";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get("/users-server/api/user/" + userId);

        test.log(Status.INFO, "Enviando solicitud para obtener usuario");
        test.log(Status.INFO, "Respuesta: " + response.getBody().asString());
        response.then().statusCode(200);
        test.log(Status.PASS, "Usuario obtenido exitosamente");
    }

    @Test
    @Order(3)
    public void testCreateAccount() {
        test = extent.createTest("Crear Cuenta", "Prueba de creación de cuenta bancaria");
        String accountJson = "{\"alias\":\"test.alias\", \"cvu\":\"1234567890123456789012\", \"balance\":0, \"name\":\"Testeando Testeando\"}";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(accountJson)
                .post("/users/1/accounts");

        test.log(Status.INFO, "Enviando solicitud para crear cuenta bancaria");
        test.log(Status.INFO, "Respuesta: " + response.getBody().asString());
        response.then().statusCode(201);
        test.log(Status.PASS, "Cuenta creada exitosamente");
    }

    @Test
    @Order(4)
    public void testGetAccount() {
        test = extent.createTest("Obtener Cuenta", "Prueba de obtención de cuenta bancaria");
        String accountId = "1";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get("/accounts-server/api/accounts/" + accountId);

        test.log(Status.INFO, "Enviando solicitud para obtener cuenta bancaria");
        test.log(Status.INFO, "Respuesta: " + response.getBody().asString());
        response.then().statusCode(200);
        test.log(Status.PASS, "Cuenta obtenida exitosamente");
    }

    @AfterAll
    public static void tearDown() {
        extent.flush();
    }
}
