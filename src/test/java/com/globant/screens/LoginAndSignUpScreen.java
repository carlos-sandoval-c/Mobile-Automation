package com.globant.screens;

import com.globant.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginAndSignUpScreen extends BaseScreen {
    private static final String TITLE_TXT = "UiSelector().textContains(\"Login / Sign up Form\")";
    private static final String LOGIN_FORM_BNT = "button-login-container";
    private static final String SIGN_UP_FORM_BNT = "button-sign-up-container";
    private static final String EMAIL_INPUT = "input-email";
    private static final String PASSWORD_INPUT = "input-password";
    private static final String CONFIRM_PASSWORD_INPUT = "input-repeat-password";
    private static final String NOTE_LOGIN = "UiSelector().textContains(\"When the device has Touch/FaceID (iOS) or FingerPrint enabled a biometrics button will be shown to use and test the login.\")";
    private static final String LOGIN_BTN = "button-LOGIN";
    private static final String SIGN_UP_BTN = "button-SIGN UP";

    @AndroidFindBy(uiAutomator = LoginAndSignUpScreen.TITLE_TXT)
    private WebElement titleTxt;
    @AndroidFindBy(accessibility = LoginAndSignUpScreen.LOGIN_FORM_BNT)
    private WebElement loginFormBtn;
    @AndroidFindBy(accessibility = LoginAndSignUpScreen.SIGN_UP_FORM_BNT)
    private WebElement signUpFormBtn;
    @AndroidFindBy(accessibility = LoginAndSignUpScreen.EMAIL_INPUT)
    private WebElement emailInput;
    @AndroidFindBy(accessibility = LoginAndSignUpScreen.PASSWORD_INPUT)
    private WebElement passwordInput;
    @AndroidFindBy(accessibility = LoginAndSignUpScreen.CONFIRM_PASSWORD_INPUT)
    private WebElement confirmPasswordInput;
    @AndroidFindBy(uiAutomator = LoginAndSignUpScreen.NOTE_LOGIN)
    private WebElement noteLoginTxt;
    @AndroidFindBy(accessibility = LoginAndSignUpScreen.LOGIN_BTN)
    private WebElement loginBtn;
    @AndroidFindBy(accessibility = LoginAndSignUpScreen.SIGN_UP_BTN)
    private WebElement signUpBtn;

    public LoginAndSignUpScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isTitleDisplayed() {
        return this.titleTxt.isDisplayed();
    }

    public boolean isFormsBtnDisplayed() {
        return this.loginFormBtn.isDisplayed() && this.signUpFormBtn.isDisplayed();
    }

    public boolean isLoginFormDisplayed() {
        return this.emailInput.isDisplayed() && this.passwordInput.isDisplayed();
    }

    public boolean isSignUpFormDisplayed() {
        return this.isLoginFormDisplayed() && this.confirmPasswordInput.isDisplayed();
    }

    public boolean isNoteDisplayed() {
        return this.noteLoginTxt.isDisplayed();
    }

    public boolean isLoginBtnDisplayed() {
        return this.loginBtn.isDisplayed();
    }

    public boolean isSignUpBtnDisplayed() {
        return this.signUpBtn.isDisplayed();
    }

    public void tapOnSignUpFormOption() {
        super.waitElementIsClickable(this.signUpFormBtn);
        this.signUpFormBtn.click();
    }
}
