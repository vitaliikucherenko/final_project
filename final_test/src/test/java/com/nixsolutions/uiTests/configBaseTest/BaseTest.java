package com.nixsolutions.uiTests.configBaseTest;

import com.nixsolutions.app.Browser;

import com.nixsolutions.uiTests.metodsViewResult.TakeScreenshot;

import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    Browser browser = new Browser();

    @BeforeMethod(groups = "authentication")
    public void start() {
        browser.getBrowserType();
        open("https://rozetka.com.ua/");
    }

    @AfterMethod(groups = "authentication")
    public void end(ITestResult result) {
        if (!result.isSuccess()) {
            TakeScreenshot takeScreenshot = new TakeScreenshot();
            try {
                takeScreenshot.takeScreen();
                System.out.println("AfterMethod - Screenshot of the test is taken");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("AfterMethod - Screenshot of the test is not taken");
        }
        closeWebDriver();
    }

    @BeforeGroups(groups = "authentication")
    public void before() {
        System.out.println("BeforeGroups - authentication test starting");
    }

    @AfterGroups(groups = "authentication")
    public void after() {
        System.out.println("AfterGroups - authentication test finished");
    }
}
