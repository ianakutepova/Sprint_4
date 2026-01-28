package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class OrderFormPage {

    private WebDriver driver;


    // Поле для ввода Имени
    private final By nameField = By.xpath(".//input[@placeholder ='* Имя']");
    // Поле для ввода Фамилии
    private final By surnameField = By.xpath(".//input[@placeholder ='* Фамилия']");
    // Поля для ввода Адреса доставки заказа
    private final By addressField = By.xpath(".//input[@placeholder ='* Адрес: куда привезти заказ']");
    // Поле для ввода Станции метро
    private final By metroStationField = By.xpath(".//input[@placeholder ='* Станция метро']");
    // Поле для ввода номера телефона
    private final By phoneNumberField = By.xpath(".//input[@placeholder ='* Телефон: на него позвонит курьер']");

    // Кнопка "Далее"
    private final By furtherButton = By.xpath(".//button[contains(text(), 'Далее')]");

    public OrderFormPage(WebDriver driver) {
        this.driver = driver;
    }


    //Метод заполнения поля "Имя"
    public OrderFormPage setNameField(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        return this;
    }


    // Метод заполнения поля "Фамилия"
    public OrderFormPage setSurnameField(String surname) {
        driver.findElement(surnameField).clear();
        driver.findElement(surnameField).sendKeys(surname);
        return this;
    }


    // Метод заполнения поля "Адрес: куда привезти заказ"
    public OrderFormPage setAddressField(String address) {
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
        return this;
    }


    //Метод заполнения поля "Станция метро"
    public OrderFormPage setMetroStationField(String metroStation) {
        driver.findElement(metroStationField).clear();
        driver.findElement(metroStationField).sendKeys(metroStation);
        By metroLocator = By.xpath("//div[contains(text(), '" + metroStation + "')]");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(metroLocator));
        driver.findElement(metroLocator).click();
            return this;
        }

    //Метод заполнения поля "Телефон: на него позвонит курьер"
    public OrderFormPage setPhoneNumberField(String phoneNumber) {
        driver.findElement(phoneNumberField).clear();
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
        return this;
    }

    //Метод заполнения формы заказа полностью
    public OrderFormPage setOrderFormCompletely(String name, String surname, String address, String metroStation, String phoneNumber) {
        setNameField(name);
        setSurnameField(surname);
        setAddressField(address);
        setMetroStationField(metroStation);
        setPhoneNumberField(phoneNumber);
        return this;
    }

    // Метод нажатия кнопки "Далее"
    public void clickFurtherButton() {
        driver.findElement(furtherButton).click();
    }
}
