package com.globant.tests;

import com.globant.screens.HomeScreen;
import com.globant.screens.LoginAndSignUpScreen;
import com.globant.utils.baseTest.BaseTest;
import com.globant.utils.persistence.UserDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SuccessfulSignUpTest extends BaseTest {
    private LoginAndSignUpScreen loginAndSignUpScreen;

    @BeforeTest
    public void navigateToLoginScreen() {
        HomeScreen homeScreen = super.loadHomeScreen();
        Assert.assertNotNull(homeScreen);
        Assert.assertTrue(homeScreen.isNavbarDisplayed());

        this.loginAndSignUpScreen = homeScreen.tapOnOptionByA11yId("Login");
        Assert.assertNotNull(this.loginAndSignUpScreen);
        Assert.assertTrue(this.loginAndSignUpScreen.isFormsBtnDisplayed());
    }

    @Test(dataProvider = "user-signup", dataProviderClass = UserDataProvider.class)
    public void signUp(String email, String password) {
        this.loginAndSignUpScreen.tapOnSignUpFormOption();
        Assert.assertTrue(this.loginAndSignUpScreen.isSignUpFormDisplayed());

        this.loginAndSignUpScreen.setEmail(email);
        this.loginAndSignUpScreen.setPassword(password);
        this.loginAndSignUpScreen.setConfirmPassword(password);
        this.loginAndSignUpScreen.tapOnSignUpBtn();
        Assert.assertTrue(this.loginAndSignUpScreen.isConfirmSignUpDisplayed());
        this.loginAndSignUpScreen.tapOnConfirmSignUpBtn();
        Assert.assertTrue(this.loginAndSignUpScreen.isSignUpFormDisplayed());
    }
}
