package come.saucedemo.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage {

    //dlja togo 4tobi pri sozdanii objekt HeaderPage v ljubom klasse, mi tuda podavali driver,
    // i vse elementi uze bili proinicializirovai v vide stranici
    public HeaderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    // kak i6em: how = How.CLASS_NAME; ispoljzuem lokator: using = "shopping_cart_link"
    @FindBy(how = How.CLASS_NAME, using = "shopping_cart_link")
    // private potomu, 4to hotim 4tobi rabota s etimi elementami proishodila v ramkah toljko etogo klassa
    // i s pomo6ju publi4nih metodov
    private WebElement shoppingCartLink;

    @FindBy(how = How.CLASS_NAME, using = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    // pravoj knopkoj - generate - getter dlja shoppingCartLink i ShoppingCartBadge
    public WebElement getShoppingCartLink() {
        return shoppingCartLink;
    }

    public WebElement getShoppingCartBadge() {
        return shoppingCartBadge;
    }
}
