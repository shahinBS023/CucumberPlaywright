package Features;

import BrowserManager.PlayWrightManager;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;

public class LoginFeature {

    Page page = PlayWrightManager.getPage();

    @Given("Goto login")
    public void gotoLogin(){
        page.navigate("https://github.com/");
        System.out.println(page.title());
    }

}
