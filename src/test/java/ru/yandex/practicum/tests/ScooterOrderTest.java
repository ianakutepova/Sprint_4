package ru.yandex.practicum.tests;

import org.junit.Test;
import org.openqa.selenium.By;
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
import ru.yandex.practicum.utils.DataGenerator;


@RunWith(Parameterized.class)
public class ScooterOrderTest extends BaseTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String days;
    private final String color;
    private final String comment;
    private final boolean isUpperButton; // Параметр для указания кнопки

    public ScooterOrderTest(String name, String surname, String address, String metroStation, String phoneNumber, String date, String days, String color, String comment, boolean isUpperButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.days = days;
        this.color = color;
        this.comment = comment;
        this.isUpperButton = isUpperButton;
    }

    @Parameterized.Parameters
    public static Object[][] provideTestData() {
        DataGenerator dataGenerator = new DataGenerator();
        return new Object[][]{
                {dataGenerator.generateName(), dataGenerator.generateSurname(), dataGenerator.generateAddress(), dataGenerator.generateMetroStation(), dataGenerator.generatePhoneNumber(), dataGenerator.generateDate(), dataGenerator.generateDays(), dataGenerator.generateColor(), dataGenerator.generateComment(), true},
                {dataGenerator.generateName(), dataGenerator.generateSurname(), dataGenerator.generateAddress(), dataGenerator.generateMetroStation(), dataGenerator.generatePhoneNumber(), dataGenerator.generateDate(), dataGenerator.generateDays(), dataGenerator.generateColor(), dataGenerator.generateComment(), false}
        };
    }


    @Test
    public void scooterOrderTest() throws ParseException {

        MainPage mainPage = new MainPage(driver);

        mainPage.openPage();
        mainPage.acceptCookie();

        if (isUpperButton) {
            mainPage.clickUpperOrderButton();
        } else {
            mainPage.scrollToLowerOrderButton();
            mainPage.clickLowerOrderButton();
        }

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


