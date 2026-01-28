package ru.yandex.practicum.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.constants.*;
import ru.yandex.practicum.pages.*;
import java.text.ParseException;
import java.time.Duration;
import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ScooterOrderTest extends BaseTest implements OrderConstants {

    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String days;
    private final String color;
    private final String comment;

    public ScooterOrderTest(String name, String surname, String address, String metroStation, String phoneNumber, String date, String days, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.days = days;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] provideTestData() {
        return new Object[][]{
                {NAME_1, SURNAME_1, ADDRESS_1, METRO_STATION_1, PHONE_NUMBER_1, DATE_1, ONE_DAY, BLACK, COMMENT_1},
                {NAME_2, SURNAME_2, ADDRESS_2, METRO_STATION_2, PHONE_NUMBER_2, DATE_2, TWO_DAYS, GREY, COMMENT_2},
                {NAME_3, SURNAME_3, ADDRESS_3, METRO_STATION_3, PHONE_NUMBER_3, DATE_3, FOUR_DAYS, BLACK, COMMENT_3},
                {NAME_4, SURNAME_4, ADDRESS_4, METRO_STATION_4, PHONE_NUMBER_4, DATE_4, SEVEN_DAYS, GREY, COMMENT_4}
        };
    }

    @Test
    public void upperButtonScooterOrderTest() throws ParseException {

        MainPage mainPage = new MainPage(driver);

        mainPage.openPage();
        mainPage.acceptCookie();
        mainPage.clickUpperOrderButton();

        OrderFormPage orderFormPage = new OrderFormPage(driver);
        orderFormPage.setOrderFormCompletely(name, surname, address, metroStation, phoneNumber);
        orderFormPage.clickFurtherButton();

        RentDetailsFormPage rentDetailsFormPage = new RentDetailsFormPage(driver);
        rentDetailsFormPage.setRentDetailsFormCompletely(date, days, color, comment);
        rentDetailsFormPage.clickOrderButton();

        ConfirmModalWindowPage confirmModalWindowPage = new ConfirmModalWindowPage(driver);
        confirmModalWindowPage.clickYesButton();

        checkSuccessfullyIssuedOrderWindow();
    }

    @Test
    public void lowerButtonScooterOrderTest() throws ParseException {

        MainPage mainPage = new MainPage(driver);

        mainPage.openPage();
        mainPage.acceptCookie();
        mainPage.scrollToLowerOrderButton();
        mainPage.clickLowerOrderButton();

        OrderFormPage orderFormPage = new OrderFormPage(driver);
        orderFormPage.setOrderFormCompletely(name, surname, address, metroStation, phoneNumber);
        orderFormPage.clickFurtherButton();

        RentDetailsFormPage rentDetailsFormPage = new RentDetailsFormPage(driver);
        rentDetailsFormPage.setRentDetailsFormCompletely(date, days, color, comment);
        rentDetailsFormPage.clickOrderButton();

        ConfirmModalWindowPage confirmModalWindowPage = new ConfirmModalWindowPage(driver);
        confirmModalWindowPage.clickYesButton();

        checkSuccessfullyIssuedOrderWindow();
    }


    public void checkSuccessfullyIssuedOrderWindow() {
    By orderIssuedModalWindow = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderIssuedModalWindow));

        WebElement expectedElement = driver.findElement(orderIssuedModalWindow);
        assertTrue("Всплывающее окно с сообщением об успешном создании заказа не отображается", expectedElement.isDisplayed());
        }
    }


