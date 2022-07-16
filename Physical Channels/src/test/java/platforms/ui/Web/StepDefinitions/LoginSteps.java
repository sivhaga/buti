package platforms.ui.Web.StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platforms.ui.Web.Pages.LoginPage;
import platforms.ui.Web.DataFactory;
import utils.CommonFunctions;

import utils.Utils;


import java.util.concurrent.TimeUnit;


public class LoginSteps extends Utils {

    public LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
     private static Logger log = Logger.getLogger(LoginSteps.class);
     WebDriverWait wait = new WebDriverWait(driver,10);
    public static CommonFunctions wc = PageFactory.initElements(driver, CommonFunctions.class);

    @Given("^user launch web application$")
    public void user_launch_web_application(){

        String baseURL = getConfigPropertyValue("./Framework.properties", "WebUrl");
        driver.get(baseURL);
        log.info("user able to launch application");
    }

    @And("^user enters username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void user_enters_username_and_password(String username, String password) throws InterruptedException {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        loginPage.UserNameTextField.clear();
        loginPage.UserNameTextField.sendKeys(DataFactory.username);
        loginPage.PasswordTextField.sendKeys(DataFactory.password);
        log.info("user able to enter username and password");
    }

    @When("^user clicks on Login$")
    public void user_clicks_on_Login() {

        try {
            loginPage.LoginButton.click();
        }catch(Exception e){
            loginPage.UserNameTextField.clear();
            loginPage.UserNameTextField.sendKeys(DataFactory.username);
            loginPage.PasswordTextField.sendKeys(DataFactory.password);
            loginPage.LoginButton.click();
        }
        log.info("user able click on login");
    }

    @Then("^user is able to see home page$")
    public void user_must_see_home_page() throws InterruptedException
    {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String actualTitle = driver.getTitle();

        String expectedTitle = "Facebook";
        Assert.assertEquals(actualTitle, expectedTitle);
        Thread.sleep(1000);
        log.info("User able to logon to web application");

    }


    @And("^user want to logout from Online banking$")
    public void userWantToLogoutFromOnlineBanking(){

        loginPage.Logout.click();
    }

    @And("^user navigate to Login screen$")
    public void userNavigateToLoginScreen(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage.UserNameTextField.isDisplayed();
    }

}
