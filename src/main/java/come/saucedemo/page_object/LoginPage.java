package com.saucedemo.page_object;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    //javnije ozhidanija stroka
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        // zhdem 2s, poka zadannoje sostojanije ne ispolnitsja
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    // PageFactory
    @FindBy(id = "user-name")
    public WebElement usernameField;

    @FindBy(name = "password")
    public WebElement passwordField;

    @FindBy(xpath = "//input[@data-test='login-button']")
    public WebElement loginButton;

    //////////////HomeWork_Start//////////////////
    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement errorField;


    public WebElement getErrorField() {
        return errorField;
    }

    //////////////HomeWork_Finish//////////////////


    public void authorize(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        // Explicitly wait element to be clickable - proverka mozhno li kliknutj na knopku
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        // metod zhdet, poka knopka ne propadet
        // wait.until(ExpectedConditions.invisibilityOf(loginButton));
        loginButton.click();



















    }
}