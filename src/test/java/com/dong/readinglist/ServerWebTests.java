package com.dong.readinglist;

import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadinglistApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 用随机端口启动
public class ServerWebTests {
    private static FirefoxDriver browser;

    @Value("${local.server.port}") // 注入端口号
    private int port;

    @BeforeClass
    public static void openBrowser() throws IOException {
        System.setProperty("webdriver.gecko.driver", "/Applications/Firefox.app/Contents/MacOS/geckodriver");
        browser = new FirefoxDriver();
        browser.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS); // 配置Firefox驱动, 10秒等候时间
    }

    @AfterClass
    public static void closeBrowser() {
        browser.quit(); // 关闭浏览器
    }

    @Test
    public void addBookToEmptyList() {
       String baseUrl = "http://localhost:" + port;
       browser.get(baseUrl);        // 获取主页
       assertEquals("You have no books in your book list", browser.findElementByTagName("div").getText()); // 判断图书列表是否为空

       browser.findElementByName("title").sendKeys("BOOK TITLE");
       browser.findElementByName("author").sendKeys("BOOK AUTHOR");
       browser.findElementByName("isbn").sendKeys("1234567890");
       browser.findElementByName("description").sendKeys("DESCRIPTION");
       browser.findElementByTagName("form").submit();       // 填充并发送表单

        WebElement dl = browser.findElementByCssSelector("dt.bookHeadline");
        assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890", dl.getText());
        WebElement dt = browser.findElementByCssSelector("dd.bookDescription");
        assertEquals("DESCRIPTION", dt.getText()); // 判断列表中是否包含新书
    }
}
