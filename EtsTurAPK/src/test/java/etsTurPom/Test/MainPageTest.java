package etsTurPom.Test;

import etsTurPom.Base.BaseTest;
import etsTurPom.Page.MainPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainPageTest extends BaseTest {
    MainPage mainPage;

    @BeforeClass
    public void mainTest(){
        mainPage = new MainPage(getAndroidDriver());
    }
    @Test
    public void test(){
        mainPage.mainPage()
                .searchOtels()
                .infoPassenger()    
                .stepPay()
                .otherPay();
    }
    @AfterClass
    public void after(){
        teardown();
    }
}
