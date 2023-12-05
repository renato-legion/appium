package org.example.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "classpath:features/BasicFlow.feature" },
        glue = { "org.example.StepDefinitions" }
)
public class RunnerTest {}
