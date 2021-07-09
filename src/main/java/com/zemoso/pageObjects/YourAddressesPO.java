package com.zemoso.pageObjects;

import com.zemoso.pageObjects.base.BasePagePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourAddressesPO extends BasePagePO {
    By addNewAddress = By.cssSelector("a#ya-myab-address-add-link h2.a-color-tertiary");
    public YourAddressesPO(WebDriver driver) {
        super(driver);
    }
    public AddAddressPage navigateToAddressAddPage(){
        driver.findElement(addNewAddress).click();
        return new AddAddressPage(driver);
    }
}
