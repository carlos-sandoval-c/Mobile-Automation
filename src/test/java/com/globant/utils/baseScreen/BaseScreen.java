package com.globant.utils.baseScreen;

import com.globant.utils.ScreenMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
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

    protected void waitElementsAreDisplayed(List<WebElement> elementList) {
        this.wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
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

    private WebElement getOptionByA11yId(String accessibilityId) throws RuntimeException {
        if (accessibilityId == null || accessibilityId.isEmpty())
            throw new RuntimeException("BaseScreen - GetOptionByAccessibilityId: Invalid Id");

        for (WebElement element : this.navbarBtnList) {
            if (element.getAttribute("content-desc").equalsIgnoreCase(accessibilityId))
                return element;
        }

        throw new RuntimeException("BaseScreen - GetOptionByAccessibilityId: Unknown option");
    }

    /**
     * A mapping of the screens accessible through the navbar is made, this map contains the classes, and they are
     * consulted through their accessibility id, this method uses reflect utilities to obtain the constructor and
     * generate the instance corresponding to the selected screen.
     * <p>
     * Generics are used to avoid the need to repeat the navbar validation code, since it is independent of the state
     * in the test flow.
     *
     * @see ScreenMap
     */
    public <T extends BaseScreen> T tapOnOptionByA11yId(String accessibilityId) {
        WebElement optionBtn = this.getOptionByA11yId(accessibilityId);
        this.waitElementIsDisplayed(optionBtn);
        this.waitElementIsClickable(optionBtn);
        optionBtn.click();

        try {
            return (T) ScreenMap.getClassByA11yId(accessibilityId).getDeclaredConstructor(AndroidDriver.class).newInstance(this.driver);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
