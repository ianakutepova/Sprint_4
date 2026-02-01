package ru.yandex.practicum.utils;

import java.util.Random;

public class DataGenerator {
        private static final String[] names = {"Иван", "Мария", "Павел", "Елена"};
        private static final String[] surnames = {"Иванов", "Федорова", "Сидоров", "Смирнова"};
        private static final String[] addresses = {"г. Москва, ул. Пресненский Вал, 16, строение 2", "г. Москва, ул. Тверская, 4", "г. Москва, ул. Героев Панфиловцев, 26", "г. Москва, ул. Мясницкий ряд, 48"};
        private static final String[] metroStations = {"Улица 1905 года", "Охотный Ряд", "Планерная", "Красные Ворота",};
        private static final String[] phoneNumbers = {"89012223344", "89012345678", "89039876543", "89090090909"};
        private static final String[] dates = {"01.02.2026", "02.02.2026", "03.02.2026", "04.02.2026"};
        private static final String[] days = {"сутки", "двое суток", "четверо суток", "семеро суток"};
        private static final String[] colors = {"black", "grey"};
        private static final String[] comments = {"Оставьте у двери", "Позвоните перед доставкой", "Доставка на работу", "-"};

        private Random random = new Random();

        public String generateName() {
            return names[random.nextInt(names.length)];
        }

        public String generateSurname() {
            return surnames[random.nextInt(surnames.length)];
        }

        public String generateAddress() {
            return addresses[random.nextInt(addresses.length)];
        }

        public String generateMetroStation() {
            return metroStations[random.nextInt(metroStations.length)];
        }

        public String generatePhoneNumber() {
            return phoneNumbers[random.nextInt(phoneNumbers.length)];
        }

        public String generateDate() {
            return dates[random.nextInt(dates.length)];
        }

        public String generateDays() {
            return days[random.nextInt(days.length)];
        }

        public String generateColor() {
            return colors[random.nextInt(colors.length)];
        }

        public String generateComment() {
            return comments[random.nextInt(comments.length)];
        }
    }


