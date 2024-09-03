package com.globant.utils.baseScreen;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class BaseScreen {
    protected AndroidDriver driver;
    protected WebDriverWait wait;
    protected FluentWait<AndroidDriver> fluentWait;

    protected static final String NAVBAR_BNT_LIST = "//android.view.ViewGroup/android.view.View/android.view.View";
    @AndroidFindBy(xpath = BaseScreen.NAVBAR_BNT_LIST)
    protected List<WebElement> navbarBtnList;

    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        this.fluentWait = new FluentWait<>(this.driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    protected void waitElementIsDisplayed(WebElement element) {
        this.wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitElementIsClickable(WebElement element) {
        this.wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void fluentWaitElementIsDisplayedByXpath(String xpathQuery) {
        this.fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathQuery)));
    }

    public boolean isNavbarDisplayed() {
        return this.navbarBtnList.stream().allMatch(WebElement::isDisplayed);
    }
}
