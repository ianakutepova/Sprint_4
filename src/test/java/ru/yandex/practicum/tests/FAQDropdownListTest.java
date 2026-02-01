package ru.yandex.practicum.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.constants.MainPageConstants;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQDropdownListTest extends BaseTest implements MainPageConstants {

    private WebDriver driver;
    private final String question;
    private final String expectedAnswer;


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Вопрос 1", MainPageConstants.PAYMENTANDCOSTSANSWER_TEXT},
                {"Вопрос 2", MainPageConstants.MULTIPLESCOOTERSQUESTION_TEXT},
                {"Вопрос 3", MainPageConstants.RENTALTIMECALCULATIONQUESTION_TEXT},
                {"Вопрос 4", MainPageConstants.ORDERTODAYQUESTION_TEXT},
                {"Вопрос 5", MainPageConstants.EXTENDORRETURNEARLYQUESTION_TEXT},
                {"Вопрос 6", MainPageConstants.CHARGINGEQUIPMENTQUESTION_TEXT},
                {"Вопрос 7", MainPageConstants.CANCELLATIONPOLICYQUESTION_TEXT},
                {"Вопрос 8", MainPageConstants.DELIVERYOUTSIDEMKADQUESTION_TEXT}

        });
    }

    public FAQDropdownListTest(String question, String expectedAnswer) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }


    @Test
    public void checkQuestionsAndAnswersCompliance() {


        mainPage.openPage();
        mainPage.acceptCookie();
        mainPage.scrollToMainPageFAQ();

        int questionNumber = Integer.parseInt(question.replace("Вопрос ", ""));
        mainPage.clickQuestion(questionNumber);

        String actualAnswer = mainPage.getAnswerText(questionNumber);
        assertEquals(expectedAnswer, actualAnswer);

    }
}

