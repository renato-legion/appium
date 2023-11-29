package org.example.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.Capabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    public AndroidDriver driver;
    public  AppiumDriverLocalService service;

    public void  ConfigureAppium() throws MalformedURLException {
         service = new AppiumServiceBuilder().withAppiumJS(
            new File("//usr//local//lib//node_modules/appium//build//lib//main.js")
            )
            .withIPAddress("127.0.0.1")
            .usingPort(4723)
            .build();

        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setApp("/Users/t24453/Desktop/Projects/QA/appium/src/test/resources/ApiDemos-debug.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), (Capabilities) options);

    }


    public void tearDown() {
        driver.quit();
        service.stop();
    }
}
