package org.example.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class BaseTest {

    public AndroidDriver driver;
    public  AppiumDriverLocalService service;
    public Properties prop = new Properties();

    public void  ConfigureAppium() throws MalformedURLException {
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {

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

    public void takeScreenshot(Boolean isCorrect) throws IOException {
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
        String screenshotType = isCorrect ? "1" : "0";
        File targetFile = new File("target/screenshots/" + timeStamp + screenshotType + ".jpg");
        FileUtils.copyFile(srcFile,targetFile);
    }

    public void tearDown() {
        driver.quit();
        service.stop();
    }
}
