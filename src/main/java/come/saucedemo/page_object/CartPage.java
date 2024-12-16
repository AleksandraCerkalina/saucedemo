package come.saucedemo.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

// pravoj knopkoj - generate - konstruktor
public class CartPage {

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CLASS_NAME, using = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(how = How.ID, using = "checkout")
    private WebElement checkoutButton;

    // element kot gener spisok elementov
    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public WebElement getCheckoutButton() {
        return checkoutButton;
    }









}
