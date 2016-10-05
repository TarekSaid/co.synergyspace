package co.synergyspace.posts;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Created by tarek on 26/09/16.
 */
@Test
@CucumberOptions(plugin = "pretty")
public class IntegrationTests extends AbstractTestNGCucumberTests {
}
