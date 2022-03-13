package StepDefs;

import org.testng.Assert;

import AndroidPageObjects.Calculator;
import BaseClasses.DataInjection;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepsAndroidCalculator{
	
	DataInjection di;
	Calculator calc;
	@Before (order = 2)
	public void before(Scenario sc)
	{
		di = new DataInjection(sc);
		di.initBrowser();
	}
	/*@Before("@FirstScenario")
	public void setExtent()
	{
		em = new ExtentManager();
	}
	
	@After("@LastScenario")
	public void flushExtent()
	{
		b.terminateextent();
	}*/
	
	@After
	public void after()
	{
		di.quit();
	}
	
	@Given("^I want to open the \"([^\"]*)\" in the \"([^\"]*)\" android device$")
	public void openAndroidDriver(String appname, String deviceid)
	{
		Assert.assertTrue(di.initDriver(deviceid+"_"+appname+".json"));
	}
	
	@Given("^I want to test the testcase for \"([^\"]*)\"$")
	public void setKeyW(String keyword)
	{
		di.setKeyWord(keyword);
		di.setMap();
		//b.startextent(b.getData("TestCaseNo"), b.getData("TestCaseDesc"));
	}

	@And("^I want the calculator to be opened in device$")
	public void waitForCalculator()
	{
		calc = new Calculator(di);
		Assert.assertTrue(calc.validateCalc());
	}
	
	@And("^I tap number key \"([^\"]*)\" on keypad$")
	public void tapNumKey(String key)
	{
		Assert.assertTrue(calc.validateClickKeyNum(di.getData(key)));
	}
	
	@And("^I tap operator key \"([^\"]*)\" on keypad$")
	public void tapOpKey(String key)
	{
		Assert.assertTrue(calc.validateClickKeyOp(di.getData(key)));
	}
	
	@Then("^I validate the result of \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void validateResult(String key1, String op, String key2)
	{
		Assert.assertTrue(calc.validateCalcResult(di.getData(key1), di.getData(op), di.getData(key2)));
	}

}
