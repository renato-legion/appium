package org.example.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//import cucumber.api.CucumberOptions;
//import net.serenitybdd.cucumber.CucumberWithSerenity;
//import net.thucydides.core.annotations.Managed;
//import org.junit.runner.RunWith;
//import org.openqa.selenium.WebDriver;
//
//import static net.thucydides.core.annotations.ClearCookiesPolicy.BeforeEachTest;
//


//@CucumberOptions(features = {"src/test/resources/features"},
//        glue={"org.example.bdd.stepsdefinition"},
//        tags = {"PacoTeamQA"}
//
//)

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/BasicFlow.feature"},
        glue = {"org.example.StepDefinitions"})

public class RunnerTest {


}
