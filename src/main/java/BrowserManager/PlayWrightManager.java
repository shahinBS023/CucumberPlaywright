package BrowserManager;

import com.microsoft.playwright.*;

import java.util.Properties;

public class PlayWrightManager {

//    Initialize variables for Playwright, Browser, BrowserContext and Page
    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

    /**
     * Initialize pages
     * @param prop
     * @return
     */

    public Page initBrowser(Properties prop) {

        String browserName = prop.getProperty("browser").trim();
        System.out.println("browser name is : " + browserName);

        // playwright = Playwright.create();
        tlPlaywright.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            case "chromium":
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;

            default:
                System.out.println("please pass the right browser name......");
                break;
        }

        tlBrowserContext.set(getBrowser().newContext());
        tlPage.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url").trim());
        return getPage();

    }

    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    public static Browser getBrowser() {
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }


}
