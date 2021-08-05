package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    //Buttons
    @FindBy(id = "buttonLogout")
    private WebElement buttonLogout;
    @FindBy(id = "buttonAddNewNote")
    private WebElement buttonAddNewNote;
    @FindBy(id = "buttonAddNewCredentials")
    private WebElement buttonAddNewCredentials;
    @FindBy(id = "buttonEditNote")
    private WebElement buttonEditNote;
    @FindBy(id = "buttonEditCredentials")
    private WebElement buttonEditCredentials;
    @FindBy(id = "buttonSubmitNoteForm")
    private WebElement buttonSubmitNoteForm;
    @FindBy(id = "buttonSubmitCredentialsForm")
    private WebElement buttonSubmitCredentialsForm;
    @FindBy(id = "buttonDeleteNote")
    private WebElement buttonDeleteNote;
    @FindBy(id = "buttonDeleteCredentials")
    private WebElement buttonDeleteCredentials;

    //Navigation tabs
    @FindBy(id = "nav-notes-tab")
    private WebElement navigationTabNotes;
    @FindBy(id = "nav-credentials-tab")
    private WebElement navigationTabCredentials;

    //Input elements
    @FindBy(id = "note-title")
    private WebElement inputNoteTitle;
    @FindBy(id = "note-description")
    private WebElement inputNoteDescription;
    @FindBy(id = "credential-url")
    private WebElement inputCredentialsUrl;
    @FindBy(id = "credential-username")
    private WebElement inputCredentialsUsername;
    @FindBy(id = "credential-decryptedPassword")
    private WebElement inputCredentialsPassword;

    //Output elements


    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void submitLogout() {
        buttonLogout.click();
    }

    public void switchToNotesTab() {
        navigationTabNotes.click();
    }

    public void switchToCredentialsTab() {
        navigationTabCredentials.click();
    }

    public void openNewNoteForm(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(buttonAddNewNote));
        buttonAddNewNote.click();
    }

    public void openNewCredentialForm(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(buttonAddNewCredentials));
        buttonAddNewCredentials.click();
    }

    public void openEditNoteForm(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(buttonEditNote));
        buttonEditNote.click();
    }

    public void openEditCredentialForm(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(buttonEditCredentials));
        buttonEditCredentials.click();
    }

    public void populateNoteForm(String noteTitle, String noteDescription, WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.and(ExpectedConditions.visibilityOf(inputNoteTitle), ExpectedConditions.visibilityOf(inputNoteDescription)));

        inputNoteTitle.clear();
        inputNoteDescription.clear();

        inputNoteTitle.sendKeys(noteTitle);
        inputNoteDescription.sendKeys(noteDescription);
    }

    public void populateCredentialsForm(String credentialsUrl, String credentialsUsername, String credentialsPassword, WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.and(ExpectedConditions.visibilityOf(inputCredentialsUrl), ExpectedConditions.visibilityOf(inputCredentialsUsername), ExpectedConditions.visibilityOf(inputCredentialsPassword)));

        inputCredentialsUrl.clear();
        inputCredentialsUsername.clear();
        inputCredentialsPassword.clear();

        inputCredentialsUrl.sendKeys(credentialsUrl);
        inputCredentialsUsername.sendKeys(credentialsUsername);
        inputCredentialsPassword.sendKeys(credentialsPassword);
    }


    public void submitNoteFormRequest(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(buttonSubmitNoteForm));
        buttonSubmitNoteForm.click();
    }

    public void submitCredentialsFormRequest(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(buttonSubmitCredentialsForm));
        buttonSubmitCredentialsForm.click();
    }

    public boolean noteListContainsTitleAndDescription(String expectedTitle, String expectedDescription, WebDriver driver) {
        boolean titleContained = false;
        boolean descriptionContained = false;

        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.id("userTable"))));

        String noteTitle = driver.findElement(By.cssSelector("#buttonEditNote")).getAttribute("data-notetitle");
        String noteDescription = driver.findElement(By.cssSelector("#buttonEditNote")).getAttribute("data-notedescription");

        if (noteTitle.equals(expectedTitle))
            titleContained = true;

        if (noteDescription.equals(expectedDescription))
            descriptionContained = true;

        return titleContained && descriptionContained;
    }

    public boolean credentialsListContainsUrlAndUsernameAndEncryptedPassword(String expectedUrl, String expectedUsername, String expectedPassword, String salt, WebDriver driver) {
        boolean urlContained = false;
        boolean usernameContained = false;
        boolean EncryptedPasswordContained = false;
        EncryptionService encryptionService = new EncryptionService();

        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.id("credentialTable"))));

        String credentialsUrl = driver.findElement(By.cssSelector("#credentialTable > tbody > tr > th")).getText();
        String credentialsUsername = driver.findElement(By.cssSelector("#credentialTable > tbody > tr > td:nth-child(3)")).getText();
        String credentialsPassword = driver.findElement(By.cssSelector("#credentialTable > tbody > tr > td:nth-child(4)")).getText();

        if (credentialsUrl.equals(expectedUrl))
            urlContained = true;

        if (credentialsUsername.equals(expectedUsername))
            usernameContained = true;

        if (credentialsPassword.equals(encryptionService.encryptValue(expectedPassword, salt)))
            EncryptedPasswordContained = true;

        return urlContained && usernameContained && EncryptedPasswordContained;
    }

    public void deleteNote(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(buttonDeleteNote));
        buttonDeleteNote.click();
    }

    public void deleteCredentials(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(3).getSeconds()).until(ExpectedConditions.elementToBeClickable(buttonDeleteCredentials));
        buttonDeleteCredentials.click();
    }
}
