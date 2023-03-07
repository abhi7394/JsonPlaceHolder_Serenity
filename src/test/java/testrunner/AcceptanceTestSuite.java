package testrunner;

import base.BaseTest;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/main/resources/", glue="steps")
public class AcceptanceTestSuite extends BaseTest {}
