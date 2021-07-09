package com.zemoso.tests;

import com.zemoso.pageObjects.BasePageNonLoggedIn;
import com.zemoso.pageObjects.LoggedInMainPageCO;
import com.zemoso.pageObjects.YourAccountPO;
import com.zemoso.pageObjects.YourOrdersPO;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class YourOrdersTest {

    @Test
    public void tc005_showLastYearOrders(){
        WebDriver driver = SeleniumAmazonTest.driver;
        LoggedInMainPageCO loggedInMainPage = new LoggedInMainPageCO(driver);
        YourAccountPO accountPO = loggedInMainPage.navigateToYourAccountPage();
        YourOrdersPO ordersPO = accountPO.navigateToOrders();
        ordersPO.selectLastOneYearOrders();
        String currentVisibleValueOfDurationOrders = ordersPO.getCurrentVisibleValueOfDurationOrders();
        System.out.println(currentVisibleValueOfDurationOrders);
        assert currentVisibleValueOfDurationOrders.equals("2021");
    }
}
