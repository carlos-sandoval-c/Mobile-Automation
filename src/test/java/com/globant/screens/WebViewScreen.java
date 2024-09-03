package com.globant.screens;

import com.globant.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class WebViewScreen extends BaseScreen {
    private static final String TOGGLE_NAV_MENU_BTN = "//android.widget.Button[contains(@text, \"Toggle navigation bar\")]";
    private static final String NAV_MENU_LOGO_IMG = "WebdriverIO";
    private static final String PAGE_LOGO_IMG = "UiSelector().textContains(\"WebdriverIO\")";

    @AndroidFindBy(xpath = WebViewScreen.TOGGLE_NAV_MENU_BTN)
    private WebElement toggleNavMenuBtn;
    @AndroidFindBy(accessibility = WebViewScreen.NAV_MENU_LOGO_IMG)
    private WebElement navMenuImg;
    @AndroidFindBy(uiAutomator = WebViewScreen.PAGE_LOGO_IMG)
    private WebElement pageLogoImg;

    public WebViewScreen(AndroidDriver driver) {
        super(driver);

        // Bypass load screen
        super.fluentWaitElementIsDisplayedByXpath(WebViewScreen.TOGGLE_NAV_MENU_BTN);
    }

    public boolean isNavMenuBtnDisplayed() {
        return this.toggleNavMenuBtn.isDisplayed();
    }

    public boolean isNavMenuLogoDisplayed() {
        return this.navMenuImg.isDisplayed();
    }

    public boolean isPageLogoDisplayed() {
        return this.pageLogoImg.isDisplayed();
    }
}
