package org.example.StepDefinitions;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.test.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import java.io.*;
import java.net.MalformedURLException;

public class StepsBasicFlowTest extends BaseTest {

    @Given("usuario abre la aplicacion")
    public void usuarioAbreLaAplicacion() throws MalformedURLException {
        ConfigureAppium();
    }

    @When("usuario realiza operaciones")
    public void usuarioRealizaOperaciones() throws InterruptedException, IOException {
        //CLICK EN "VIEWS"
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(500);
        takeScreenshot(true);
        //Click en "animations"
        driver.findElement(AppiumBy.accessibilityId("Animation")).click();
        Thread.sleep(500);
        //Click en Xpath
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3D Transition\"]")).click();
        Thread.sleep(500);
        takeScreenshot(true);

    }

    @Then("cierra la aplicacion")
    public void cierraLaAplicacion() {
        tearDown();
    }

    @And("usuario se dirige a Notificacion con texto")
    public void usuarioSeDirigeANotificacionConTexto() throws InterruptedException, IOException {
        driver.findElement(AppiumBy.accessibilityId("App")).click();
        Thread.sleep(500);
        takeScreenshot(true);
        driver.findElement(AppiumBy.accessibilityId("Notification")).click();
        Thread.sleep(500);
        takeScreenshot(true);
        driver.findElement(AppiumBy.accessibilityId("NotifyWithText")).click();
        Thread.sleep(500);
        takeScreenshot(true);
    }

    @When("clickea mostrar notoficacion larga")
    public void clickeaMostrarNotoficacionLarga() {
        driver.findElement(By.id("io.appium.android.apis:id/long_notify")).click();
    }


    @Then("la app te muestra una notificacion")
    public void laAppTeMuestraUnaNotificacion() throws IOException {
        Assert.assertEquals( driver.findElement(By.xpath(" //android.widget.Toast[@text=\"This is a long notification. See, you might need a second more to read it.\"]")).getText(),"This is a long notification. See, you might need a second more to read it.");
        takeScreenshot(true);
        tearDown();
    }

    @And("usuario abre Contenido")
    public void usuarioAbreMediaPLayer() throws InterruptedException, IOException {
        driver.findElement(AppiumBy.accessibilityId("Content")).click();
        Thread.sleep(500);
        takeScreenshot(true);
        driver.findElement(AppiumBy.accessibilityId("Assets")).click();
        takeScreenshot(true);
    }

    @When("clickea sobre leer asset")
    public void clickeaSobreAudioDesdeRecursos() throws InterruptedException, IOException {
        Thread.sleep(500);
        driver.findElement(AppiumBy.accessibilityId("Read Asset")).click();
        takeScreenshot(true);
    }


    @Then("la app muestra contenido de lectura")
    public void laAppReproduceElAudio() throws InterruptedException, IOException {
        Thread.sleep(500);
        Assert.assertEquals( driver.findElement(By.id("io.appium.android.apis:id/text")).getText(),"This text is stored in a raw Asset.\n" +
                "\n" +
                "It was read and placed into the TextView here.\n");
        Thread.sleep(500);
        takeScreenshot(true);
        tearDown();
    }
}
