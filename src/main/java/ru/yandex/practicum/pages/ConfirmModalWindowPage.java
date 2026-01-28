package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ConfirmModalWindowPage {

    private final WebDriver driver;

    //Модальное окно с запросом подтверждения оформления заказа
    private final By orderConfirmQuestionBox = By.xpath(".//div[@class='Order_Modal__YZ-d3' and contains(text(), 'Хотите оформить заказ?')]");

    //Кнопка "Да" в модальном окне подтверждения заказа
    private final By yesButton = By.xpath(".//button[contains(text(), 'Да')]");

    //Модальное окно оформленного заказа
    private final By orderIssuedModalWindow = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");


    public ConfirmModalWindowPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод проверки доступности и нажатия на кнопку "Да" в окне подтверждения заказа
    public ConfirmModalWindowPage clickYesButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(yesButton));
        driver.findElement(yesButton).click();
        return this;
    }


}


