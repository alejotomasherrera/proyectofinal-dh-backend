package digitalmoneyhouse.servicio_test_ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Transf {
    private WebDriver wait;
    private WebDriver driver;

    // <a class="tw-flex tw-items-center tw-gap-x-2 tw-text-neutral-gray-100 hover:tw-text-primary !tw-text-primary tw-font-bold" href="/send-money">Enviar Dinero</a>
    private By enviarDinero = By.xpath("//a[contains(text(), 'Enviar Dinero')]");
    // <a class="tw-w-full tw-flex tw-items-center tw-justify-between tw-p-4 hover:tw-bg-neutral-gray-500 tw-transition" href="/send-money?step=1"><div class="tw-flex tw-items-center tw-gap-x-4"><svg width="34" height="34" fill="none" xmlns="http://www.w3.org/2000/svg" role="img"><circle cx="17" cy="17" r="16.35" stroke="currentColor" stroke-width="1.3"></circle><path d="M16.75 10v14.5M24 17H9.5" stroke="currentColor" stroke-width="1.3" stroke-linejoin="round"></path></svg><p>Nueva cuenta</p></div><svg width="22" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" role="img"><path d="M2 10.5a1.5 1.5 0 0 0 0 3v-3Zm19.06 2.56a1.5 1.5 0 0 0 0-2.12l-9.545-9.547a1.5 1.5 0 1 0-2.122 2.122L17.88 12l-8.486 8.485a1.5 1.5 0 1 0 2.122 2.122l9.546-9.546ZM2 13.5h18v-3H2v3Z" fill="currentColor"></path></svg></a>
    private By nuevaCuenta = By.xpath("//p[contains(text(), 'Nueva cuenta')]");
    // <input aria-invalid="false" autocomplete="off" id="outlined-adornment-destination" name="destination" type="text" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value="">
    private By destino = By.id("outlined-adornment-destination");


    // <button class="MuiButtonBase-root Mui-disabled MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeMedium MuiButton-outlinedSizeMedium tw-h-12 tw-w-64 tw-text-neutral-gray-300 tw-border-neutral-gray-300 tw-cursor-not-allowed css-1vbvugf-MuiButtonBase-root-MuiButton-root" tabindex="-1" type="submit" disabled="">Continuar</button>
    private By continuar = By.xpath("//button[contains(text(), 'Continuar')]");

    // <input aria-invalid="false" autocomplete="off" id="outlined-adornment-amount" name="amount" type="number" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value="">
    private By monto = By.id("outlined-adornment-amount");

    // <button class="MuiButtonBase-root Mui-disabled MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeMedium MuiButton-outlinedSizeMedium tw-h-12 tw-w-64 tw-text-neutral-gray-300 tw-border-neutral-gray-300 tw-cursor-not-allowed css-1vbvugf-MuiButtonBase-root-MuiButton-root" tabindex="-1" type="submit" disabled="">Continuar</button>
    private By continuar2 = By.xpath("//button[contains(text(), 'Continuar')]");

    // <button class="MuiButtonBase-root MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeMedium MuiButton-outlinedSizeMedium tw-h-12 tw-w-64 css-1vbvugf-MuiButtonBase-root-MuiButton-root" tabindex="0" type="submit">Transferir<span class="MuiTouchRipple-root css-8je8zh-MuiTouchRipple-root"></span></button>
    private By transferir = By.xpath("//button[contains(text(), 'Transferir')]");

    // Si contiene el boton <button class="MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium tw-h-12 tw-w-64 print:tw-hidden css-jcwuwq-MuiButtonBase-root-MuiButton-root" tabindex="0" type="button">Continuar<span class="MuiTouchRipple-root css-8je8zh-MuiTouchRipple-root"></span></button>
    private By listoContinuar3Final = By.xpath("//button[contains(text(), 'Continuar')]");
    private By outsideClickArea = By.xpath("//body");

    public Transf(WebDriver driver) {
        this.driver = driver;
    }

    public void clickEnviarDinero() {
        driver.findElement(enviarDinero).click();
    }

    public void clickNuevaCuenta() {
        driver.findElement(nuevaCuenta).click();
    }

    public void ingresarDestino(String destino) {
        driver.findElement(this.destino).sendKeys(destino);
    }

    public void clickContinuar() {
        driver.findElement(continuar).click();
    }

    public void ingresarMonto(String monto) {
        driver.findElement(this.monto).sendKeys(monto);
    }

    public void clickContinuar2() {
        driver.findElement(continuar2).click();
    }

    public void clickTransferir() {
        driver.findElement(transferir).click();
    }

    public void clickListoContinuar3Final() {
        driver.findElement(listoContinuar3Final).click();
    }

    public void clickOutside() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement outsideArea = wait.until(ExpectedConditions.elementToBeClickable(outsideClickArea));
        outsideArea.click();
    }


}
