package com.nixsolutions.uiTests.metodsViewResult;
import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import com.nixsolutions.uiTests.configBaseTest.BaseTest;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;


public class TakeScreenshot extends BaseTest {

    @Attachment(type = "image/png")
    public byte[] takeScreen() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        FileUtils.copyFile(screenshot, new File("target\\allure-results\\screenshots\\" +screenshot.getName()));
        return Files.toByteArray(screenshot);
    }
}
