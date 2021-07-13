package com.zemoso.tests;

import com.sun.org.apache.xml.internal.security.Init;
import com.zemoso.pageObjects.mainPages.LoggedInMainPagePO;
import com.zemoso.pageObjects.YourAccountPO;
import com.zemoso.pageObjects.YourOrdersPO;
import config.InitialConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class YourOrdersTest {

    @Test
    public void tc005_showLastYearOrders(){
        WebDriver driver = SeleniumAmazonTest.driver;
        driver.get(InitialConfig.getBaseURL());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LoggedInMainPagePO loggedInMainPage = new LoggedInMainPagePO(driver);
        YourAccountPO accountPO = loggedInMainPage.navigateToYourAccountPage();
        YourOrdersPO ordersPO = accountPO.navigateToOrders();
        ordersPO.selectLastOneYearOrders();
        String currentVisibleValueOfDurationOrders = ordersPO.getCurrentVisibleValueOfDurationOrders();
        System.out.println(currentVisibleValueOfDurationOrders);
        assert currentVisibleValueOfDurationOrders.equals("2021");
    }
}
