package ru.yandex.practicum.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import ru.yandex.practicum.constants.MainPageConstants;
import ru.yandex.practicum.pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQDropdownListTest extends BaseTest implements MainPageConstants {


    private final By question;
    private final String expectedAnswer;


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {MainPage.paymentAndCostsQuestion, MainPageConstants.PAYMENTANDCOSTSANSWER_TEXT},
                {MainPage.multipleScootersQuestion, MainPageConstants.MULTIPLESCOOTERSANSWER_TEXT},
                {MainPage.rentalTimeCalculationQuestion, MainPageConstants.RENTALTIMECALCULATIONANSWER_TEXT},
                {MainPage.orderTodayQuestion, MainPageConstants.ORDERTODAYANSWER_TEXT},
                {MainPage.extendOrReturnEarlyQuestion, MainPageConstants.EXTENDORRETURNEARLYANSWER_TEXT},
                {MainPage.chargingEquipmentQuestion, MainPageConstants.CHARGINGEQUIPMENTANSWER_TEXT},
                {MainPage.cancellationPolicyQuestion, MainPageConstants.CANCELLATIONPOLICYANSWER_TEXT},
                {MainPage.deliveryOutsideMKADQuestion, MainPageConstants.DELIVERYOUTSIDEMKADANSWER_TEXT}
        });
    }

    public FAQDropdownListTest(By question, String expectedAnswer) {

        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }


    @Test
    public void checkQuestionsAndAnswersCompliance() {


        mainPage.openPage();
        mainPage.acceptCookie();
        mainPage.scrollToMainPageFAQ();

        mainPage.clickQuestion(question);
        String actualAnswer = mainPage.getAnswerText(getAnswerLocator(question));

        assertEquals(expectedAnswer, actualAnswer);

    }
    private By getAnswerLocator(By questionLocator) {
        if (questionLocator.equals(MainPage.paymentAndCostsQuestion)) {
            return MainPage.paymentAndCostsAnswer;
        } else if (questionLocator.equals(MainPage.multipleScootersQuestion)) {
            return MainPage.multipleScootersAnswer;
        } else if (questionLocator.equals(MainPage.rentalTimeCalculationQuestion)) {
            return MainPage.rentalTimeCalculationAnswer;
        } else if (questionLocator.equals(MainPage.orderTodayQuestion)) {
            return MainPage.orderTodayAnswer;
        } else if (questionLocator.equals(MainPage.extendOrReturnEarlyQuestion)) {
            return MainPage.extendOrReturnEarlyAnswer;
        } else if (questionLocator.equals(MainPage.chargingEquipmentQuestion)) {
            return MainPage.chargingEquipmentAnswer;
        } else if (questionLocator.equals(MainPage.cancellationPolicyQuestion)) {
            return MainPage.cancellationPolicyAnswer;
        } else if (questionLocator.equals(MainPage.deliveryOutsideMKADQuestion)) {
            return MainPage.deliveryOutsideMKADAnswer;
        }
        return null;
    }
}

