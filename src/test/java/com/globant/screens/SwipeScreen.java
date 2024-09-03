package com.globant.screens;

import com.globant.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class SwipeScreen extends BaseScreen {
    private static final String TITLE = "UiSelector().textContains(\"Swipe horizontal\")";
    private static final String SUB_TITLE = "UiSelector().textContains(\"Or swipe vertical to find what I'm hiding.\")";
    private static final String CARDS_CONTAINER = "//android.view.ViewGroup[@content-desc=\"Carousel\"]/android.view.ViewGroup/android.view.ViewGroup";
    private static final String END_LOGO_IMG = "WebdriverIO logo";
    private static final String END_LABEL_TXT = "UiSelector().textContains(\"You found me!!!\")";

    @AndroidFindBy(uiAutomator = SwipeScreen.TITLE)
    private WebElement titleTxt;
    @AndroidFindBy(uiAutomator = SwipeScreen.SUB_TITLE)
    private WebElement subTitleTxt;
    @AndroidFindBy(xpath = SwipeScreen.CARDS_CONTAINER)
    private WebElement cardsContainer;
    @AndroidFindBy(accessibility = SwipeScreen.END_LOGO_IMG)
    private WebElement endLogoImg;
    @AndroidFindBy(uiAutomator = SwipeScreen.END_LABEL_TXT)
    private WebElement endLabelTxt;

    public SwipeScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isTitleDisplayed() {
        return this.titleTxt.isDisplayed() && this.subTitleTxt.isDisplayed();
    }

    public boolean isCardsContainerDisplayed() {
        return this.cardsContainer.isDisplayed();
    }

    public boolean isEndLogoDisplayed() {
        return this.endLogoImg.isDisplayed();
    }

    public boolean isEndLabelDisplayed() {
        return this.endLabelTxt.isDisplayed();
    }

    private int getYPosContainerCards() {
        Point containerLocation = this.cardsContainer.getLocation();
        return (containerLocation.getY() + (this.cardsContainer.getSize().getHeight() / 2));
    }

    public void swipeLeftOnCards() {
        Point start = new Point(1100, this.getYPosContainerCards());
        Point end = new Point(70, this.getYPosContainerCards());
        super.swipeByCoords(start, end);
    }

    private boolean isEndDisplayed() {
        try {
            return (this.endLogoImg.isDisplayed() && this.endLabelTxt.isDisplayed());
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void swipeToEnd() {
        while (!this.isEndDisplayed())
            super.verticalDownSwipe();
    }
}
