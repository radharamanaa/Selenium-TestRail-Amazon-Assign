package com.zemoso.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumAmazonTest {
    public static WebDriver driver;
    @BeforeSuite
    public void beforeSuite(){
        System.setProperty("webdriver.chrome.driver","/home/abhim/Documents/chromedriver_linux64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        amazonLogin();
    }

    @AfterSuite
    public void afterSuite(){
        driver.close();
        driver.quit();
    }
    @Test()
    public void tc001_testAddingItemToCart(){
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Todays deals");
        searchBox.sendKeys(Keys.RETURN);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String searchResultSelector = "div.s-main-slot.s-result-list.s-search-results.sg-row " +
                "div[data-index='5'] img";
        waitTillSelectorVisible(searchResultSelector);
        List<WebElement> products =  driver.findElements(By
                .cssSelector(searchResultSelector));
        assert products.size()==1:"Size was not 1";
        if(products.size() != 1){
            assert false;
            driver.close();
            return;
        }
        products.get(0).click();
        List<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        List<WebElement> stateBuybox = driver.findElements(
                By.cssSelector("div.a-section.a-spacing-small.a-text-center strong"));

        if(stateBuybox.size()!=0){
            Select selectDropDown = new Select(driver.findElement(
                    By.id("native_dropdown_selected_size_name")));
            selectDropDown.selectByIndex(1);
            waitTillSelectorVisible("select#quantity");
        }
        addToCart();
        WebElement cartCount = waitForCartCount("1");
        assert Integer.parseInt(cartCount.getText()) == 1;
    }

    @Test()
    public void tc002_searchForMobilesAndAddLastToCart(){
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Mobiles");
        searchBox.sendKeys(Keys.RETURN);
        List<WebElement> mobiles = driver.findElements(By
                .cssSelector("div[data-component-type='s-search-result'].s-result-item"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if(mobiles.size() > 0){
            Actions actions = new Actions(driver);
            WebElement lastMobile = mobiles.get(mobiles.size() - 1);
            actions.moveToElement(lastMobile);
            actions.perform();
            lastMobile.findElement(By.tagName("img")).click();
            List<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(tabs2.get(tabs2.size()-1));
            addToCart();
            WebElement cartCount = waitForCartCount("2");
            assert Integer.parseInt(cartCount.getText()) == 2;
        }
    }

    @Test(priority = 3,enabled = false)
    public void navigateToCartAndCheckDelivery() throws InterruptedException {
        driver.get("http://www.amazon.in");
        driver.findElement(By.id("nav-cart-count-container")).click();
        WebElement inputField = driver.findElement(By.cssSelector("input[data-feature-id='proceed-to-checkout-action']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(inputField);
        actions.perform();
        inputField.click();
        driver.findElement(By.id("address-ui-widgets-enterAddressFullName"))
                .sendKeys("ZeMoSo Technologies Pvt Ltd");
        driver.findElement(By.id("address-ui-widgets-enterAddressPhoneNumber"))
                .sendKeys("9666738943");
        driver.findElement(By.id("address-ui-widgets-enterAddressPostalCode"))
                .sendKeys("500008");
        WebElement addressLine = driver.findElement(By.id("address-ui-widgets-enterAddressLine1"));
        addressLine
                .sendKeys("802/803 MJR Magnifique");
        addressLine.sendKeys(Keys.ARROW_DOWN);
        addressLine.sendKeys(Keys.RETURN);
        driver.findElement(By.id("address-ui-widgets-addr-details-address-type-and-business-hours")).click();
        Select addressType = new Select(driver.findElement(
                By.cssSelector("[name='address-ui-widgets-addr-details-address-type-and-business-hours']")));
        actions.moveToElement(addressType.getWrappedElement());
        actions.perform();
        String dropDownSelector = "a[data-value='COM']";
        waitTillSelectorVisible(dropDownSelector);
        driver.findElement(By.cssSelector(dropDownSelector)).click();
        driver.findElement(By.id("address-ui-widgets-form-submit-button input")).click();

        WebElement shippingOptionForm = driver.findElement(By.id("shippingOptionForm"));
        String allText = shippingOptionForm.getText();
        assert allText.contains("Magnifique");
        System.out.println(allText);

    }

    private void amazonLogin() {
        driver.get("http://www.amazon.in");
        driver.findElement(By.id("nav-link-accountList")).click();
        driver.findElement(By.id("ap_email")).sendKeys("colouredpages@gmail.com");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("ap_password")).sendKeys("");
        driver.findElement(By.id("signInSubmit")).click();
    }

    private void waitTillSelectorVisible(String selector) {
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        WebElement selectVis = driver.findElement(By.cssSelector(selector));
        wait.until(ExpectedConditions.elementToBeClickable(selectVis));
    }

    private WebElement waitForCartCount(String count) {
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        WebElement cartCount = driver.findElement(By.id("nav-cart-count"));
        wait.until(ExpectedConditions.textToBePresentInElement(cartCount, count));
        return cartCount;
    }

    private void addToCart() {
        driver.findElement(By.id("add-to-cart-button")).click();

    }

}

