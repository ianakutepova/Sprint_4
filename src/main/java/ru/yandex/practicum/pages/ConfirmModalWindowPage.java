package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ConfirmModalWindowPage {

    private final WebDriver driver;

    //Модальное окно с запросом подтверждения оформления заказа
    public final By orderConfirmQuestionBox = By.xpath(".//div[@class='Order_Modal__YZ-d3' and contains(text(), 'Хотите оформить заказ?')]");

    //Кнопка "Да" в модальном окне подтверждения заказа
    public final By yesButton = By.xpath(".//button[contains(text(), 'Да')]");

    //Модальное окно оформленного заказа
    public final By orderIssuedModalWindow = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");


    public ConfirmModalWindowPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод проверки доступности и нажатия на кнопку "Да" в окне подтверждения заказа
    public void clickYesButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderConfirmQuestionBox));
        driver.findElement(yesButton).click();

    }


}


