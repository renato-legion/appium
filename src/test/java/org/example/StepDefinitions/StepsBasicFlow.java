package org.example.StepDefinitions;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.example.test.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.net.MalformedURLException;

public class StepsBasicFlow extends BaseTest {
    @Given("usuario abre la aplicacion")
    public void usuarioAbreLaAplicacion() throws MalformedURLException {
        ConfigureAppium();
    }

    @When("usuario realiza operaciones")
    public void usuarioRealizaOperaciones() {
        //CLICK EN "VIEWS"
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        //Click en "animations"
        driver.findElement(AppiumBy.accessibilityId("Animation")).click();
        //Click en Xpath
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3D Transition\"]")).click();
    }

    @Then("cierra la aplicacion")
    public void cierraLaAplicacion() {
        tearDown();
    }

    @And("usuario se dirige a Notificacion con texto")
    public void usuarioSeDirigeANotificacionConTexto() {
    driver.findElement(AppiumBy.accessibilityId("App")).click();
    driver.findElement(AppiumBy.accessibilityId("Notification")).click();
    driver.findElement(AppiumBy.accessibilityId("NotifyWithText")).click();

    }

    @When("clickea mostrar notoficacion larga")
    public void clickeaMostrarNotoficacionLarga() {
        driver.findElement(By.id("io.appium.android.apis:id/long_notify")).click();
    }

    @Then("la app te muestra una notificacion")
    public void laAppTeMuestraUnaNotificacion() {
        Assert.assertEquals( driver.findElement(By.xpath(" //android.widget.Toast[@text=\"This is a long notification. See, you might need a second more to read it.\"]")).getText(),"This is a long notification. See, you might need a second more to read it.");
        tearDown();
    }

    @And("usuario abre MediaPLayer")
    public void usuarioAbreMediaPLayer() {
        driver.findElement(AppiumBy.accessibilityId("Media")).click();
        driver.findElement(AppiumBy.accessibilityId("MediaPlayer")).click();
    }

    @When("clickea sobre audio desde recursos")
    public void clickeaSobreAudioDesdeRecursos() {
        driver.findElement(AppiumBy.accessibilityId("Play Audio from Resources")).click();
    }

    @Then("la app reproduce el audio")
    public void laAppReproduceElAudio() throws InterruptedException {
        Assert.assertEquals( driver.findElement(By.xpath( "//android.widget.TextView[@text=\"Playing audio...\"]")).getText(),"Playing audio...");
        tearDown();
    }
}
