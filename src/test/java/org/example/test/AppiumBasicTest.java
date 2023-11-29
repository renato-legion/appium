package org.example.test;


import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class AppiumBasicTest extends BaseTest {

    @Test
    public void AppiumTest() throws MalformedURLException {
        ConfigureAppium();
        //PARA APK GENERICA
        //CLICK EN "VIEWS"
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        //Click en "animations"
        driver.findElement(AppiumBy.accessibilityId("Animation")).click();
        //Click en Xpath
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3D Transition\"]")).click();
        tearDown();
    }
}