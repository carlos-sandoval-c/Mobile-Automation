package com.globant.screens;

import com.globant.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomeScreen extends BaseScreen {
    private static final String LOGO_IMG_LIST = "//android.widget.ImageView";
    private static final String TITLE_TXT = "UiSelector().textContains(\"WEBDRIVER\")";
    private static final String SUB_TITLE_TXT = "UiSelector().textContains(\"Demo app for the appium-boilerplate\")";
    private static final String WEB_VIEW_BTN = "Webview";

    @AndroidFindBy(xpath = HomeScreen.LOGO_IMG_LIST)
    private List<WebElement> logoImgList;
    @AndroidFindBy(uiAutomator = HomeScreen.TITLE_TXT)
    private WebElement titleTxt;
    @AndroidFindBy(uiAutomator = HomeScreen.SUB_TITLE_TXT)
    private WebElement subTitleTxt;
    @AndroidFindBy(accessibility = HomeScreen.WEB_VIEW_BTN)
    private WebElement webViewBtn;

    public HomeScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isTitleImagesDisplayed() {
        return this.logoImgList.stream().allMatch(WebElement::isDisplayed);
    }

    public boolean isTitleDisplayed() {
        return this.titleTxt.isDisplayed();
    }

    public boolean isSubTitleDisplayed() {
        return this.subTitleTxt.isDisplayed();
    }

    public WebViewScreen tapOnWebViewOption() {
        super.waitElementIsDisplayed(this.webViewBtn);
        super.waitElementIsClickable(this.webViewBtn);

        this.webViewBtn.click();
        return new WebViewScreen(super.driver);
    }
}
