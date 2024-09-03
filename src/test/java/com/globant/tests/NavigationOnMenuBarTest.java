package com.globant.tests;

import com.globant.screens.HomeScreen;
import com.globant.screens.WebViewScreen;
import com.globant.utils.baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NavigationOnMenuBarTest extends BaseTest {
    private HomeScreen homeScreen;

    @BeforeTest(alwaysRun = true)
    public void navigateToHomeScreen() {
        this.homeScreen = super.loadHomeScreen();
        Assert.assertNotNull(this.homeScreen);
        Assert.assertTrue(this.homeScreen.isTitleImagesDisplayed());
        Assert.assertTrue(this.homeScreen.isTitleDisplayed());
        Assert.assertTrue(this.homeScreen.isSubTitleDisplayed());
        Assert.assertTrue(this.homeScreen.isNavbarDisplayed());
    }

    @Test
    public void navigateToWebViewScreen() {
        WebViewScreen webViewScreen = this.homeScreen.tapOnWebViewOption();
        Assert.assertNotNull(webViewScreen);
        Assert.assertTrue(webViewScreen.isNavMenuBtnDisplayed());
        Assert.assertTrue(webViewScreen.isNavMenuLogoDisplayed());
        Assert.assertTrue(webViewScreen.isPageLogoDisplayed());

    }
}
