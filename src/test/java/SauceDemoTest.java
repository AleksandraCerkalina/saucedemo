import com.github.javafaker.Faker;
import com.saucedemo.page_object.InventoryPage;
import com.saucedemo.page_object.LoginPage;
import come.saucedemo.page_object.CartPage;
import come.saucedemo.page_object.CheckoutPage;
import come.saucedemo.page_object.HeaderPage;
import come.saucedemo.utils.Helper;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

import static come.saucedemo.utils.Helper.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SauceDemoTest {

    ChromeDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    HeaderPage headerPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    Configurations configs;
    Configuration config;

    // dlja randomnih imen dlja testa; randomData - objekt
    Faker randomData = new Faker();

    @BeforeMethod
    public void setUp() throws ConfigurationException {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        headerPage = new HeaderPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        configs = new Configurations();
        config = configs.properties("config.properties");

        driver.get(config.getString("web.url"));
    }

    @Test
    public void sauceDemoLoginTest() {
        loginPage.authorize(config.getString("username"), config.getString("password"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    //////////////HomeWork_Start//////////////////
    @Test
    public void sauceDemoLoginOkPassEmptyTest() {
        loginPage.authorize(config.getString("username"), config.getString("password"));
        Assertions.assertThat(loginPage.getErrorField().getText())
                .isEqualTo("Epic sadface: Password is required");
    }

    @Test
    public void sauceDemoLoginNokPassNokTest() {
        loginPage.authorize(config.getString("username"), config.getString("password"));
        Assertions.assertThat(loginPage.getErrorField().getText())
                .isEqualTo("Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void sauceDemoLoginEmptyPassOkTest() {
        loginPage.authorize(config.getString("username"), config.getString("password"));
        Assertions.assertThat(loginPage.getErrorField().getText())
                .isEqualTo("Epic sadface: Username is required");
    }

    @Test
    public void sauceDemoLoginOkPassNokTest() {
        loginPage.authorize(config.getString("username"), config.getString("password"));
        Assertions.assertThat(loginPage.getErrorField().getText())
                .isEqualTo("Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void sauceDemoLoginEmptyPassEmtyTest() {
        loginPage.authorize(config.getString("username"), config.getString("password"));
        Assertions.assertThat(loginPage.getErrorField().getText())
                .isEqualTo("Epic sadface: Username is required");
    }

    //////////////HomeWork_End//////////////////

    @Test
    public void sauceDemoAddItemToTheCartTest() {
        loginPage.authorize(config.getString("username"), config.getString("password"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        inventoryPage.selectItemByName("Backpack");
        inventoryPage.selectItemByName("Bike Light");
        // headerPage.getShoppingCartBadge().getText() - mi ozhidaem tekst so strani4ki
        //Assert.assertEquals("1", headerPage.getShoppingCartBadge().getText());
        Assertions.assertThat(headerPage.getShoppingCartBadge().getText()).isEqualTo("2");

        // otkrivaem samu korzinu
        headerPage.getShoppingCartLink().click();
        // proverka v korzine 2 produkta ili net
        assertThat(cartPage.getCartItems().size()).isEqualTo(2);
        // proverka, 4to 1ij element v korzine Backpack
        assertThat(cartPage.getCartItems().get(0).getText()).contains("Backpack");
        // proverka, 4to 2oj element v korzine Bike Light
        assertThat(cartPage.getCartItems().get(1).getText()).contains("Bike Light");

        // Functional
        assertThat(cartPage.getCartItems())
                .extracting(WebElement::getText)
                .anyMatch(text -> text.contains("Bike Light"));

        assertThat(cartPage.getCartItems())
                .extracting(WebElement::getText)
                .anyMatch(text -> text.contains("Backpack"));

        cartPage.getCheckoutButton().click();

        // 4tobi test proklikal i ostanovilsja, nuzhna eta stroka
        //System.out.println("123");

        checkoutPage.fillStepOne(
                randomData.funnyName().name(),
                randomData.howIMetYourMother().character(),
                randomData.address().zipCode());


        // Price summary
        double backpackPrice = convertStringWithDollarToDouble(checkoutPage.getPriceByItemName("Backpack"));
        double bikeLightPrice = convertStringWithDollarToDouble(checkoutPage.getPriceByItemName("Bike Light"));
        double sumPrice = backpackPrice + bikeLightPrice;
        System.out.println(sumPrice);

        double totalPrice = sumPrice + checkoutPage.getTax();
        assertThat(checkoutPage.getSummaryTotal()).isEqualTo(totalPrice);
        //mozhno kursor na assrtThat -> pravoj knopkoj -> show context actions -> use static imort i budet:
        //assertThat(totalPrice).isEqualTo(43.18);

        //FinishButton
        checkoutPage.getFinishButton().click();
        Assertions.assertThat(checkoutPage.getCompleteHeader().getText()).isEqualTo("Thank you for your order!");
        //mozhno kursor na assrtThat -> pravoj knopkoj -> show context actions -> use static imort i budet:
        //assertThat(checkoutPage.getCompleteHeader().getText()).isEqualTo("Thank you for your order!");




    }





    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}