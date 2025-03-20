package digitalmoneyhouse.servicio_test_ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class User {
    private WebDriver driver;
    private WebDriverWait wait;

    // click en <div class="tw-flex"><a href="/profile?edit=true"><svg width="32" height="32" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg" role="img" class="tw-text-primary"><g clip-path="url(#clip0_19_502)"><path d="M24.889 26.6666H5.33344V7.11109H17.0846L18.8623 5.33331H5.33344C4.86195 5.33331 4.40976 5.52061 4.07636 5.85401C3.74297 6.18741 3.55566 6.63959 3.55566 7.11109V26.6666C3.55566 27.1381 3.74297 27.5903 4.07636 27.9237C4.40976 28.2571 4.86195 28.4444 5.33344 28.4444H24.889C25.3605 28.4444 25.8127 28.2571 26.1461 27.9237C26.4795 27.5903 26.6668 27.1381 26.6668 26.6666V13.3333L24.889 15.1111V26.6666Z" fill="currentColor"></path><path d="M29.8042 5.19113L26.8086 2.19557C26.6757 2.06227 26.5178 1.9565 26.3439 1.88434C26.17 1.81217 25.9836 1.77502 25.7953 1.77502C25.607 1.77502 25.4206 1.81217 25.2467 1.88434C25.0728 1.9565 24.9149 2.06227 24.782 2.19557L12.5953 14.4534L11.6086 18.7289C11.5666 18.9362 11.571 19.1502 11.6216 19.3555C11.6721 19.5608 11.7676 19.7524 11.901 19.9165C12.0345 20.0805 12.2026 20.213 12.3934 20.3042C12.5841 20.3955 12.7927 20.4434 13.0042 20.4445C13.1135 20.4565 13.2238 20.4565 13.3331 20.4445L17.6442 19.4934L29.8042 7.2178C29.9375 7.08485 30.0433 6.92692 30.1154 6.75303C30.1876 6.57915 30.2247 6.39273 30.2247 6.20446C30.2247 6.0162 30.1876 5.82978 30.1154 5.6559C30.0433 5.48201 29.9375 5.32407 29.8042 5.19113V5.19113ZM16.7197 17.8489L13.4664 18.5689L14.222 15.3422L23.3953 6.10669L25.902 8.61335L16.7197 17.8489ZM26.9064 7.60891L24.3997 5.10224L25.7775 3.6978L28.302 6.22224L26.9064 7.60891Z" fill="currentColor"></path></g><defs><clipPath id="clip0_19_502"><rect width="32" height="32" fill="currentColor"></rect></clipPath></defs></svg></a><div class="tw-relative tw-inline-flex tw-justify-center tw-cursor-pointer tw-ml-4"><button><svg width="32" height="32" fill="none" xmlns="http://www.w3.org/2000/svg" role="img"><path d="M28 10v18H10V10h18Zm0-2H10a2 2 0 0 0-2 2v18a2 2 0 0 0 2 2h18a2 2 0 0 0 2-2V10a2 2 0 0 0-2-2Z" fill="currentColor"></path><path d="M4 18H2V4a2 2 0 0 1 2-2h14v2H4v14Z" fill="currentColor"></path></svg></button></div></div>
    private By editarPerfil = By.xpath("//a[contains(@href, 'profile?edit')]");

    // Ingresar texto en <input aria-invalid="false" autocomplete="off" id="outlined-adornment-alias" name="alias" type="text" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value="risa.aroma.apino">
    private By alias = By.id("outlined-adornment-alias");

    // <button class="MuiButtonBase-root MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeMedium MuiButton-outlinedSizeMedium tw-h-12 tw-w-64  css-1vbvugf-MuiButtonBase-root-MuiButton-root" tabindex="0" type="submit">Confirmar<span class="MuiTouchRipple-root css-8je8zh-MuiTouchRipple-root"></span></button>
    private By confirmar = By.xpath("//button[contains(text(), 'Confirmar')]");

    // Alias nuevo <p class="">risa.aroma.apino</p>
    private By aliasNuevo = By.id("user-alias");

    public User(WebDriver driver) {
        this.driver = driver;
    }

    public void clickEditAlias() {
        driver.findElement(editarPerfil).click();
    }

    public void enterAlias(String alias) {
        driver.findElement(this.alias).clear();
        driver.findElement(this.alias).sendKeys(alias);
        driver.findElement(this.alias).click();
        driver.findElement(confirmar).click();
    }

    public String getAlias() {
        return driver.findElement(aliasNuevo).getText();
    }

    // Get errors
    public String getErrorMessage() {
        return driver.findElement(By.id("error-message")).getText();
    }



}
