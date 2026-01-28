package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class RentDetailsFormPage {

        private WebDriver driver;


    //Поле для ввода даты доставки самоката
    private final By deliveryDateField = By.xpath(".//input[@placeholder ='* Когда привезти самокат']");

    //Выпадающий список для выбора срока аренды
    private final By aboutOrderHeader = By.xpath(".//div[text()='Про аренду']");
    private final By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder']");
//    //Выпадающий список для выбора срока аренды
//    private final By rentalPeriodDropdownList = By.xpath(".//div[@class='Dropdown-control']");
    //Поле с вариантами выбора цвета самоката
    private final By scooterColorField = By.xpath(".//div[@class='Order_Checkboxes__3lWSI']");
    //Поле для ввода комментария курьеру
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");


    //Кнопка "Назад"
    private final By stepBackButton = By.xpath(".//button[contains(text(), 'Назад']");

    //Кнопка "Заказать"
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    public RentDetailsFormPage(WebDriver driver) {
        this.driver = driver;
    }


    // Метод ввода даты доставки
    public RentDetailsFormPage setDeliveryDateField(String date) throws ParseException {
        // Преобразуем дату в формат, соответствующий aria-label
        String formattedDate = "Choose " + new SimpleDateFormat("EEEE, d-е MMMM yyyy г.")
                .format(new SimpleDateFormat("dd.MM.yyyy").parse(date));

        driver.findElement(deliveryDateField).clear();
        driver.findElement(deliveryDateField).sendKeys(date);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By dateLocator = By.xpath(String.format("//div[@aria-label='%s']", formattedDate));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateLocator));
        driver.findElement(dateLocator).click();
        return this;
    }


    //Метод выбора срока аренды
    public void setRentalPeriod(String rentalPeriod) {
        driver.findElement(aboutOrderHeader).click();
        driver.findElement(rentalPeriodField).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By periodLocator = By.xpath(".//div[text()='" + rentalPeriod + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(periodLocator));
        driver.findElement(periodLocator).click();
    }

    //Метод выбора цвета самоката
    public RentDetailsFormPage selectScooterColor (String color) {
        driver.findElement(scooterColorField);
        By scooterColor = By.id(color);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(scooterColor));
        driver.findElement(scooterColor).click();
        return this;
    }

    //Метод заполнения поля "Комментарий для курьера"
    public RentDetailsFormPage setCommentField(String comment) {
        driver.findElement(commentField).clear();
        driver.findElement(commentField).sendKeys(comment);
        return this;
    }

    //Общий метод для заполнения формы деталей аренды полностью
    public RentDetailsFormPage setRentDetailsFormCompletely(String date, String rentalPeriod, String color, String comment) throws ParseException {
        setDeliveryDateField(date);
        setRentalPeriod(rentalPeriod);
        selectScooterColor(color);
        setCommentField(comment);
        return this;
    }


    // Метод для нажатия на кнопку "Заказать"
    public RentDetailsFormPage clickOrderButton() {
        driver.findElement(orderButton).click();
        return this;
    }
}

