package ru.yandex.practicum.tests;

import org.junit.Test;


public class firstTest extends BaseTest {

    @Test
    public void openPageTest() {
        openPage();
    }

    private void openPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
}




