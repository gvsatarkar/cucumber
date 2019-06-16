package commonLibrary;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class CommonLibrary {

	static WebDriver driver;
	boolean status = false;
	static Properties prop;
	static final Logger logger = Logger.getLogger(CommonLibrary.class);

	public static void launchBrowserAndEnterURL() throws Exception {

		prop = raedProprtyFiles();
		String geckoDriverPath = prop.getProperty("geckoDriverExtension");
		String connectURL = prop.getProperty("connectURL");

		try {
			// To Open URL in Firefox browser
			if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
				// To open browser in desktop view
				System.setProperty("webdriver.gecko.driver", geckoDriverPath);
				driver = new FirefoxDriver();
				// driver.get(connectURL);
				driver.get("https://www.gmail.com");
				Thread.sleep(5000);

				// To open browser in Mobile view
				if (prop.getProperty("device").equalsIgnoreCase("Mobile")) {
					driver.manage().window().setSize(new Dimension(400, 800));
					logger.info("Application launched in mobile view and Entered URL successfully");
				}

				// To open browser in Tablet view
				else if (prop.getProperty("device").equalsIgnoreCase("Tablet")) {
					driver.manage().window().setSize(new Dimension(1080, 800));
					logger.info("Application launched in tablet view and Entered URL successfully");
				} else {
					driver.manage().window().maximize();
				}
				takeSnapShot(prop.getProperty("fileWithPath"));
				Thread.sleep(2000);

			}

			// To Open URL in Firefox browser
			else if (prop.getProperty("browser").equalsIgnoreCase("safari")) {
				driver = new SafariDriver();
				driver.get(connectURL);
				Thread.sleep(5000);

				// To open browser in Mobile view
				if (prop.getProperty("device").equalsIgnoreCase("Mobile")) {
					driver.manage().window().setSize(new Dimension(400, 800));
					logger.info("Application launched in mobile view and Entered URL successfully");
				}

				// To open browser in Tablet view
				else if (prop.getProperty("device").equalsIgnoreCase("Tablet")) {
					driver.manage().window().setSize(new Dimension(1080, 800));
					logger.info("Application launched in Tablet view and Entered URL successfully");
				} else {
					driver.manage().window().maximize();
				}
				Thread.sleep(2000);
			}

			// To Open URL in Chrome browser
			else if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\ilook\\Documents\\Cucumber\\Driver\\Chrome_Driver\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.get(connectURL);
				Thread.sleep(5000);

				// To open browser in Mobile view
				if (prop.getProperty("device").equalsIgnoreCase("Mobile")) {
					driver.manage().window().setSize(new Dimension(400, 800));
					logger.info("Application launched in mobile view and Entered URL successfully");
				}

				// To open browser in Tablet view
				else if (prop.getProperty("device").equalsIgnoreCase("Tablet")) {
					driver.manage().window().setSize(new Dimension(1080, 800));
					logger.info("Application launched in Tablet view and Entered URL successfully");
				} else {
					driver.manage().window().maximize();
				}
				Thread.sleep(2000);
			}

		} catch (UnreachableBrowserException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("unable to reach browser");
		}
	}

	public static void enterText(String locator, String valueToBeEntered) throws Exception {
		// boolean status;
		WebElement element = null;
		try {
			element = getWebElement(locator);
			// status = highLight(element);
			// if (status) {
			element.click();
			element.clear();
			element.sendKeys(valueToBeEntered);
			// }
			Thread.sleep(2000);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			logger.info("Unable to find web element: Actual Web element is: " + element + " Expected Web element is :"
					+ locator);
		}
	}

	public static void click(String locator) throws Exception {
		boolean status;
		WebElement element = getWebElement(locator);
		status = highLight(element);
		if (status) {
			element.click();
		}
		Thread.sleep(2000);
	}

	public static void selectByValue(String locator, String value) throws Exception {
		WebElement element = getWebElement(locator);
		Select obj = new Select(element);
		obj.selectByValue(value);
		highLight(element);
		Thread.sleep(2000);
	}

	public static void selectByVisibleText(String locator, String value) throws Exception {
		boolean status;
		WebElement element = getWebElement(locator);
		status = highLight(element);
		if (status) {
			Select obj = new Select(element);
			obj.selectByVisibleText(value);
		}
		Thread.sleep(2000);
	}

	public static void getText(String expected_text, String locator) throws Exception {
		try {
			boolean status;
			String actual_text = null;
			WebElement element = getWebElement(locator);
			status = highLight(element);
			if (status) {
				actual_text = element.getText();
			}
			Thread.sleep(2000);

			Assert.assertTrue("Successfully logged into CRM application: " + "Actual text is : " + actual_text
					+ " Expected text is :" + expected_text, actual_text.equalsIgnoreCase(expected_text));

		} catch (NoSuchElementException e) {
			// TODO: handle exception
			e.printStackTrace();
			;
		} catch (AssertionError e) {
			// TODO: handle exception
			e.printStackTrace();
			;
		}
	}

	public static WebElement getWebElement(String locator) {
		WebElement element = null;

		String[] locatorTypeAndValue = locator.split("-");
		String locatorValue = locatorTypeAndValue[0].trim();
		String locatorType = locatorTypeAndValue[1].trim();

		switch (locatorType.toLowerCase()) {

		case "id":
			element = driver.findElement(By.id(locatorValue));
			break;

		case "name":
			element = driver.findElement(By.name(locatorValue));
			break;

		case "xpath":
			element = driver.findElement(By.xpath(locatorValue));
			break;

		case "cssSelector":
			element = driver.findElement(By.cssSelector(locatorValue));
			break;

		case "className":
			element = driver.findElement(By.className(locatorValue));
			break;

		case "tagName":
			element = driver.findElement(By.tagName(locatorValue));
			break;

		case "partialLinkText":
			element = driver.findElement(By.partialLinkText(locatorValue));
			break;

		default:
			element = null;

			break;

		}
		return element;
	}

	public static boolean highLight(WebElement element) throws Exception {
		boolean status = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// use executeScript() method and pass the arguments
		// Here i pass values based on css style. Yellow background color with
		// solid red
		// color border.
		if (element != null) {
			js.executeScript("arguments[0].style.border='3px solid red'", element);
			status = true;
		}
		// takeSnapShot(prop.getProperty("fileWithPath"));
		Thread.sleep(2000);
		return status;

	}

	public static void verifyExpected(String locator, String expectedValue) throws Exception {
		boolean status;
		String actualValue;
		WebElement element = getWebElement(locator);
		status = highLight(element);
		if (status) {
			actualValue = element.getText();
			if (actualValue.equalsIgnoreCase(expectedValue)) {
				System.out.println("element verified successfully");
			}
		} else {
			System.out.println("Failed to verify expected value");
		}
		Thread.sleep(2000);
	}

	public String readExcel(String bookName, String sheetNmae, String coloumnName)
			throws ClassNotFoundException, SQLException, IOException, BiffException {
		prop = raedProprtyFiles();
		String FilePath = prop.getProperty("testData");
		FileInputStream fs = new FileInputStream(FilePath);
		String value = null;
		Workbook wb = Workbook.getWorkbook(fs);
		Sheet sh = wb.getSheet(sheetNmae);

		int rowCount = sh.getRows();
		int columnCount = sh.getColumns();

		for (int i = 0; i < columnCount; i++) {
			for (int j = 0; j < rowCount; j++) {
				Cell cell = sh.getCell(i, j);
				if (cell.getContents().equalsIgnoreCase(coloumnName)) {
					value = sh.getCell(i, j + 1).getContents();
				}
			}
		}
		return value;
	}

	public static Properties raedProprtyFiles() throws IOException {
		prop = new Properties();
		InputStream input = new FileInputStream(new File("config/config.properties"));
		prop.load(input);
		return prop;
	}

	public static void takeSnapShot(String fileWithPath) throws Exception {

		for (int i = 0; i <= 10; i++) {
			// Take screenshot and store as a file format
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// now copy the screenshot to desired location using copyFile //method
			FileUtils.copyFile(src, new File(fileWithPath + "\\error" + i + ".png"));
			i++;
		}

	}

	public static void closeAllWindows() {

		driver.quit();

	}

	public void closeSingleWindow() {

		driver.close();

	}

}
