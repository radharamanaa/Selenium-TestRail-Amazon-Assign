package com.zemoso.tests;

import com.zemoso.pageObjects.LoggedInMainPagePO;
import com.zemoso.pageObjects.YourAccountPO;
import com.zemoso.pageObjects.YourAddressesPO;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddressAdditionTest {
    @Test
    public void tc005_addAndVerifyAddress(){
        WebDriver driver  = SeleniumAmazonTest.driver;
        driver.get("http://www.amazon.in");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LoggedInMainPagePO mainPageCO = new LoggedInMainPagePO(driver);
        YourAccountPO accountPO = mainPageCO.navigateToYourAccountPage();
        YourAddressesPO addressesPO = accountPO.navigateToAddressesPO();
        addressesPO.navigateToAddressAddPage();

    }
}
