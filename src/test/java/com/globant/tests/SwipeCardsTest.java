package com.globant.tests;

import com.globant.screens.HomeScreen;
import com.globant.screens.SwipeScreen;
import com.globant.utils.baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SwipeCardsTest extends BaseTest {
    private SwipeScreen swipeScreen;

    @BeforeMethod()
    public void navigateToSwipeScreen() {
        HomeScreen homeScreen = super.loadHomeScreen();
        Assert.assertNotNull(homeScreen);
        Assert.assertTrue(homeScreen.isNavbarDisplayed());

        this.swipeScreen = homeScreen.tapOnOptionByA11yId("Swipe");
        Assert.assertNotNull(this.swipeScreen);
        Assert.assertTrue(this.swipeScreen.isTitleDisplayed());
        Assert.assertTrue(this.swipeScreen.isCardsContainerDisplayed());
    }

    @Test(priority = 1)
    public void verifySwipeCards() {
        this.swipeScreen.swipeToStart();
        Assert.assertTrue(this.swipeScreen.isCardsContainerDisplayed());
        Assert.assertTrue(this.swipeScreen.swipeAndVerifyCardsIsHide());
    }

    @Test(priority = 2)
    public void verifyLastCardIsOnlyCardDisplayed() {
        this.swipeScreen.swipeToStart();
        Assert.assertTrue(this.swipeScreen.isCardsContainerDisplayed());
        this.swipeScreen.swipeLastCard();
        Assert.assertEquals(this.swipeScreen.totalDisplayedCards(), 1);
    }

    @Test(priority = 3)
    public void verifyHiddenText() {
        this.swipeScreen.swipeToEnd();
        Assert.assertTrue(this.swipeScreen.isEndLogoDisplayed());
        Assert.assertTrue(this.swipeScreen.isEndLabelDisplayed());
        Assert.assertEquals(this.swipeScreen.getEndLabelTxt(), "You found me!!!");
    }
}
