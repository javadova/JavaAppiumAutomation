package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String WEBDRIVER_ANDROID = "AndroidDriver";
    private static final String WEBDRIVER_IOS = "IOSDriver";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesBuPlatformEnv();

        driver =  getWebDriver(capabilities);
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(Duration.ofSeconds((seconds)));
    }

    private DesiredCapabilities getCapabilitiesBuPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/elvira/JavaAppiumAutomation/apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)) {
        capabilities.setCapability("platformName", "tests/iOS");
        capabilities.setCapability("deviceName", "iPhone 11");
        capabilities.setCapability("platformVersion", "13.4");
        capabilities.setCapability("app", "/Users/elvira/JavaAppiumAutomation/apks/Wikipedia.app");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }

        return capabilities;
    }

    private AppiumDriver getWebDriver(DesiredCapabilities capabilitiesDriver) throws Exception{
        String driverEnv = System.getenv("WEBDRIVER");

        if(driverEnv.equals(WEBDRIVER_ANDROID)){
            driver = new AndroidDriver(new URL(AppiumURL), capabilitiesDriver);
        }else if(driverEnv.equals(WEBDRIVER_IOS)){
            driver = new IOSDriver(new URL(AppiumURL), capabilitiesDriver);
        }else{
            throw new Exception("Cannot get run webdriver from env variable. Webdriver value " + driver);
        }
        return driver;
    }
}
