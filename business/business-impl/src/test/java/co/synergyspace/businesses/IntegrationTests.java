package co.synergyspace.businesses;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Business Features Integration Tests
 * Created by tarek on 21/09/16.
 */
@Test
@CucumberOptions(plugin = "pretty")
public class IntegrationTests extends AbstractTestNGCucumberTests {
}
