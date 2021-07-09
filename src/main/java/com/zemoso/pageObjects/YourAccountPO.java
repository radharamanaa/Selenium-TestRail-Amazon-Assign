package com.zemoso.pageObjects;

import com.zemoso.pageObjects.base.BasePagePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourAccountPO extends BasePagePO {
    By orders = By.cssSelector("div[data-card-identifier='YourOrders']");
    public YourAccountPO(WebDriver driver) {
        super(driver);
    }
    public YourOrdersPO navigateToOrders(){
        driver.findElement(orders).click();
        return new YourOrdersPO(driver);
    }

}
