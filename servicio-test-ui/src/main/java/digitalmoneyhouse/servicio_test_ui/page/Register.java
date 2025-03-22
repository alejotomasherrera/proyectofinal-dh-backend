package digitalmoneyhouse.servicio_test_ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Register {
    private WebDriver driver;
    // Elementos localizadores
    // <button class="MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium css-jcwuwq-MuiButtonBase-root-MuiButton-root" tabindex="0" type="button">Crear cuenta<span class="MuiTouchRipple-root css-8je8zh-MuiTouchRipple-root"></span></button>
    private By crearCuenta = By.xpath("//button[contains(text(), 'Crear cuenta')]");
    // <form class="tw-flex tw-flex-wrap tw-gap-x-16 tw-gap-y-12 tw-mt-10 tw-bg-background tw-justify-between"><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-password">Nombre</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-px39cz-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" id="outlined-adornment-name" name="name" type="text" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value=""><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>nombre</span></legend></fieldset></div></div></div><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-password">Apellido</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-px39cz-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" id="outlined-adornment-last-name" name="lastName" type="text" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value=""><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>lastName</span></legend></fieldset></div></div></div><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-dni">DNI</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-px39cz-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" autocomplete="off" id="outlined-adornment-dni" name="dni" type="number" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value=""><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>dni</span></legend></fieldset></div></div></div><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-password">Correo</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-px39cz-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" id="outlined-adornment-email" name="email" type="text" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value=""><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>email</span></legend></fieldset></div></div></div><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-password">Contraseña</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-adornedEnd css-q2lp9a-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" autocomplete="off" id="outlined-adornment-password" name="password" type="password" class="MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputAdornedEnd css-1h9uykw-MuiInputBase-input-MuiOutlinedInput-input" value=""><div class="MuiInputAdornment-root MuiInputAdornment-positionEnd MuiInputAdornment-outlined MuiInputAdornment-sizeMedium css-1vhgub1-MuiInputAdornment-root"><button class="MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeEnd MuiIconButton-sizeMedium tw-text-neutral-gray-100 css-1k3ibsw-MuiButtonBase-root-MuiIconButton-root" tabindex="0" type="button" aria-label="toggle password visibility"><svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium css-i4bv87-MuiSvgIcon-root" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="VisibilityIcon"><path d="M12 4.5C7 4.5 2.73 7.61 1 12c1.73 4.39 6 7.5 11 7.5s9.27-3.11 11-7.5c-1.73-4.39-6-7.5-11-7.5zM12 17c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5zm0-8c-1.66 0-3 1.34-3 3s1.34 3 3 3 3-1.34 3-3-1.34-3-3-3z"></path></svg><span class="MuiTouchRipple-root css-8je8zh-MuiTouchRipple-root"></span></button></div><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>Password</span></legend></fieldset></div></div></div><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-password-repeated">Confirmar contraseña</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-adornedEnd css-q2lp9a-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" autocomplete="off" id="outlined-adornment-password-repeated" name="passwordRepeated" type="password" class="MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputAdornedEnd css-1h9uykw-MuiInputBase-input-MuiOutlinedInput-input" value=""><div class="MuiInputAdornment-root MuiInputAdornment-positionEnd MuiInputAdornment-outlined MuiInputAdornment-sizeMedium css-1vhgub1-MuiInputAdornment-root"><button class="MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeEnd MuiIconButton-sizeMedium tw-text-neutral-gray-100 css-1k3ibsw-MuiButtonBase-root-MuiIconButton-root" tabindex="0" type="button" aria-label="toggle password visibility"><svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium css-i4bv87-MuiSvgIcon-root" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="VisibilityIcon"><path d="M12 4.5C7 4.5 2.73 7.61 1 12c1.73 4.39 6 7.5 11 7.5s9.27-3.11 11-7.5c-1.73-4.39-6-7.5-11-7.5zM12 17c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5zm0-8c-1.66 0-3 1.34-3 3s1.34 3 3 3 3-1.34 3-3-1.34-3-3-3z"></path></svg><span class="MuiTouchRipple-root css-8je8zh-MuiTouchRipple-root"></span></button></div><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>Password</span></legend></fieldset></div></div></div><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-dni">Télefono</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-px39cz-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" id="outlined-adornment-phone" name="phone" type="number" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value=""><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>phone</span></legend></fieldset></div></div></div><div class="tw-w-full tw-flex tw-justify-center"><button class="MuiButtonBase-root Mui-disabled MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeMedium MuiButton-outlinedSizeMedium tw-h-14 tw-w-80 tw-text-neutral-gray-300 tw-border-neutral-gray-300 tw-cursor-not-allowed css-1vbvugf-MuiButtonBase-root-MuiButton-root" tabindex="-1" type="submit" disabled="">Ingresar</button></div></form>
    private By nombre = By.id("outlined-adornment-name");
    private By apellido = By.id("outlined-adornment-last-name");
    private By dni = By.id("outlined-adornment-dni");
    private By correo = By.id("outlined-adornment-email");
    private By contrasena = By.id("outlined-adornment-password");
    private By confirmarContrasena = By.id("outlined-adornment-password-repeated");
    private By telefono = By.id("outlined-adornment-phone");
    private By ingresar = By.xpath("//button[@type='submit']");

    public Register(WebDriver driver) {
        this.driver = driver;
    }

    // Metodo para ingresar el nombre
    public void ingresarNombre(String nombre) {
        driver.findElement(this.nombre).sendKeys(nombre);
    }

    // Metodo para ingresar el apellido
    public void ingresarApellido(String apellido) {
        driver.findElement(this.apellido).sendKeys(apellido);
    }

    // Metodo para ingresar el DNI
    public void ingresarDNI(String dni) {
        driver.findElement(this.dni).sendKeys(dni);
    }

    // Metodo para ingresar el correo
    public void ingresarCorreo(String correo) {
        driver.findElement(this.correo).sendKeys(correo);
    }

    // Metodo para ingresar la contraseña
    public void ingresarContrasena(String contrasena) {
        driver.findElement(this.contrasena).sendKeys(contrasena);
    }

    // Metodo para confirmar la contraseña
    public void confirmarContrasena(String contrasena) {
        driver.findElement(this.confirmarContrasena).sendKeys(contrasena);
    }

    // Metodo para ingresar el telefono
    public void ingresarTelefono(String telefono) {
        driver.findElement(this.telefono).sendKeys(telefono);
    }

    // Metodo para hacer clic en el boton de ingresar
    public void clicIngresar() {
        driver.findElement(ingresar).click();
    }

    public WebElement getIngresarButton() {
        return driver.findElement(ingresar);
    }

    public String obtenerMensajeError() {
        return driver.findElement(By.id("error-message")).getText();
    }
}
