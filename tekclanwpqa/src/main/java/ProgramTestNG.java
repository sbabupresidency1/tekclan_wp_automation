import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.internal.ApacheHttpClient;
import org.openqa.selenium.remote.internal.HttpClientFactory;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.tekclan.qa.listeners.ConsoleLoggingListener;
import com.tekclan.qa.util.MailingReport;
import com.tekclan.qa.utils.Directory;
import com.tekclan.qa.writers.CurrentRunPageWriter;

public class ProgramTestNG {
	public static String ExecutionType=null;
	private void testRunner(Map<String, String> testngParams) {

		TestNG testNG = new TestNG();

		XmlSuite suite = new XmlSuite();

		if(ExecutionType.equalsIgnoreCase("sequential") || ExecutionType.isEmpty())
		{
			System.out.println("Sequential Execution started");
			suite.setDataProviderThreadCount(1);
		}
		else if(ExecutionType.equalsIgnoreCase("parallel"))
		{
			System.out.println("Parallel Execution started");
			suite.setDataProviderThreadCount(2);
		}
		suite.setName("Tekclan QA");

		XmlTest test = new XmlTest(suite);
		test.setName("Tekclan");
		test.setParameters(testngParams);

		List<XmlClass> classez = new ArrayList<XmlClass>();
		classez.add(new XmlClass("TestNGClass"));
		test.setXmlClasses(classez);

		List<XmlTest> tests = new ArrayList<XmlTest>();
		tests.add(test);
		suite.setTests(tests);

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		testNG.setXmlSuites(suites);
		testNG.run();
	}


	public static void main(String args[]) throws Exception {
		
		ProgramTestNG program = new ProgramTestNG();
		String userDirectory = System.getProperty("user.dir");
		System.setProperty("log.file.location", userDirectory);
		Map<String,String> params = new HashMap<String,String>();
		ExecutionType=System.getProperty("tekclan.execution.parseq").trim();
		program.testRunner(params);
		try { MailingReport.MailZipped() ;} catch (Exception e) {e.printStackTrace();}
		try { MailingReport.SendMail()   ;} catch (Exception e) {e.printStackTrace();}
		if(Directory.reexecution){
			if(CurrentRunPageWriter.FailedTestCasesCount>0)	{
				CurrentRunPageWriter.writeFailedTestCases();
				ExecutionType=System.getProperty("tekclan.execution.parseq").trim();
				program.testRunner(params);
				try { MailingReport.MailZipped() ;} catch (Exception e) {e.printStackTrace();}
				try { MailingReport.SendMail()   ;} catch (Exception e) {e.printStackTrace();}
			}
			else {
				System.out.println("===============================================");
				System.out.println("No Failure Test Case(s) 			   ");
				System.out.println("===============================================");
			}
		}	
	}	
}
