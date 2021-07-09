package com.zemoso.pageObjects;

import com.zemoso.pageObjects.base.BasePagePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedInMainPageCO extends BasePagePO {
    By mainNav = By.id("nav-link-accountList");

    public LoggedInMainPageCO(WebDriver driver) {
        super(driver);
    }
    public YourAccountPO navigateToYourAccountPage(){
        driver.findElement(mainNav).click();
        return new YourAccountPO(driver);
    }
}
