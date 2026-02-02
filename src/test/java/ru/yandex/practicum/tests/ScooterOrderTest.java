package ru.yandex.practicum.tests;

import org.junit.Test;
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

    private final boolean isUpperButton;

    public ScooterOrderTest(boolean isUpperButton) {
        this.isUpperButton = isUpperButton;
    }

    @Parameterized.Parameters
    public static Object[][] provideTestData() {
        return new Object[][]{
                {true},
                {false}
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

        DataGenerator dataGenerator = new DataGenerator();

        String name = dataGenerator.generateName();
        String surname = dataGenerator.generateSurname();
        String address = dataGenerator.generateAddress();
        String metroStation = dataGenerator.generateMetroStation();
        String phoneNumber = dataGenerator.generatePhoneNumber();
        String date = dataGenerator.generateDate();
        String days = dataGenerator.generateDays();
        String color = dataGenerator.generateColor();
        String comment = dataGenerator.generateComment();

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
        ConfirmModalWindowPage confirmModalWindowPage = new ConfirmModalWindowPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(confirmModalWindowPage.orderIssuedModalWindow));

        WebElement expectedElement = driver.findElement(confirmModalWindowPage.orderIssuedModalWindow);
        assertTrue("Всплывающее окно с сообщением об успешном создании заказа не отображается", expectedElement.isDisplayed());
        }
    }


