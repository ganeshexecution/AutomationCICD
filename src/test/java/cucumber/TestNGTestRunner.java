package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//Starting with cucumberoptions provided  feature file path glued with stepdef path
//Monochrome true for cucumber to give output in readable format and plugin to generate cucumber report in the path given
@CucumberOptions(features="src/test/java/cucumber" ,glue="seleniumFrameworkDesign.StepDefinitions",
monochrome=true ,tags= "@Regression" ,plugin= {"html:target/cucumber.html"} )
public class TestNGTestRunner extends AbstractTestNGCucumberTests
{

}
