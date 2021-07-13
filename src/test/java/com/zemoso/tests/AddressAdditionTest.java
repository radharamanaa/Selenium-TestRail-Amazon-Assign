package com.zemoso.tests;

import com.zemoso.pageObjects.AddAddressPO;
import com.zemoso.pageObjects.mainPages.LoggedInMainPagePO;
import com.zemoso.pageObjects.YourAccountPO;
import com.zemoso.pageObjects.YourAddressesPO;
import config.InitialConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddressAdditionTest {
    @Test
    public void tc005_addAndVerifyAddress(){
        WebDriver driver  = SeleniumAmazonTest.driver;
        driver.get(InitialConfig.getBaseURL());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LoggedInMainPagePO mainPageCO = new LoggedInMainPagePO(driver);
        YourAccountPO accountPO = mainPageCO.navigateToYourAccountPage();
        YourAddressesPO addressesPO = accountPO.navigateToAddressesPO();
        AddAddressPO addressPO = addressesPO.navigateToAddressAddPage();
        addressPO.addAddressAndAssertExistence("ZeMoSo Technologies Pvt Ltd","9666738943",
                "500008","802/803 MJR Magnifique","Raidurgam", "Hyderabad");

    }
}
