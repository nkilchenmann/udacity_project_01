package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {
    @Autowired
    private CredentialService credentialService;

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    //Signup, Login, Access
    @Test
    public void testUnauthorizedHomePageRequest() {
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
    }

    @Test
    public void testUnauthorizedResultPageRequest() {
        driver.get("http://localhost:" + this.port + "/result");
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
    }

    @Test
    public void testLoginPageAccessible() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
    }

    @Test
    public void testSignupPageAccessible() {
        driver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("http://localhost:" + this.port + "/signup", driver.getCurrentUrl());
    }

    @Test
    public void testUserSignupAndDuplicateUsername() {
        String firstName = "duplicateFirstName";
        String lastName = "duplicateLastName";
        String username = "duplicatetTestUsername";
        String password = "duplicateTestPassword";
        String expectedSuccessText = "You successfully signed up! Please continue to the login page.";
        String expectedFailureText = "Username already exists.";

        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);

        signupPage.populateSignupForm(firstName, lastName, username, password);
        signupPage.submitSignupForm();
        Assertions.assertEquals(expectedSuccessText, signupPage.obtainSignupResult());

        signupPage.populateSignupForm(firstName, lastName, username, password);
        signupPage.submitSignupForm();
        Assertions.assertEquals(expectedFailureText, signupPage.obtainSignupResult());
    }

    @Test
    public void testInvalidUsernameOrPassword() {
        String username = "invalidUsername";
        String password = "invalidPassword";
        String expectedFailureText = "Invalid username or password.";

        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.populateLoginForm(username, password);
        loginPage.submitLoginForm();
        Assertions.assertEquals(expectedFailureText, loginPage.obtainLoginResult());
    }

    @Test
    public void testUserSignupLoginAndLogout() {
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String username = "testUsername";
        String password = "testPassword";
        String expectedLogoutText = "You have been successfully logged out.";

        //Signup
        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.populateSignupForm(firstName, lastName, username, password);
        signupPage.submitSignupForm();

        //Login
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.populateLoginForm(username, password);
        loginPage.submitLoginForm();

        //HomePage (authorized)
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        //Logout
        HomePage homePage = new HomePage(driver);
        homePage.submitLogout();
        Assertions.assertEquals("http://localhost:" + this.port + "/login?logout=true", driver.getCurrentUrl());
        Assertions.assertEquals(expectedLogoutText, loginPage.obtainLogoutResult());

        //retry HomePage (unauthorized)
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
    }


    //Note creation, editing, deletion
    @Test
    public void testNoteCreationEditingAndDeletion() {
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String username = "testUsername";
        String password = "testPassword";

        String originalTitle = "Fancy note title";
        String originalDescription = "Fancy note description";
        String changedTitle = "Changed Title";
        String changedDescription = "Changed Description";

        //Signup
        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.populateSignupForm(firstName, lastName, username, password);
        signupPage.submitSignupForm();

        //Login
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.populateLoginForm(username, password);
        loginPage.submitLoginForm();

        //create Note
        HomePage homePage = new HomePage(driver);
        homePage.switchToNotesTab();
        homePage.openNewNoteForm(driver);
        homePage.populateNoteForm(originalTitle, originalDescription, driver);
        homePage.submitNoteFormRequest(driver);
        homePage.switchToNotesTab();
        Assertions.assertTrue(homePage.noteListContainsTitleAndDescription(originalTitle, originalDescription, driver));

        //edit Note
        homePage.openEditNoteForm(driver);
        homePage.populateNoteForm(changedTitle, changedDescription, driver);
        homePage.submitNoteFormRequest(driver);
        homePage.switchToNotesTab();
        Assertions.assertTrue(homePage.noteListContainsTitleAndDescription(changedTitle, changedDescription, driver));
        Assertions.assertFalse(homePage.noteListContainsTitleAndDescription(originalTitle, originalDescription, driver));

        //delete Note
        homePage.deleteNote(driver);
        homePage.switchToNotesTab();
        Assertions.assertThrows(NoSuchElementException.class, () -> homePage.noteListContainsTitleAndDescription(changedTitle, changedDescription, driver));
    }

    //Credential creation, viewing, editing and deletion
    @Test
    public void testCredentialCreationEditingAndDeletion() {
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String username = "testUsername";
        String password = "testPassword";

        String originalUrl = "www.fancyurl.com";
        String originalUsername = "fancy_user";
        String originalPassword = "fancy_password";
        String changedUrl = "www.changed.com";
        String changedUsername = "changed_user";
        String changedPassword = "changed_password";
        Credential originalCredential;
        Credential changedCredential;

        //Signup
        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.populateSignupForm(firstName, lastName, username, password);
        signupPage.submitSignupForm();

        //Login
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.populateLoginForm(username, password);
        loginPage.submitLoginForm();

        //create Note
        HomePage homePage = new HomePage(driver);
        homePage.switchToCredentialsTab();
        homePage.openNewCredentialForm(driver);
        homePage.populateCredentialsForm(originalUrl, originalUsername, originalPassword, driver);
        homePage.submitCredentialsFormRequest(driver);
        homePage.switchToCredentialsTab();
        originalCredential = credentialService.getCredentialById(
                Integer.valueOf(driver.findElement(By.cssSelector("#buttonEditCredentials")).getAttribute("data-credentialid")),
                Integer.valueOf(driver.findElement(By.cssSelector("#buttonEditCredentials")).getAttribute("data-credentialuserid")));
        Assertions.assertTrue(homePage.credentialsListContainsUrlAndUsernameAndEncryptedPassword(originalUrl, originalUsername, originalPassword, originalCredential.getKey(), driver));

        //edit Note
        homePage.openEditCredentialForm(driver);
        homePage.populateCredentialsForm(changedUrl, changedUsername, changedPassword, driver);
        homePage.submitCredentialsFormRequest(driver);
        homePage.switchToCredentialsTab();
        changedCredential = credentialService.getCredentialById(
                Integer.valueOf(driver.findElement(By.cssSelector("#buttonEditCredentials")).getAttribute("data-credentialid")),
                Integer.valueOf(driver.findElement(By.cssSelector("#buttonEditCredentials")).getAttribute("data-credentialuserid")));
        Assertions.assertTrue(homePage.credentialsListContainsUrlAndUsernameAndEncryptedPassword(changedUrl, changedUsername, changedPassword, changedCredential.getKey(), driver));
        Assertions.assertFalse(homePage.credentialsListContainsUrlAndUsernameAndEncryptedPassword(originalUrl, originalUsername, originalPassword, originalCredential.getKey(), driver));

        //delete Note
        homePage.deleteCredentials(driver);
        homePage.switchToCredentialsTab();
        Assertions.assertThrows(NoSuchElementException.class, () -> homePage.credentialsListContainsUrlAndUsernameAndEncryptedPassword(changedUrl, changedUsername, changedPassword, changedCredential.getKey(), driver));

    }

}
