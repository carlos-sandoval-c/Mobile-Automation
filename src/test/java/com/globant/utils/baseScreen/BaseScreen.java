package com.globant.utils.baseScreen;

import com.globant.utils.ScreenMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class BaseScreen {
    protected AndroidDriver driver;
    protected WebDriverWait wait;
    protected final static PointerInput FINGER = new PointerInput(PointerInput.Kind.TOUCH, "finger");
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

    protected void swipeByCoords(Point start, Point end) {
        Sequence swipe = new Sequence(BaseScreen.FINGER, 1)
                .addAction(BaseScreen.FINGER.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), start.getX(), start.getY()))
                .addAction(BaseScreen.FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(BaseScreen.FINGER.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), end.getX(), end.getY()))
                .addAction(BaseScreen.FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        this.driver.perform(Collections.singletonList(swipe));
    }

    protected void verticalDownSwipe() {
        Dimension windowSize = this.driver.manage().window().getSize();
        Point start = new Point(windowSize.getWidth() / 2, windowSize.getHeight() / 2);
        Point end = new Point(windowSize.getWidth() / 2, 0);
        this.swipeByCoords(start, end);
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
