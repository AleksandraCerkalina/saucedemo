package come.saucedemo.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;

import static come.saucedemo.utils.Helper.convertStringWithDollarToDouble;

public class CheckoutPage {

    private CartPage cartPage;

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        cartPage = new CartPage(driver);
    }

    private final By itemPriceElement = By.xpath(".//div[@class='inventory_item_price']");

    // Create 3 fields and 1 button using @Find;

    @FindBy(how = How.ID, using = "first-name")
    private WebElement firstNameInputField;

    @FindBy(how = How.ID, using = "last-name")
    private WebElement lastNameInputField;

    @FindBy(how = How.ID, using = "postal-code")
    private WebElement postalCodeInputField;

    @FindBy(how = How.ID, using = "continue")
    private WebElement submitButton;

    @FindBy(how = How.CLASS_NAME, using = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(how = How.CLASS_NAME, using = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(how = How.CLASS_NAME, using = "summary_total_label")
    private WebElement summaryTotalLabel;

    @FindBy(how = How.ID, using = "finish")
    private WebElement finishButton;

    //checking message "Thank you for your order!" after finish purchase
    @FindBy(how = How.CLASS_NAME, using = "complete-header")
    private WebElement completeHeader;


    public WebElement getFirstNameInputField() {
        return firstNameInputField;
    }

    public WebElement getLastNameInputField() {
        return lastNameInputField;
    }

    public WebElement getPostalCodeInputField() {
        return postalCodeInputField;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public WebElement getTaxLabel() {
        return taxLabel;
    }

    public WebElement getSummaryTotalLabel() {
        return summaryTotalLabel;
    }

    public WebElement getFinishButton() {
        return finishButton;
    }

    public WebElement getCompleteHeader() {
        return completeHeader;
    }

    public void fillStepOne(String firstName, String lastName, String postalCode) {
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.sendKeys(lastName);
        postalCodeInputField.sendKeys(postalCode);
        submitButton.click();

    }


    // metod, kotorij vernet cenu produkta
    public String getPriceByItemName(String itemName) {
        Optional<WebElement> cartItem = cartItems.stream()
                .filter(item -> item.getText().contains(itemName)).
                findFirst();

        return cartItem.get().findElement(By.xpath(".//div[@class='inventory_item_price']")).getText();

    }


    public double getTax() {
        // getTaxLabel().getTex() - Tax: $3.20;
        // $3.20; -> 3.20
        return convertStringWithDollarToDouble(getTaxLabel().getText().replace("Tax: ", ""));

    }

    public double getSummaryTotal() {
        return convertStringWithDollarToDouble(getSummaryTotalLabel().getText().replace("Total:", ""));
    }
















}
