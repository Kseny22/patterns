package ru.netology.delivery.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int days) {
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity() {
        List<String> list = Arrays.asList("Волгоград", "Воронеж", "Астрахань", "Барнаул", "Тамбов","Красноярск", "Калининград", "Краснодар", "Самара", "Саратов", "Владимир", "Новосибирск", "Рязань", "Ижевск", "Москва",
                "Санкт-Петербург", "Ростов-на-Дону");
        Random rand = new Random();
        String city = list.get(rand.nextInt(list.size()));
        return city;
    }

    public static String generateName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generatePhone() {

        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }
}



