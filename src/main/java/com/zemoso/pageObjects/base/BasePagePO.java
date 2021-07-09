package com.zemoso.pageObjects.base;

import com.zemoso.pageObjects.CartPO;
import com.zemoso.pageObjects.SearchPagePO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePagePO {
    private By searchBox = By.id("twotabsearchtextbox");
    private By goToCart = By.id("nav-cart-count-container");
    protected WebDriver driver;
    public BasePagePO(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public SearchPagePO searchItem(String item){
        WebElement searchBoxx = driver.findElement(searchBox);
        searchBoxx.sendKeys(item);
        searchBoxx.sendKeys(Keys.RETURN);
        return new SearchPagePO(driver);
    }

    public CartPO navigateToCart(){
        driver.findElement(goToCart).click();
        return new CartPO(driver);
    }
}
