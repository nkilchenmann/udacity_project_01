package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ErrorPage {
    @FindBy(id = "errorTitle")
    private WebElement errorTitle;
    @FindBy(id = "errorRedirectMessage")
    private WebElement errorRedirectMessage;

    public ErrorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getErrorTitle(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.visibilityOf(errorTitle));
        return errorTitle.getText();
    }

    public String getErrorRedirectMessage(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.visibilityOf(errorRedirectMessage));
        return errorRedirectMessage.getText();
    }

}
