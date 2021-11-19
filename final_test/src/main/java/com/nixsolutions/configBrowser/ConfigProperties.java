package com.nixsolutions.configBrowser;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;


public class ConfigProperties implements BrowserDao {
    private final ChromeOptions chromeOptions = new ChromeOptions();
    private final EdgeOptions edgeOptions = new EdgeOptions();

    @Override
    public void createChrome(BrowserDao browserDao) {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        clearBrowserCookies();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        chromeOptions.merge(capabilities);
        Configuration.browserCapabilities = capabilities;
    }

    @Override
    public void createEdge(BrowserDao browserDao) {
        clearBrowserCookies();
        edgeOptions.setCapability("ms:inPrivate", true);
        Configuration.startMaximized = true;
    }
}
