package org.example.test;


import io.appium.java_client.AppiumBy;
import org.junit.Test;
import org.openqa.selenium.By;

import java.net.MalformedURLException;


public class AppiumBasic extends BaseTest {

    @Test
    public void AppiumTest() throws MalformedURLException {
        ConfigureAppium();

// PARA WARDA
//        driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"com.bcp.fem:id/keyedWardaCardView\"]/android.view.ViewGroup")).click();
//        driver.findElement(By.id("com.bcp.fem:id/textInputEditTextAccessToken")).sendKeys("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsIm9yZy5hcGVyZW8uY2FzLnNlcnZpY2VzLlJlZ2lzdGVyZWRTZXJ2aWNlIjoiMTAwMDAzMzgifQ.eyJzdWIiOiIyMGJlNWU4ZS1mYmY4LTQ4NDctOGNmYS02YTdmNDRlZTY0MjMiLCJyb2xlcyI6W10sImlzcyI6IkNBU19BVVRIIiwiYXBwQ29kZSI6IkJNIiwidXNlckFwcCI6IkFQQk1DRVIiLCJub25jZSI6IiIsImNsaWVudF9pZCI6IjIwYmU1ZThlLWZiZjgtNDg0Ny04Y2ZhLTZhN2Y0NGVlNjQyMyIsImF1ZCI6IjIwYmU1ZThlLWZiZjgtNDg0Ny04Y2ZhLTZhN2Y0NGVlNjQyMyIsImdyYW50X3R5cGUiOiJDTElFTlRfQ1JFREVOVElBTFMiLCJwZXJtaXNzaW9ucyI6W10sInNjb3BlIjpbInNkay13cmRhLW1vYmlsZSIsImJzLWV2YWx1YXRpb24tdjQiLCJicy1wcm9kdWN0LXY0IiwiYnMtcGVyc29uLXY1IiwiYnMtc2F2aW5nLWFjY291bnQtdjMiLCJicy1ub3RpZmljYXRpb24tdjMiLCJicy1hY2NvdW50LXRyYW5zZmVyLXYyIiwiYnMtbW9uZXRhcnktb3BlcmF0aW9uLW5vdGlmaWNhdGlvbi12MSIsImJzLW1vbmV0YXJ5LW9wZXJhdGlvbi12MSIsImJzLW9wZXJhdGlvbi10ZXJtaW5hbC12MSIsImJzLW9wZXJhdGlvbi1udW1iZXItdjEiLCJicy13YXJkYS1zZXNzaW9uLXYxIl0sImNsYWltcyI6W10sInNjb3BlcyI6InNkay13cmRhLW1vYmlsZSBicy1ldmFsdWF0aW9uLXY0IGJzLXByb2R1Y3QtdjQgYnMtcGVyc29uLXY1IGJzLXNhdmluZy1hY2NvdW50LXYzIGJzLW5vdGlmaWNhdGlvbi12MyBicy1hY2NvdW50LXRyYW5zZmVyLXYyIGJzLW1vbmV0YXJ5LW9wZXJhdGlvbi1ub3RpZmljYXRpb24tdjEgYnMtbW9uZXRhcnktb3BlcmF0aW9uLXYxIGJzLW9wZXJhdGlvbi10ZXJtaW5hbC12MSBicy1vcGVyYXRpb24tbnVtYmVyLXYxIGJzLXdhcmRhLXNlc3Npb24tdjEiLCJzdGF0ZSI6IiIsImV4cCI6MTY5OTMyMjkxNSwiaWF0IjoxNjk5MzIyNjE1LCJqdGkiOiJBVC02OTMzODAtLXFOeFoyOGZCVVpEMnk1cHRCMDVjMFoxUzJLekgzTWQifQ.ipHHISIxd4r4H9fhZPJJ0Gr74SHPnW8ON4TIdDBqnQTgYcSZr0rYt_8VBePRjJEWKzmGjDbrqP_nYQJ3QBjGuHvP-2FNLUYLbwZxn2Jyf0ySujqm1JI2HvldAYMg1BLI7SHqgz2mqwteawpspvMnF3DCgqyGK8c2tvH6YXSWoMQuBSUAq1RRiCTUEtiNQg_aHDOnPK1lNaa-Zd4C85w6XaHvEN2LPx724qNF57sV604ZmUTYN7OiHhW7kibeHv60D2WrLKW9vHdeNKYZtiq8Y3vDeCPWQIo2n07FiUXtUQgmfuLfj0mEeLg6gp1sH8EmRVkZyGy-Cgi1llKCgY73Rw");
//
//        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
//                "left", 200, "top", 200, "width", 1500, "height", 1500,
//                "direction", "down",
//                "percent", 80.0
//        ));
//
//        driver.findElement(By.id("com.bcp.fem:id/textInputWardaContextId")).sendKeys("aa438d9f-8708-4aa7-9646-93413a1f476f");
//    driver.findElement(By.id("com.bcp.fem:id/wardaFrontDoorSavingWithWardaImageView")).click();


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
