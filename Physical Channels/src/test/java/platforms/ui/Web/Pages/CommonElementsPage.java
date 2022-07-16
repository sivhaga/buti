package platforms.ui.Web.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CommonElementsPage {

    @FindBy(how = How.ID, using = "datePicker")
    public WebElement Payment_Date;

    @FindBy(how = How.CLASS_NAME, using = "dropdown-place")
    public WebElement Payment_Repeat;

    @FindBy(how = How.XPATH, using = "//*[@class=\"dropdown-place\"]/div[2]/div[4]/ul/li[3]")
    public WebElement Payment_Monthly;

    @FindBy(how = How.XPATH, using = "//*[@class=\"dropdown-place\"]/div[2]/div[4]/ul/li[2]")
    public WebElement Payment_Weekly;

    public String FromAccount =  ".//*[@id='from_account']/div";
    public String FromAccountList = ".//*[@id='from_account']/ul";
    public String Transfer_ToAccount =  ".//*[@id='to_account']/div";
    public String Transfer_ToAccountList = ".//*[@id='to_account']/ul";
}
