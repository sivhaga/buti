package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

import org.openqa.selenium.interactions.Actions;


public class CommonFunctions {
    public static Logger logger = Logger.getLogger(CommonFunctions.class);
    public static WebDriver wdriver;
   
    public CommonFunctions wc;


    public CommonFunctions(WebDriver wDriver) {
        this.wdriver = wDriver;
    }

    public CommonFunctions() {

    }
	
    public void scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) this.wdriver;
        jse.executeScript("window.scrollBy(0,300)", new Object[]{"100"});
    }

    public void scrollToEndOfPage() {
        ((JavascriptExecutor) wdriver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }



    public void scrollUp() {
        JavascriptExecutor jse = (JavascriptExecutor) this.wdriver;
        jse.executeScript("window.scrollBy(0,-300)", new Object[]{""});
    }

    public void scrollToElement(WebElement ele) {
        ((JavascriptExecutor) wdriver).executeScript("arguments[0].scrollIntoView(true);", ele);
    }

    public String getDateData(String strDate) {
        int intDaysAmt = Integer.valueOf(strDate);
        SimpleDateFormat sdFormat = new SimpleDateFormat("EEEE, dd MMMM yyy");
        SimpleDateFormat sdDayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat sdMonthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat sdYearFormat = new SimpleDateFormat("yyy");
        Calendar clDate = Calendar.getInstance();
        String strCurrYear = sdYearFormat.format(clDate.getTime());
        String strCurrMonth = sdMonthFormat.format(clDate.getTime());
        String strCurrDay = sdDayFormat.format(clDate.getTime());
        clDate.add(5, intDaysAmt);
        String strDateData = sdFormat.format(clDate.getTime());
        String strDay = sdDayFormat.format(clDate.getTime());
        String strMonth = sdMonthFormat.format(clDate.getTime());
        String strYear = sdYearFormat.format(clDate.getTime());
        int intDaysInMth = clDate.getActualMaximum(5);
        strDateData = strDateData + "," + strDay + "," + strCurrDay + "," + strMonth + "," + strCurrMonth + "," + strYear + "," + strCurrYear + "," + intDaysInMth;
        return strDateData;
    }

    public static void driverquit(WebDriver driver) {
        driver.quit();
    }

    public boolean verifyTextPresent(String textToVerify) {
        this.logger.info("Verifying text present on page - " + textToVerify);

        try {
            return this.wdriver.getPageSource().contains(textToVerify);
        } catch (Exception var3) {
            this.logger.info("Text not present on page - " + textToVerify + "--" + var3.getMessage());
            return false;
        }
    }

    public void secondsDelay(int sec) {
        int timeinMilliSeconds;
        try {
            timeinMilliSeconds = sec * 1000;
            logger.info("##############################################");
            logger.info("Going for " + timeinMilliSeconds + " delay");
            logger.info("##############################################");
            Thread.sleep(timeinMilliSeconds);
        } catch (Exception e) {
            logger.info(e.getStackTrace());
        }
    }

    /* **********************************************************************
       Description of the function : Overloaded explicit wait.
     ***********************************************************************/
    public WebElement waitForElement(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(wdriver, 40);
        wait.until(ExpectedConditions.visibilityOf(ele));
        return ele;
    }

    /* **********************************************************************
       Description of the function : Overloaded explicit wait.
    ***********************************************************************/
    public WebElement waitForElement(WebElement ele, int Time) {
        WebDriverWait wait = new WebDriverWait(wdriver, Time);
        wait.until(ExpectedConditions.visibilityOf(ele));
        return ele;
    }

    /* **********************************************************************
       Description of the function : Javascript click if normal click does not work.
    ***********************************************************************/
    public void javaScriptClick(WebElement ele) {
        JavascriptExecutor executor = (JavascriptExecutor) wdriver;
        executor.executeScript("arguments[0].click();", ele);
    }

    public void actionsClick(WebElement ele) {
        Actions actions = new Actions(wdriver);
        actions.moveToElement(ele).click().build().perform();
    }

    /* **********************************************************************
       Description of the function : Try clicking the element multiple times if there are errors like stale element etc.
    ***********************************************************************/
    public void clickAndTryMultiple(WebElement ele, int attempts) {
        boolean done = false;
        int iLoop = 0;
        while (!done && iLoop <= attempts) {
            try {
                Thread.sleep(1000);
                ele.click();
                done = true;
            } catch (Exception e) {
                iLoop++;
                if (iLoop == attempts) {
                    javaScriptClick(ele);
                }
            }
        }
    }

    /* **********************************************************************
       Description of the function : Try sendkeys multiple times if there are errors like stale element etc.
    ***********************************************************************/
    public void sendKeysAndTryMultiple(WebElement ele, String value, int attempts) {
        boolean done = false;
        int iLoop = 0;
        while (!done && iLoop <= attempts) {
            try {
                Thread.sleep(2000);
                ele.sendKeys(value);
                done = true;
            } catch (Exception e) {
                iLoop++;
            }
        }
    }

}