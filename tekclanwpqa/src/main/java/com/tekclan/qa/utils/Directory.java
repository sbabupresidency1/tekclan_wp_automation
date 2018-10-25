package com.tekclan.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import com.tekclan.qa.TekclanReports;
import com.tekclan.qa.enums.ReportLabels;
import com.tekclan.qa.exceptions.TekclanReporterException;
import com.tekclan.qa.writers.HTMLDesignFilesWriter;
/**
 * Configurations
 * @author Babu 
 *
 */
public class Directory {

	static Logger log = Logger.getLogger(Directory.class.getName());
	public static final String CURRENTDir = System.getProperty("user.dir");
	public static final String SEP = System.getProperty("file.separator");
	public static String REPORTSDIRName = "Tekclan Reports";
	public static String REPORTSDir = CURRENTDir + SEP + REPORTSDIRName;
	public static String RESULTSDir = REPORTSDir + SEP + "Results";
	public static String HTMLDESIGNDIRName = "HTML_Design_Files";
	public static String HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
	public static String CSSDIRName = "CSS";
	public static String CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
	public static String IMGDIRName = "IMG";
	public static String IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
	public static String JSDIRName = "JS";
	public static String JSDir = HTMLDESIGNDir + SEP + JSDIRName;
	public static String RUNName = "Test Execution_1"; // Babu
	public static String RUNDir = RESULTSDir + SEP + RUNName;
	public static String SETTINGSFile = RESULTSDir + SEP + "Settings.properties";
	public static final char JS_SETTINGS_DELIM = ';';
	public static final String REPO_DELIM = "##@##@##";
	public static final char JS_FILE_DELIM = ',';
	public static final String MENU_LINK_NAME = "Run ";
	public static final String SCREENSHOT_TYPE = "PNG";
	public static final String SCREENSHOT_EXTENSION = ".PNG";
	private static String logo = null;
	public static String SCREENSHOT_DIRName = "img";
	public static boolean generateConfigReports = true;
	public static boolean generateExcelReports = false;
	public static boolean takeScreenshot = false;
	public static boolean continueExecutionAfterStepFailed = false;
	public static boolean recordExecutionAvailable = false;
	public static boolean recordSuiteExecution = false;
	public static boolean recordTestMethodExecution = false;
	public static boolean reexecution=true;
	public static final String MAIN_RECORD_FILE_NAME = "ATU_CompleteSuiteRecording";
	public static String userName=null;
	public static String password=null;
	public static String smtpHost=null;
	public static String fromAddress=null;
	public static String toAddress=null;
	public static String ccAddress=null;
	//public static String bccAddress=null;
	public static String testCasePath=null;
	public static String ORSheetPath=null;
	public static String browser=null;
	public static String Subject=null;
	public static String GRID_IP=null;
	public static String Reports_Path=null;
	public static String Zipfolder_Path=null;
	public static String WIDGET_HTML_FILE=null;
	public static String WaitFor=null;	
	public static String Coachesurl=null;
	public static String OpsAdminurl=null;	
	public static String Guerrillamailurl=null;
	public static String Oracle_Port=null;
	public static String Oracle_Databasename=null;
	public static String Oracle_User=null;
	public static String Oracle_Pass =null;
	public static String Oracle_Hostname=null;
	public static String Sql_Hostname=null;
	public static String Sql_Databasename=null;
	public static String Sql_User=null;
	public static String Sql_Pass=null;
	public static String Sql_Port=null;	
	public static String uploadFilePath=null;
	public static String cpr_qa_url=null;
	public static String cpr_qa_navigate_url=null;
	public static String cpr_qa_username=null;
	public static String cpr_qa_password=null;
	public static String lls_qa_url=null;
	public static String lls_qa_navigate_url=null;
	public static String lls_qa_username=null;
	public static String lls_qa_password=null;
	public static String faf_qa_url=null;
	public static String faf_qa_navigate_url=null;
	public static String faf_qa_username=null;
	public static String faf_qa_password=null;


