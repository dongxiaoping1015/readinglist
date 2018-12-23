package com.dong.readinglist;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadinglistApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 用随机端口启动
public class ServerWebTests {
    private static FirefoxDriver browser;

    @Value("${local.server.port}") // 注入端口号
    private int port;

    @BeforeClass
    public static void openBrowser() {
        browser = new FirefoxDriver();
        browser.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS); // 配置Firefox驱动, 10秒等候时间
    }

    @AfterClass
    public static void closeBrowser() {
        browser.quit(); // 关闭浏览器
    }
}
