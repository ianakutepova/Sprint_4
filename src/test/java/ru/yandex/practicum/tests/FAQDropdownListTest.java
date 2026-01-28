package ru.yandex.practicum.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.constants.MainPageConstants;
import ru.yandex.practicum.pages.MainPage;
import org.openqa.selenium.chrome.ChromeDriver;

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
                {"Вопрос 1", MainPageConstants.ANSWER_1_TEXT},
                {"Вопрос 2", MainPageConstants.ANSWER_2_TEXT},
                {"Вопрос 3", MainPageConstants.ANSWER_3_TEXT},
                {"Вопрос 4", MainPageConstants.ANSWER_4_TEXT},
                {"Вопрос 5", MainPageConstants.ANSWER_5_TEXT},
                {"Вопрос 6", MainPageConstants.ANSWER_6_TEXT},
                {"Вопрос 7", MainPageConstants.ANSWER_7_TEXT},
                {"Вопрос 8", MainPageConstants.ANSWER_8_TEXT}

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

