//package org.example.StepDefinitions;
//
////import cucumber.api.java.en.Given;
////import cucumber.api.java.en.Then;
////import cucumber.api.java.en.When;
//import io.appium.java_client.AppiumBy;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.example.test.BaseTest;
//import org.openqa.selenium.By;
//
//import java.net.MalformedURLException;
//
//public class MySteps extends BaseTest {
//    @Given("usuario abre la aplicacion")
//    public void usuarioAbreLaAplicacion() throws MalformedURLException {
//        ConfigureAppium();
//    }
//
//    @When("usuario realiza operaciones")
//    public void usuarioRealizaOperaciones() {
//        //CLICK EN "VIEWS"
//        driver.findElement(AppiumBy.accessibilityId("Views")).click();
//        //Click en "animations"
//        driver.findElement(AppiumBy.accessibilityId("Animation")).click();
//        //Click en Xpath
//        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3D Transition\"]")).click();
//    }
//
//    @Then("cierra la aplicacion")
//    public void cierraLaAplicacion() {
//        tearDown();
//    }
//}
