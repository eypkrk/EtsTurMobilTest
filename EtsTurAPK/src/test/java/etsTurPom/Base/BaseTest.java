package etsTurPom.Base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URL;
import java.time.Duration;

public class BaseTest {
    AndroidDriver androidDriver = null;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        try{
            DesiredCapabilities cap;
            cap = new DesiredCapabilities();
            cap.setCapability("deviceName","Pixel 4 API 29");
            cap.setCapability("platformName","Android");
            cap.setCapability("udid","emulator-5554");
            cap.setCapability("platformVersion","10.0");
            cap.setCapability("appPackage","com.etstur");
            cap.setCapability("appActivity","com.etstur.modules.common.main.MainActivity");
            cap.setCapability("skipUnlock","true");
            cap.setCapability("noReset","false");

            androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
            wait = new WebDriverWait(androidDriver, 60);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public AndroidDriver getAndroidDriver(){
        return androidDriver;
    }
    public void setAndroidDriver(AndroidDriver androidDriver){
        this.androidDriver = androidDriver;
    }
    @AfterClass
    public void teardown(){
        androidDriver.quit();
    }
}
