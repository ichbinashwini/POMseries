package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameWorkException;

public class DriverFactory {
	public WebDriver driver;
	private Properties prop;
	public static String highlightEle;

	static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	private static final Logger log = LogManager.getLogger(DriverFactory.class);

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("url");
		log.info("Browser name " + browserName);

		highlightEle = prop.getProperty("highlight");
		OptionsManager optionsManager = new OptionsManager(prop);

		switch (browserName.trim().toLowerCase()) {

		case "chrome": {
			// driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		}
		case "firefox": {
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			break;
		}

		case "edge": {
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		}

		// ****** For Macbook only *******
		case "safari": {
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		}
		default:
			// System.out.println("Selected driver is " + browserName);
			log.error(AppError.INVALID_BROWSER_MESG + ":" + browserName);
			throw new BrowserException("=========== Invalid browser selected ============");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		log.info("Selected browser is " + browserName);
		getDriver().get(url);

		return getDriver();

	}

	/**
	 * This method is used to get the local copy of driver
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		log.info("Env name =======>" + envName);
		try {
			if (envName == null) {
				log.warn("no env.. is passed, hence running tcs on QA environment...by default..");
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			}

			else {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stag.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				default:
					log.error("Env value is invalid...plz pass the right env value..");
					throw new FrameWorkException("====INVALID ENVIRONMENT====");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * takescreenshot
	 */

	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}

}
