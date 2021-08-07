package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;
    @FindBy(id = "inputPassword")
    private WebElement inputPassword;
    @FindBy(id = "buttonSubmitLogin")
    private WebElement buttonSubmitLogin;
    @FindBy(id = "messageLoginFailure")
    private WebElement messageLoginFailure;
    @FindBy(id = "messageLogoutSuccess")
    private WebElement messageLogoutSuccess;
    @FindBy(id = "messageSignupSuccess")
    private WebElement messageSignupSuccess;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void populateLoginForm(String username, String password) {
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
    }

    public void submitLoginForm() {
        buttonSubmitLogin.click();
    }

    public String obtainLoginResult() {
        try {
            return messageLoginFailure.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Login failure text not set.");
        }
        return null;
    }

    public String obtainLogoutResult() {
        try {
            return messageLogoutSuccess.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Logout success text not set.");
        }
        return null;
    }

    public String obtainSignupResult() {
        try {
            return messageSignupSuccess.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Signup success text not set");
        }
        return null;
    }
}
