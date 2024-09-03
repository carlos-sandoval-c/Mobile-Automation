package com.globant.tests;

import com.globant.screens.LoginAndSignUpScreen;
import com.globant.utils.baseTest.BaseTest;
import com.globant.utils.persistence.UserDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class SuccessfulLoginTest extends BaseTest {
    private LoginAndSignUpScreen loginAndSignUpScreen;

    @BeforeTest
    public void navigateToLoginScreenAndCreateUser() {
        SuccessfulSignUpTest signUpTest = new SuccessfulSignUpTest();
        signUpTest.setupEnvironment();
        this.loginAndSignUpScreen = signUpTest.navigateToLoginScreen();

        UserDataProvider dataProvider = new UserDataProvider();
        List<String[]> data = dataProvider.getSignUpUserDataAsList();
        Assert.assertTrue(!data.isEmpty() && data.get(0).length == 2);
        String[] values = data.get(0);
        signUpTest.verifySuccessfulSignUp(values[0], values[1]);
    }

    @Test(dataProvider = "user-login", dataProviderClass = UserDataProvider.class)
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
