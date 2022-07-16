package platforms.ui.Web.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(how = How.ID, using ="email")
    public WebElement UserNameTextField;

    @FindBy(how = How.ID, using ="pass")
    public WebElement PasswordTextField;

    @FindBy(how = How.NAME, using ="login")
    public WebElement LoginButton;

    @FindBy(how = How.ID, using = "accept")//"//span[contains(text(),'Accept')]")
    public WebElement AcceptCookies;

    @FindBy(how = How.XPATH, using ="//a[contains(text(),'Overview')]")
    public WebElement OverviewNav;

    @FindBy(how = How.XPATH, using ="//a[contains(text(),'Log out')]")
    public WebElement Logout;
}
