package com.globant.tests;

import com.globant.screens.*;
import com.globant.utils.baseTest.BaseTest;
import org.openqa.selenium.Point;
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
        WebViewScreen webViewScreen = this.homeScreen.tapOnOptionByA11yId("Webview");
        Assert.assertNotNull(webViewScreen);
        Assert.assertTrue(webViewScreen.isNavMenuBtnDisplayed());
        Assert.assertTrue(webViewScreen.isNavMenuLogoDisplayed());
        Assert.assertTrue(webViewScreen.isPageLogoDisplayed());
    }

    @Test
    public void navigateToLoginAndSignUpScreen() {
        LoginAndSignUpScreen loginScreen = this.homeScreen.tapOnOptionByA11yId("Login");
        Assert.assertNotNull(loginScreen);
        Assert.assertTrue(loginScreen.isTitleDisplayed());
        Assert.assertTrue(loginScreen.isFormsBtnDisplayed());
        Assert.assertTrue(loginScreen.isLoginFormDisplayed());
        Assert.assertTrue(loginScreen.isNoteDisplayed());
        Assert.assertTrue(loginScreen.isLoginBtnDisplayed());

        loginScreen.tapOnSignUpFormOption();
        Assert.assertTrue(loginScreen.isSignUpFormDisplayed());
        Assert.assertTrue(loginScreen.isSignUpBtnDisplayed());
    }

    @Test
    public void navigateToFormsScreen() {
        FormsScreen formsScreen = this.homeScreen.tapOnOptionByA11yId("Forms");
        Assert.assertNotNull(formsScreen);
        Assert.assertTrue(formsScreen.isTitleDisplayed());
        String testString = "TESTING INPUT FIELD";
        formsScreen.setTextInInputField(testString);
        Assert.assertEquals(formsScreen.getTextFromInputViewer(), testString);
        Assert.assertEquals(formsScreen.getSwitchStatusText(), "Click to turn the switch ON");
        formsScreen.tapOnSwitch();
        Assert.assertEquals(formsScreen.getSwitchStatusText(), "Click to turn the switch OFF");
        formsScreen.tapOnDropdown();
        Assert.assertTrue(formsScreen.isModalDropdownDisplayed());
        String selectedOptionTxt = formsScreen.tapOnDropdownSection(1);
        Assert.assertEquals(formsScreen.getDropdownTxt(), selectedOptionTxt);
        Assert.assertFalse(formsScreen.isInactiveBtnCheckable());
        formsScreen.tapOnActiveBtn();
        Assert.assertTrue(formsScreen.isActiveModalDisplayed());
        formsScreen.tapOnCloseModalActiveBtn();
    }

    @Test
    public void navigateToSwipeScreen() {
        SwipeScreen swipeScreen = this.homeScreen.tapOnOptionByA11yId("Swipe");
        Assert.assertNotNull(swipeScreen);
        Assert.assertTrue(swipeScreen.isTitleDisplayed());
        Assert.assertTrue(swipeScreen.isCardsContainerDisplayed());
        swipeScreen.swipeToEnd();
        Assert.assertTrue(swipeScreen.isEndLogoDisplayed());
        Assert.assertTrue(swipeScreen.isEndLabelDisplayed());
    }
}
