package ru.netology.delivery.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    String name = DataGenerator.generateName();
    String phone = DataGenerator.generatePhone();
    String city = DataGenerator.generateCity();

    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        open("http://localhost:9999/");
    }

    @Test
    void submitRequest() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue(DataGenerator.generateCity());
        form.$("[data-test-id=date] input").doubleClick().sendKeys(DataGenerator.generateDate(3));
        form.$("[data-test-id=name] input").setValue(DataGenerator.generateName());
        form.$("[data-test-id=phone] input").setValue(DataGenerator.generatePhone());
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".notification_status_ok").shouldBe(visible);
        $("[data-test-id='success-notification'] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(3)));
        form.$("[data-test-id=date] input").doubleClick().sendKeys(DataGenerator.generateDate(7));
        form.$(".button").click();
        $("[data-test-id=replan-notification]").shouldBe(visible, Duration.ofSeconds(15));
        $(withText("Перепланировать")).click();
        $(".notification_status_ok").shouldBe(visible);
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(7)));
    }
}

