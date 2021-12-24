package generic;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IAutoConstants {

	public WebDriver driver;
	public WebDriverWait wait;
	public SoftAssert softAssert;

	static {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.edgedriver().setup();
	}

	@Parameters({ "useGrid", "hubURL", "browser" })
	@BeforeMethod
	public void openApp(@Optional("no")String useGrid, @Optional("")String hubURL, @Optional("chrome")String browser) {

		if (useGrid.equalsIgnoreCase("yes")) {
			URL whichSystem;
			try {
				whichSystem = new URL(hubURL);
				DesiredCapabilities capability = new DesiredCapabilities();
				capability.setBrowserName(browser);
				driver = new RemoteWebDriver(whichSystem, capability);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else {
			if (browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} 
			else if (browser.equalsIgnoreCase("firefox"))
			{
				driver = new FirefoxDriver();
			} 
			else
			{

				driver = new EdgeDriver();
			}
		}

		driver.manage().window().maximize();
		
		String AppUrl = Property.getProperty(PROP_PATH, "URL");
		driver.get(AppUrl);
		Reporter.log("Application URL : " + AppUrl, true);

		String impWaitTimeOut = Property.getProperty(PROP_PATH, "IMPTO");
		long iwto = Long.parseLong(impWaitTimeOut);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(iwto));
		Reporter.log("Implicit Timoeout : " + impWaitTimeOut, true);

		String expWaitTimeOut = Property.getProperty(PROP_PATH, "EXPTO");
		long ewto = Long.parseLong(expWaitTimeOut);
		wait = new WebDriverWait(driver, Duration.ofSeconds(ewto));
		Reporter.log("Webdriver/Explicit Timeout : " + expWaitTimeOut, true);

		softAssert = new SoftAssert();
	}

	@AfterMethod
	public void closeApp() {
		Reporter.log("Closing the browser", true);
		driver.quit();
		
	}
}
