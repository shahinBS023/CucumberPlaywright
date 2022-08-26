package AppHook;

import BrowserManager.PlayWrightManager;
import Utility.ConfigReader;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.util.Properties;

public class AppHook {

    Properties prop;
    public ConfigReader configReader;
//    Playwright playwright;
//    Browser browser;
//    BrowserContext browserContext;
    PlayWrightManager playWrightManager;
    Page page;

    @Before(order = 0)
    public void getProperties(){
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @Before(order = 1)
    public void launchBrowser(){
        String browserName = prop.getProperty("browser");
        playWrightManager = new PlayWrightManager();
        page = playWrightManager.initBrowser(prop);
    }


    @After
    public void teatDown(){
        page.context().browser().close();
    }


}
