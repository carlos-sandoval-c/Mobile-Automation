package com.globant.screens;

import com.globant.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomeScreen extends BaseScreen {
    private static final String LOGO_IMG = "//android.widget.ImageView[1]";
    @AndroidFindBy(xpath = HomeScreen.LOGO_IMG)
    private WebElement logoImg;

    public HomeScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isLogoDisplayed() {
        return this.logoImg.isDisplayed();
    }
}
