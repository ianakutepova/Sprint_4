package ru.yandex.practicum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.practicum.pages.ConfirmModalWindowPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.OrderFormPage;
import ru.yandex.practicum.pages.RentDetailsFormPage;

public class BaseTest {

    WebDriver driver;
    MainPage mainPage;
    OrderFormPage orderFormPage;
    RentDetailsFormPage rentDetailsFormPage;
    ConfirmModalWindowPage confirmModalWindowPage;

    @Before
    public void startUp(){
        String browser = System.getProperty("browser", "chrome"); //"chrome" - значение по умолчанию
        if (browser.equals("chrome")) {
            startBrowserChrome();
        } else if(browser.equals("firefox")) {
            startBrowserFirefox();
        }

        mainPage = new MainPage(driver);
        orderFormPage = new OrderFormPage(driver);
        rentDetailsFormPage = new RentDetailsFormPage(driver);
        confirmModalWindowPage = new ConfirmModalWindowPage(driver);
    }

    private void startBrowserChrome() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
    }

    private void startBrowserFirefox() {
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
    }


    @After
    public void tearDown (){
        driver.quit();
    }
}
