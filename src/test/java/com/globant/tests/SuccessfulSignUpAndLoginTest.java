package com.globant.tests;

import com.globant.screens.HomeScreen;
import com.globant.screens.LoginAndSignUpScreen;
import com.globant.utils.baseTest.BaseTest;
import com.globant.utils.persistence.UserDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SuccessfulSignUpAndLoginTest extends BaseTest {
    private LoginAndSignUpScreen loginAndSignUpScreen;

    @BeforeMethod()
    public void navigateToLoginScreen() {
        HomeScreen homeScreen = super.loadHomeScreen();
        Assert.assertNotNull(homeScreen);
        Assert.assertTrue(homeScreen.isNavbarDisplayed());

        this.loginAndSignUpScreen = homeScreen.tapOnOptionByA11yId("Login");
        Assert.assertNotNull(this.loginAndSignUpScreen);
        Assert.assertTrue(this.loginAndSignUpScreen.isFormsBtnDisplayed());
    }

    @Test(dataProvider = "user-signup", dataProviderClass = UserDataProvider.class, priority = 1)
    public void verifySuccessfulSignUp(String email, String password) {
        this.loginAndSignUpScreen.tapOnSignUpFormOption();
        Assert.assertTrue(this.loginAndSignUpScreen.isSignUpFormDisplayed());
        Assert.assertTrue(this.loginAndSignUpScreen.isSignUpBtnDisplayed());

        this.loginAndSignUpScreen.setEmail(email);
        this.loginAndSignUpScreen.setPassword(password);
        this.loginAndSignUpScreen.setConfirmPassword(password);
        this.loginAndSignUpScreen.tapOnSignUpBtn();
        Assert.assertTrue(this.loginAndSignUpScreen.isConfirmActionBtnDisplayed());
        this.loginAndSignUpScreen.tapOnConfirmActionBtn();
        Assert.assertTrue(this.loginAndSignUpScreen.isSignUpFormDisplayed());
    }

    @Test(dataProvider = "user-login", dataProviderClass = UserDataProvider.class, priority = 2)
    public void verifySuccessfulLogin(String email, String password) {
        this.loginAndSignUpScreen.tapOnLoginFormOption();
        Assert.assertTrue(this.loginAndSignUpScreen.isLoginFormDisplayed());
        Assert.assertTrue(this.loginAndSignUpScreen.isLoginBtnDisplayed());

        this.loginAndSignUpScreen.setEmail(email);
        this.loginAndSignUpScreen.setPassword(password);
        this.loginAndSignUpScreen.tapOnLoginBtn();
        Assert.assertTrue(this.loginAndSignUpScreen.isConfirmActionBtnDisplayed());
        this.loginAndSignUpScreen.tapOnConfirmActionBtn();
        Assert.assertTrue(this.loginAndSignUpScreen.isLoginFormDisplayed());
    }
}
