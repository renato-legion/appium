package org.example.runner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.thucydides.core.annotations.Managed;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.thucydides.core.annotations.ClearCookiesPolicy.BeforeEachTest;



@CucumberOptions(features = {"src/test/resources/features"},
        glue={"org.example.bdd.stepsdefinition"},
        tags = {"PacoTeamQA"}

)
public class RunnerTest {


}