	/**
	 * Retrieve values from custom properties
	 * @throws TekclanReporterException
	 * @throws Exception 
	 */
	public static void init() throws TekclanReporterException, Exception {
		if (getCustomProperties() != null) {
			log.info("Reading from custom properties");
			Properties localProperties = new Properties();

			try {

				localProperties.load(new FileReader(getCustomProperties()));
				String reportsDir = localProperties.getProperty("tekclan.reports.dir")			.trim();
				String headerText = localProperties.getProperty(			"tekclan.proj.header.text").trim();	
				String projectDescription = localProperties.getProperty(			"tekclan.proj.description").trim();
				String takeScreenShot = localProperties.getProperty(			"tekclan.reports.takescreenshot").trim();
				String configReports = localProperties.getProperty(			"tekclan.reports.configurationreports").trim();
				String excelReport = localProperties.getProperty("tekclan.reports.excel")			.trim();
				String contExectution = localProperties.getProperty(			"tekclan.reports.continueExecutionAfterStepFailed").trim();
				String reExecution = localProperties.getProperty(   "tekclan.failures.reexecution").trim();

				userName = localProperties.getProperty(			"tekclan.mail.username").trim();
				password = localProperties.getProperty(			"tekclan.mail.password").trim();
				smtpHost = localProperties.getProperty(			"tekclan.mail.smtp.host").trim();
				fromAddress = localProperties.getProperty(			"tekclan.mail.from.address").trim();
				toAddress = localProperties.getProperty(			"tekclan.mail.to.address").trim();
				ccAddress = localProperties.getProperty(				"tekclan.mail.cc.address").trim();	
				Subject = localProperties.getProperty(				"tekclan.mail.subject").trim();
				Reports_Path = localProperties.getProperty(				"tekclan.reports.dir").trim();
				Zipfolder_Path = localProperties.getProperty(				"tekclan.mail.zipfolder").trim();
				testCasePath = localProperties.getProperty(			"tekclan.proj.testcasePath").trim();
				String ORSheet = localProperties.getProperty(			"tekclan.proj.ORSheet.path").trim();
				ORSheetPath=testCasePath+"/"+ORSheet;
				browser = localProperties.getProperty(			"tekclan.browser.name").trim().toLowerCase();

				WaitFor = localProperties.getProperty(			"tekclan.proj.waitSec").trim().toLowerCase();				
				//Properties urlsProperties = new Properties();
				//System.out.println("testCasePath: "+testCasePath);								
				String uploadPath=localProperties.getProperty(   "tekclan.proj.uploadfile").trim();
				uploadFilePath=testCasePath+"/"+uploadPath+"/";
				String logo1 = localProperties.getProperty("tekclan.proj.header.logo")			.trim();
				logo=uploadFilePath+logo1;
				GRID_IP = localProperties.getProperty(			"tekclan.Grid").trim().toLowerCase();

				//URLs and Credentials for PAF  - CPR
				Properties urlsProperties = new Properties();
				System.out.println("testCasePath: "+testCasePath);
				InputStream input = new FileInputStream(testCasePath+"/"+"urls.properties");
				urlsProperties.load(input);	
			
				//CPR
				cpr_qa_url=urlsProperties.getProperty("cpr_qa_url".trim());
				cpr_qa_navigate_url=urlsProperties.getProperty("cpr_qa_navigate_url".trim());
				cpr_qa_username=urlsProperties.getProperty("cpr_qa_username".trim());
				cpr_qa_password=urlsProperties.getProperty("cpr_qa_password".trim());
				
				//LLS:
				lls_qa_url=urlsProperties.getProperty("lls_qa_url".trim());
				lls_qa_navigate_url=urlsProperties.getProperty("lls_qa_navigate_url".trim());
				lls_qa_username=urlsProperties.getProperty("lls_qa_username".trim());
				lls_qa_password=urlsProperties.getProperty("lls_qa_password".trim());
				
				//FAF:
				faf_qa_url=urlsProperties.getProperty("faf_qa_url".trim());
				faf_qa_navigate_url=urlsProperties.getProperty("faf_qa_navigate_url".trim());
				faf_qa_username=urlsProperties.getProperty("faf_qa_username".trim());
				faf_qa_password=urlsProperties.getProperty("faf_qa_password".trim());
				
				try {
					if ((headerText != null) && (headerText.length() > 0)) {
						ReportLabels.HEADER_TEXT.setLabel(headerText);
					}
					if ((reExecution != null) && (reExecution.length() > 0)) {
						try {
							reexecution = Boolean.parseBoolean(reExecution);
						} catch (Exception localException1) {
						}
					}
					if ((takeScreenShot != null) && (takeScreenShot.length() > 0)) {
						try {
							takeScreenshot = Boolean.parseBoolean(takeScreenShot);
						} catch (Exception localException1) {
						}
					}
					if ((configReports != null) && (configReports.length() > 0)) {
						try {
							generateConfigReports = Boolean.parseBoolean(configReports);
						} catch (Exception localException2) {
						}
					}
					if ((excelReport != null) && (excelReport.length() > 0)) {
						try {
							generateExcelReports = Boolean.parseBoolean(excelReport);
						} catch (Exception localException3) {
						}
					}
					if ((contExectution != null) && (contExectution.length() > 0)) {
						try {
							continueExecutionAfterStepFailed = Boolean
									.parseBoolean(contExectution);
						} catch (Exception localException4) {
						}
					}
					/*if ((recordExecution != null) && (recordExecution.length() > 0)) {
			try {
			    RecordingFor localRecordingFor = RecordingFor
				    .valueOf(recordExecution.toUpperCase());
			    if (localRecordingFor == RecordingFor.SUITE) {
				recordSuiteExecution = true;
			    } else if (localRecordingFor == RecordingFor.TESTMETHOD) {
				recordTestMethodExecution = true;
			    }
			} catch (Throwable localThrowable) {
			}
		    }
					File file = new File(reportsDir);
					if(file.exists())
					{
						FileUtils.cleanDirectory(file); //clean out directory (this is optional -- but good know)
			            FileUtils.forceDelete(file);
						System.out.println("Report directory deleted");
					}*/
					File outputFile = new File(Directory.Zipfolder_Path);
					if(!outputFile.exists()){
						outputFile.mkdir();
					}


					if ((projectDescription != null) && (projectDescription.length() > 0)) {
						TekclanReports.indexPageDescription = projectDescription;
					}
					if ((reportsDir != null) && (reportsDir.length() > 0)) {
						REPORTSDir = reportsDir;
						REPORTSDIRName = new File(REPORTSDir).getName();
						RESULTSDir = REPORTSDir + SEP + "Results";
						HTMLDESIGNDIRName = "HTML_Design_Files";
						HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
						CSSDIRName = "CSS";
						CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
						IMGDIRName = "IMG";
						IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
						JSDIRName = "JS";
						JSDir = HTMLDESIGNDir + SEP + JSDIRName;
						RUNName = "Run_";
						RUNDir = RESULTSDir + SEP + RUNName;
						SETTINGSFile = RESULTSDir + SEP + "Settings.properties";
					}
				} catch (Exception localException5) {
					throw new TekclanReporterException(localException5.toString());
				}
			} catch (FileNotFoundException localFileNotFoundException) {
				throw new TekclanReporterException(
						"The Path for the Custom Properties file could not be found", localFileNotFoundException);
			} catch (IOException localIOException) {
				throw new TekclanReporterException(
						"Problem Occured while reading the Tekclan Reporter Config File");
			}
		}
	}

