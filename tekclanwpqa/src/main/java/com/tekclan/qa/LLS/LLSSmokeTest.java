package com.tekclan.qa.LLS;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.tekclan.qa.CPR.*;
import com.tekclan.qa.utils.Directory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class LLSSmokeTest {
	private static Workbook wb;
	public  static void LLS_Smoke_Test(WebDriver driver) throws InterruptedException, IOException
	{
		int urls = 2; 
		int users = 5;
		int datas = 6;
		
		String fileName = Directory.uploadFilePath+"PAF_Inputs.xlsx";
		String sheetName = "LLS";
		System.out.println(fileName);

		File file =    new File(fileName);
		FileInputStream inputStream = new FileInputStream(file);
		wb = new XSSFWorkbook(inputStream);
		Sheet sheet = wb.getSheet(sheetName);

		for(int i= 1 ; i <= urls ; i++){

			String url = sheet.getRow(i).getCell(4).getStringCellValue();

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			WebDriverWait wait = new WebDriverWait(driver,30);

			driver.manage().window().maximize();

			driver.get(url);

			System.out.println("Logged in as - " + url);

			for (int j = 1; j <= users ; j++){

				String usrName = sheet.getRow(j).getCell(1).getStringCellValue();

				wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.USERNAME_FIELD)));
				driver.findElement(By.id(OR.USERNAME_FIELD)).sendKeys(usrName);

				wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PASSWORD_FIELD)));
				driver.findElement(By.id(OR.PASSWORD_FIELD)).sendKeys("rules");

				wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.SUBMIT_BUTTOM)));
				driver.findElement(By.id(OR.SUBMIT_BUTTOM)).click();

				System.out.println("Signed in with -  " + usrName);
				Thread.sleep(2000);

				for (int k=1 ; k<=datas ; k++){

					int patientId = (int) sheet.getRow(k).getCell(3).getNumericCellValue();
					String PID = Integer.toString(patientId);

					System.out.println("Entered patient ID - " + PID);

					Thread.sleep(1000);

					wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.NEW_LINK)));
					driver.findElement(By.id(OR.NEW_LINK)).click();


					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.NEW_PHONE_CALL)));
					driver.findElement(By.xpath(OR.NEW_PHONE_CALL)).click();

					WebElement frame1 = driver.findElement(By.id(OR.FRAME_1));
					driver.switchTo().frame(frame1);

					WebElement frame2 = driver.findElement(By.id(OR.FRAME_2));                
					driver.switchTo().frame(frame2);

					wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_ID_FIELD)));
					driver.findElement(By.id(OR.PATIENT_ID_FIELD)).sendKeys(PID);

					Thread.sleep(4000);
					driver.findElement(By.cssSelector(OR.PATIENT_SEARCH_BUTTON)).click();

					Thread.sleep(4000);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.PATIENT_RECORD +PID+ OR.PATIENT_RECORD_END)));
					driver.findElement(By.xpath(OR.PATIENT_RECORD +PID+ OR.PATIENT_RECORD_END)).click();

					Thread.sleep(2000);
					driver.findElement(By.id(OR.PATIENT_SUBMIT_BUTTON)).click();
					Thread.sleep(1000);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.PATIENT_INFO_DOB)));
					driver.findElement(By.xpath(OR.PATIENT_INFO_DOB)).click();

					wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_INFO_SSN)));
					driver.findElement(By.id(OR.PATIENT_INFO_SSN)).click();

					wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_SUBMIT_BUTTON)));
					driver.findElement(By.id(OR.PATIENT_SUBMIT_BUTTON)).click();

					driver.switchTo().defaultContent();

					WebElement frame5 = driver.findElement(By.id(OR.FRAME_1)); 
					driver.switchTo().frame(frame5);

					WebElement diaction = driver.findElement(By.id(OR.FRAME_2));              
					driver.switchTo().frame(diaction);

					driver.findElement(By.xpath(OR.WRAPUP_INTENT)).click();
					WebElement wp=driver.findElement(By.xpath(OR.WRAPUP_INTENT));

					Actions builder = new Actions(driver);
					builder.doubleClick(wp).build().perform();

					WebElement WrapupFrame = driver.findElement(By.id(OR.WRAPUP_FRAME));               
					driver.switchTo().frame(WrapupFrame);

					WebElement state= driver.findElement(By.id(OR.WRAPUP_STATE_DROPDOWN));
					Select d1=new Select(state);
					d1.selectByIndex(2);

					WebElement Reason= driver.findElement(By.id(OR.WRAPUP_REASON_DROPDOWN));
					Select d2=new Select(Reason);
					d2.selectByIndex(2);

					driver.findElement(By.id(OR.WRAPUP_NOTES)).sendKeys("testing");
					
					Thread.sleep(2000);
					driver.findElement(By.id(OR.PATIENT_SUBMIT_BUTTON)).click();
					
					System.out.println("submitted successfully");
					driver.switchTo().defaultContent();
				}
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.CSR_NAME_LINK)));
				driver.findElement(By.xpath(OR.CSR_NAME_LINK)).click();
				Thread.sleep(1000);

				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.LOGOUT_LINK)));
				driver.findElement(By.xpath(OR.LOGOUT_LINK)).click();

				Thread.sleep(2000);
				
				driver.switchTo().alert().accept();

				Thread.sleep(5000);

				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.LOGIN_LINK)));
				driver.findElement(By.xpath(OR.LOGIN_LINK)).click();
				driver.manage().deleteAllCookies();
			}	
		}
	}
}
