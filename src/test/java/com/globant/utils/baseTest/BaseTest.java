package com.globant.utils.baseTest;

import com.globant.screens.HomeScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


public class BaseTest {
    private static final String PROPERTIES_PATH = "src/test/resources/config.properties";
    private static final Properties properties = new Properties();

    protected AndroidDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());

    private static String getCapability(String keyName) {
        return BaseTest.properties.getProperty(keyName);
    }

    private void loadProperties() throws RuntimeException {
        try {
            FileInputStream fileInputStream = new FileInputStream(BaseTest.PROPERTIES_PATH);
            properties.load(fileInputStream);
            this.logger.debug("BaseTest - LoadProperties: Properties correctly loaded");
        } catch (IOException e) {
            this.logger.error("BaseTest - LoadProperties: FileInput properties generated IOException");
            throw new RuntimeException("BaseTest - Setup Properties: FileInput error");
        }
    }

    private void setupCapabilities(UiAutomator2Options capabilities) {
        capabilities.setPlatformName(BaseTest.getCapability("platformName"));
        capabilities.setDeviceName(BaseTest.getCapability("deviceName"));
        capabilities.setAppPackage(BaseTest.getCapability("appPackage"));
        capabilities.setAppActivity(BaseTest.getCapability("appActivity"));
        capabilities.setCapability("newCommandTimeout", BaseTest.getCapability("newCommandTimeout"));
    }

    @BeforeClass(alwaysRun = true)
    public void setupEnvironment() {
        this.loadProperties();
        UiAutomator2Options capabilities = new UiAutomator2Options();
        this.setupCapabilities(capabilities);

        try {
            this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
            this.logger.info("BaseTest - Setup Environment: Driver Initialized");
        } catch (MalformedURLException e) {
            this.logger.error("BaseTest - Setup Environment: Error creating driver");
            throw new RuntimeException("BaseTest - Setup Environment: Error with URL");
        }
    }

    protected HomeScreen loadHomeScreen() {
        if (this.driver == null) {
            this.setupEnvironment();
            this.logger.info("RECREATE DRIVER");
        }

        HomeScreen homeScreen = new HomeScreen(this.driver);
        homeScreen.tapOnOptionByA11yId("home");
        return homeScreen;
    }
}
