package etsTurPom.Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;

public class BasePage {
    AppiumDriver appiumDriver;
    WebDriverWait wait;
    TouchAction touchAction;
    Actions actions;

    public BasePage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        wait = new WebDriverWait(appiumDriver,60);
    }
    public void waitElement(By by){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public WebElement findElement(By by){
        waitElement(by);
        return appiumDriver.findElement(by);
    }
    public void clickElement(By by){
        findElement(by).click();
    }
    public void clearElement(By by){
        findElement(by).clear();
    }
    public void sendElement(By by,String txt){
        findElement(by).sendKeys(txt);
    }
    public void sendElementKeyboard(String txt){
        actions = new Actions(appiumDriver);
        actions.sendKeys(txt).perform();
    }
    public void scrollScreen(int px,int py,int mx,int my){
        touchAction = new TouchAction<>(appiumDriver)
                .press(PointOption.point(px,py))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(600)))
                .moveTo(PointOption.point(mx,my))
                .release()
                .perform();
    }
    public void startRecordScreen(){
         ((CanRecordScreen)appiumDriver)
                .startRecordingScreen();
    }
    public void stopRecordScreen(){
        String video;
        video = ((CanRecordScreen)appiumDriver)
                .stopRecordingScreen();
        byte[] decodeVideo = Base64.getDecoder().decode(video);
        try {
            Path testVideoDir = Paths.get(System.getProperty("user.dir")+"/video");
            Files.createDirectories(testVideoDir);
            Path testVideoFile = Paths.get(testVideoDir.toString(),String.format("%s-%d.%s","test",System.currentTimeMillis(),"mp4"));
            Files.write(testVideoFile,decodeVideo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
