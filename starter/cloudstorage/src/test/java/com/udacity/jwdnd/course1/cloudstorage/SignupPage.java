package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "messageSignupSuccess")
    private WebElement messageSignupSuccess;

    @FindBy(id = "messageSignupFailure")
    private WebElement messageSignupFailure;

    @FindBy(id = "buttonSubmitSignup")
    private WebElement buttonSubmitSignup;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void populateSignupForm(String firstName, String lastName, String username, String password) {
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
    }

    public void submitSignupForm() {
        buttonSubmitSignup.click();
    }

    public String obtainSignupResult() {
        try {
            return messageSignupSuccess.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Success text field not set.");
        }

        try {
            return messageSignupFailure.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Failure text field not set.");
        }

        return null;
    }

}
