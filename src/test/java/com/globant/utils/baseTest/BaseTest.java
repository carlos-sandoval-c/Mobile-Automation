package com.globant.utils.baseTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class BaseTest {
    private static final String PROPERTIES_PATH = "src/test/resources/config.properties";
    private static final Properties properties = new Properties();

    protected AndroidDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());

    private void setupProperties() throws RuntimeException {
        try {
            FileInputStream fileInputStream = new FileInputStream(BaseTest.PROPERTIES_PATH);
            properties.load(fileInputStream);

        } catch (IOException e) {
            throw new RuntimeException("BaseTest - Setup Properties: FileInput error");
        }
    }

    @BeforeTest(alwaysRun = true)
    protected void setupEnvironment() {
        this.setupProperties();
        UiAutomator2Options capabilities = new UiAutomator2Options();
    }
}
