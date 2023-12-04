package org.example.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.Capabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseTest {

    public AndroidDriver driver;
    public  AppiumDriverLocalService service;

    public void  ConfigureAppium() throws MalformedURLException {
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);

            service = new AppiumServiceBuilder().withAppiumJS(
                new File(prop.getProperty("APPIUM_LOCATION"))
            )
            .withIPAddress("127.0.0.1")
            .usingPort(4723)
            .build();
            service.start();

            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("emulator-5554");
            options.setApp(prop.getProperty("APK_LOCATION"));
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), (Capabilities) options);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void tearDown() {
        driver.quit();
        service.stop();
    }
}
