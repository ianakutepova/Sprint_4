package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.constants.MainPageConstants;

import java.time.Duration;


public class MainPage implements MainPageConstants {

    private WebDriver driver;

    //Элементы главной страницы, задействованные в тестах
    // Кнопка "Заказать" вверху страницы
    private final By upperOrderButton = By.xpath(".//button[@class = 'Button_Button__ra12g']");
    // кнопка "да все привыкли" для принятия cookie
    private final By acceptCookieButton = By.id("rcc-confirm-button");
    // кнопка "Заказать" внизу страницы
    private final By lowerOrderButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    //Элемент раздела главной страницы «Вопросы о важном»
    private final By mainPageFAQ = By.xpath("//div[contains(text(), 'Вопросы о важном')]");

    // Вопрос 1 "Сколько это стоит? И как оплатить?"
    public static By paymentAndCostsQuestion = By.xpath(".//div[@class='accordion__item'][1]");
    // Вопрос 2 "Хочу сразу несколько самокатов! Так можно?"
    public static By multipleScootersQuestion = By.xpath(".//div[@class='accordion__item'][2]");
    // Вопрос 3 "Как рассчитывается время аренды?"
    public static By rentalTimeCalculationQuestion = By.xpath(".//div[@class='accordion__item'][3]");
    // Вопрос 4 "Можно ли заказать самокат прямо на сегодня?"
    public static By orderTodayQuestion = By.xpath(".//div[@class='accordion__item'][4]");
    // Вопрос 5 "Можно ли продлить заказ или вернуть самокат раньше?"
    public static By extendOrReturnEarlyQuestion = By.xpath(".//div[@class='accordion__item'][5]");
    // Вопрос 6 "Вы привозите зарядку вместе с самокатом?"
    public static By chargingEquipmentQuestion = By.xpath(".//div[@class='accordion__item'][6]");
    // Вопрос 7 "Можно ли отменить заказ?"
    public static By cancellationPolicyQuestion = By.xpath(".//div[@class='accordion__item'][7]");
    // Вопрос 8 "Я живу за МКАДом, привезёте?"
    public static By deliveryOutsideMKADQuestion = By.xpath(".//div[@class='accordion__item'][8]");

    // Ответ на вопрос 1 "Сколько это стоит? И как оплатить?"
    public static By paymentAndCostsAnswer = By.id("accordion__panel-0");
    // Ответ на вопрос 2 "Хочу сразу несколько самокатов! Так можно?"
    public static By multipleScootersAnswer = By.id("accordion__panel-1");
    // Ответ на вопрос 3 "Как рассчитывается время аренды?"
    public static By rentalTimeCalculationAnswer = By.id("accordion__panel-2");
    // Ответ на вопрос 4 "Можно ли заказать самокат прямо на сегодня?"
    public static By orderTodayAnswer = By.id("accordion__panel-3");
    // Ответ на вопрос 5 "Можно ли продлить заказ или вернуть самокат раньше?"
    public static By extendOrReturnEarlyAnswer = By.id("accordion__panel-4");
    // Ответ на вопрос 6 "Вы привозите зарядку вместе с самокатом?"
    public static By chargingEquipmentAnswer = By.id("accordion__panel-5");
    // Ответ на вопрос 7 "Можно ли отменить заказ?"
    public static By cancellationPolicyAnswer = By.id("accordion__panel-6");
    // Ответ на вопрос 8 "Я живу за МКАДом, привезёте?"
    public static By deliveryOutsideMKADAnswer = By.id("accordion__panel-7");


    //Прочие элементы главной страницы
    // Логотип "Яндекс"
    private final By yandexLogo = By.xpath("//img[@alt='Yandex']");
    // Логотип Самокат
    private final By scooterLogo = By.xpath("//img[@alt='Scooter']");
    // кнопка "Статус заказа"
    private final By statusOrderButton = By.xpath("//button[text()='Статус заказа']");
    // поле для ввода номера заказа
    private final By orderNumberField = By.xpath("//input[@placeholder = 'Введите номер заказа']");
    // кнопка "GO" для начала поиска заказа
    private final By goButton = By.xpath("//button[text()='Go!']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(START_URL);
    }

    public void acceptCookie() {
        WebElement cookieMessage = driver.findElement(By.className("App_CookieText__1sbqp"));
        if (cookieMessage.isDisplayed()) {
            driver.findElement(acceptCookieButton).click();
        }
    }


    public void clickUpperOrderButton() {
        driver.findElement(upperOrderButton).click();
    }

    public void scrollToLowerOrderButton() {
        WebElement lowerOrderButtonElement = driver.findElement(lowerOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", lowerOrderButtonElement);
    }


    public void clickLowerOrderButton() {
        driver.findElement(lowerOrderButton).click();
    }

    //Метод прокрутки до раздела «Вопросы о важном»
    public void scrollToMainPageFAQ() {
        WebElement mainPageFAQElement = driver.findElement(mainPageFAQ);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mainPageFAQElement);
    }

    // Методы для раскрытия вопросов
    public void clickQuestion(int questionNumber) {
        WebElement question = driver.findElement(By.xpath(".//div[@class='accordion__item'][" + questionNumber + "]"));
        question.click();
    }

    //Методы получения ответа
    public String getAnswerText(int questionNumber) {
        String answerId = "accordion__panel-" + (questionNumber - 1);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(answerId)));
        return answer.getText();
    }
}