	public static void mkDirs(String paramString) {
		File localFile = new File(paramString);
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
	}

	public static boolean exists(String paramString) {
		File localFile = new File(paramString);
		return localFile.exists();
	}
	/**
	 * Verifying required files for report generation
	 * @throws Exception 
	 */
	public static void verifyRequiredFiles() throws Exception {
		init();
		log.info("Setting Reports Dir---"+REPORTSDir);
		log.info("Setting Results Dir---"+RESULTSDir);
		mkDirs(REPORTSDir);
		if (!exists(RESULTSDir)) {
			mkDirs(RESULTSDir);
			SettingsFile.initSettingsFile();
		}
		if (!exists(HTMLDESIGNDir)) {
			mkDirs(HTMLDESIGNDir);
			mkDirs(CSSDir);
			mkDirs(JSDir);
			mkDirs(IMGDir);
			HTMLDesignFilesWriter.writeCSS();
			HTMLDesignFilesWriter.writeIMG();
			HTMLDesignFilesWriter.writeJS();
		}
		if ((logo != null) && (logo.length() > 0)) {
			String str = new File(logo).getName();
			if (!new File(IMGDir + SEP + str).exists()) {
				copyImage(logo);
			}
			ReportLabels.PROJ_LOGO.setLabel(str);
		}
	}

	private static void copyImage(String paramString) throws TekclanReporterException {
		File localFile = new File(paramString);
		if (!localFile.exists()) {
			return;
		}
		FileImageInputStream localFileImageInputStream = null;
		FileImageOutputStream localFileImageOutputStream = null;
		try {
			localFileImageInputStream = new FileImageInputStream(new File(
					paramString));
			localFileImageOutputStream = new FileImageOutputStream(new File(
					IMGDir + SEP + localFile.getName()));
			int i = 0;
			while ((i = localFileImageInputStream.read()) >= 0) {
				localFileImageOutputStream.write(i);
			}
			localFileImageOutputStream.close();
			return;
		} catch (Exception localException2) {
		} finally {
			try {
				localFileImageInputStream.close();
				localFileImageOutputStream.close();
				localFile = null;
			} catch (Exception localException4) {
				localFileImageInputStream = null;
				localFileImageOutputStream = null;
				localFile = null;
			}
		}
	}

	public static String getCustomProperties() {
		return System.getProperty("tekclan.reporter.config");
	}

	public static String createTestRunDateTime() {
		Calendar cal = Calendar.getInstance();
		return DateFormatUtils.format(cal, "dd-MM-yy, hh.mm aa");
	}

	public static String getTestRunDateTime(int run) {
		try {
			String testRun = SettingsFile.get("testRunDT");
			String timeArray [] = testRun.split(";");
			return timeArray[run-1];
		} catch (TekclanReporterException e) {
			e.printStackTrace();
		}
		return " ";
	}
}