package com.globant.screens;

import com.globant.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SwipeScreen extends BaseScreen {
    private static final String TITLE = "UiSelector().textContains(\"Swipe horizontal\")";
    private static final String SUB_TITLE = "UiSelector().textContains(\"Or swipe vertical to find what I'm hiding.\")";
    private static final String CARDS_CONTAINER = "//android.view.ViewGroup[@content-desc=\"Carousel\"]/android.view.ViewGroup/android.view.ViewGroup";
    private static final String CARDS_LIST = "//android.view.ViewGroup[contains(@resource-id, \"__CAROUSEL_ITEM\")]";
    private static final String LAST_CARD = "//android.widget.TextView[contains(@text, \"COMPATIBLE\")]";
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

    // Dynamic list
    private List<WebElement> visibleCards;


    public SwipeScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isTitleDisplayed() {
        try {
            return this.titleTxt.isDisplayed() && this.subTitleTxt.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
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

    private List<WebElement> getVisibleCards() throws RuntimeException {
        List<WebElement> cards = super.driver.findElements(By.xpath(SwipeScreen.CARDS_LIST));
        if (cards == null || cards.isEmpty())
            throw new RuntimeException("SwipeScreen - Visible Cards: Invalid card");

        return cards;
    }

    private WebElement getDisplayedCard() {
        return this.getVisibleCards().get(0);
    }

    public void swipeLeftOnCards() {
        Point start = new Point(1100, this.getYPosContainerCards());
        Point end = new Point(300, this.getYPosContainerCards());
        super.swipeByCoords(start, end);
    }

    public void swipeRightOnCards() {
        Point start = new Point(300, this.getYPosContainerCards());
        Point end = new Point(1100, this.getYPosContainerCards());
        super.swipeByCoords(start, end);
    }

    public boolean swipeAndVerifyCardsIsHide() {
        if (this.isLastCardDisplayed()) {
            // The last card is not hidden, so is necessary go 2 cards to right
            this.swipeRightOnCards();
            this.swipeRightOnCards();
        }

        WebElement displayedCard = this.getDisplayedCard();
        this.swipeLeftOnCards();

        try {
            return (!displayedCard.isDisplayed());
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public boolean isLastCardDisplayed() {
        try {
            WebElement lastCard = super.driver.findElement(By.xpath(SwipeScreen.LAST_CARD));

            return (this.getVisibleCards().size() == 1 && lastCard.isDisplayed());
        } catch (NoSuchElementException e) {
            return false;
        }
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

    public void swipeToStart() {
        while (!this.isTitleDisplayed()) {
            super.verticalUpSwipe();
        }
    }

    public void swipeLastCard() {
        while (!this.isLastCardDisplayed()) {
            this.swipeLeftOnCards();
        }
    }

    public String getEndLabelTxt() {
        return this.endLabelTxt.getText();
    }

    public int totalDisplayedCards() {
        return this.getVisibleCards().size();
    }
}
