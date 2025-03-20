package digitalmoneyhouse.servicio_test_ui.page;

// UI test para cards

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cards {
    private WebDriver driver;

    // ABMC CARDS
    // <a class="tw-flex tw-items-center tw-gap-x-2 tw-text-neutral-gray-100 hover:tw-text-primary !tw-text-primary tw-font-bold" href="/cards">Mis Tarjetas</a>
    private By cardsLink = By.xpath("//a[contains(text(), 'Mis Tarjetas')]");
    // Cambiar el contains pq tienda a fallar pq busca el primero que encuentra
    // <a class="tw-w-full tw-flex tw-items-center tw-justify-between tw-p-4 hover:tw-bg-neutral-gray-500 tw-transition" id="add-card-button" href="/cards?add=true"><div class="tw-flex tw-items-center tw-gap-x-4"><svg width="34" height="34" fill="none" xmlns="http://www.w3.org/2000/svg" role="img"><circle cx="17" cy="17" r="16.35" stroke="currentColor" stroke-width="1.3"></circle><path d="M16.75 10v14.5M24 17H9.5" stroke="currentColor" stroke-width="1.3" stroke-linejoin="round"></path></svg><p>Nueva tarjeta</p></div><svg width="22" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" role="img"><path d="M2 10.5a1.5 1.5 0 0 0 0 3v-3Zm19.06 2.56a1.5 1.5 0 0 0 0-2.12l-9.545-9.547a1.5 1.5 0 1 0-2.122 2.122L17.88 12l-8.486 8.485a1.5 1.5 0 1 0 2.122 2.122l9.546-9.546ZM2 13.5h18v-3H2v3Z" fill="currentColor"></path></svg></a>
    private By addCardButton = By.id("add-card-button");

    // Test agregar tarjeta de credito/debito
    // <form class="tw-flex tw-flex-wrap tw-gap-y-12 tw-gap-x-16 tw-justify-center"><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-number">Número</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-px39cz-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" autocomplete="off" id="outlined-adornment-number" name="number" type="number" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value=""><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>number</span></legend></fieldset></div></div></div><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-name">Nombre</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-px39cz-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" autocomplete="off" id="outlined-adornment-name" name="name" type="text" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value=""><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>name</span></legend></fieldset></div></div></div><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-expiry">Válida hasta</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-px39cz-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" autocomplete="off" id="outlined-adornment-expiry" name="expiry" type="number" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value=""><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>expiry</span></legend></fieldset></div></div></div><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-cvc">CVC</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-px39cz-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" autocomplete="off" id="outlined-adornment-cvc" name="cvc" type="number" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value=""><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>cvc</span></legend></fieldset></div></div></div><div class="tw-flex tw-w-full tw-justify-end tw-mt-6"><button class="MuiButtonBase-root Mui-disabled MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeMedium MuiButton-outlinedSizeMedium tw-h-12 tw-w-64 tw-text-neutral-gray-300 tw-border-neutral-gray-300 tw-cursor-not-allowed css-1vbvugf-MuiButtonBase-root-MuiButton-root" tabindex="-1" type="submit" disabled="">Agregar</button></div></form>
    // <form class="tw-flex tw-flex-wrap tw-gap-y-12 tw-gap-x-16 tw-justify-center"><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-number">Número</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-px39cz-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" autocomplete="off" id="outlined-adornment-number" name="number" type="number" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value=""><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>number</span></legend></fieldset></div></div></div><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-name">Nombre</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-px39cz-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" autocomplete="off" id="outlined-adornment-name" name="name" type="text" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value=""><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>name</span></legend></fieldset></div></div></div><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-expiry">Válida hasta</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-px39cz-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" autocomplete="off" id="outlined-adornment-expiry" name="expiry" type="number" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value=""><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>expiry</span></legend></fieldset></div></div></div><div><div class="MuiFormControl-root css-1nrlq1o-MuiFormControl-root"><label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-outlined MuiFormLabel-colorPrimary css-rb5gc9-MuiFormLabel-root-MuiInputLabel-root" data-shrink="false" for="outlined-adornment-cvc">CVC</label><div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-px39cz-MuiInputBase-root-MuiOutlinedInput-root"><input aria-invalid="false" autocomplete="off" id="outlined-adornment-cvc" name="cvc" type="number" class="MuiInputBase-input MuiOutlinedInput-input css-p51h6s-MuiInputBase-input-MuiOutlinedInput-input" value=""><fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-9425fu-MuiOutlinedInput-notchedOutline"><legend class="css-1ftyaf0"><span>cvc</span></legend></fieldset></div></div></div><div class="tw-flex tw-w-full tw-justify-end tw-mt-6"><button class="MuiButtonBase-root Mui-disabled MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeMedium MuiButton-outlinedSizeMedium tw-h-12 tw-w-64 tw-text-neutral-gray-300 tw-border-neutral-gray-300 tw-cursor-not-allowed css-1vbvugf-MuiButtonBase-root-MuiButton-root" tabindex="-1" type="submit" disabled="">Agregar</button></div></form>
    private By numberField = By.id("outlined-adornment-number");
    private By nameField = By.id("outlined-adornment-name");
    private By expiryField = By.id("outlined-adornment-expiry");
    private By cvcField = By.id("outlined-adornment-cvc");

    private By submitButton = By.xpath("//button[contains(text(), 'Agregar')]");

    public Cards(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCardsLink() {
        driver.findElement(cardsLink).click();
    }

    public void clickAddCardButton() {
        driver.findElement(addCardButton).click();
    }

    public void enterCardNumber(String number) {
        driver.findElement(numberField).sendKeys(number);
    }

    public void enterCardName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterCardExpiry(String expiry) {
        driver.findElement(expiryField).sendKeys(expiry);
    }

    public void enterCardCVC(String cvc) {
        driver.findElement(cvcField).sendKeys(cvc);
    }

    public void submitCard() {
        driver.findElement(submitButton).click();
    }

    // Catch error
    public String getErrorMessage() {
        return driver.findElement(By.id("error-message")).getText();
    }

}
