package ru.netology.delivery.test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.delivery.data.DataGenerator;
import java.time.Duration;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class DeliveryTest {
    DataGenerator dataGenerator = new DataGenerator();
    String date = dataGenerator.generateDate(4);
    String changeTheDate = dataGenerator.generateDate(10);


    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void otherMeetingDate(){
        $("[data-test-id=city]").$("[type=text]").setValue(DataGenerator.generateCity());
        $("[data-test-id=date]").$("[class=input__control]").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id=date]").$("[class=input__control]").setValue(date);
        $("[name=name]").setValue(DataGenerator.generateName("ru"));
        $("[name=phone]").setValue(DataGenerator.generatePhone("ru"));
        $(".checkbox__text").click();
        $(".button__text").click();
        $("[data-test-id=success-notification]").shouldBe(visible, Duration.ofSeconds(14));
        $("[data-test-id=success-notification] [class=notification__content]").shouldHave(exactText("Встреча успешно запланирована на " + date))
                .shouldBe(visible);
        $("[data-test-id=success-notification] [class=icon-button__content]").click();
        $("[data-test-id=date]").$("[class=input__control]").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id=date]").$("[class=input__control]").setValue(changeTheDate);
        $(".button__text").click();
        $("[data-test-id=replan-notification] [class=notification__content]").shouldHave(exactText("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(visible);
        $("[data-test-id=replan-notification] [class=button__content]").click();
        $("[data-test-id=success-notification] [class=notification__content]").shouldHave(exactText("Встреча успешно запланирована на " + changeTheDate))
                .shouldBe(visible);
    }
//    @Test
//    @DisplayName("Should successful plan and replan meeting")
//    void currentMeetingDate(){
//        $("[data-test-id=city]").$("[type=text]").setValue(DataGenerator.generateCity());
//        $("[data-test-id=date]").$("[class=input__control]").doubleClick().sendKeys(BACK_SPACE);
//        $("[data-test-id=date]").$("[class=input__control]").setValue(date);
//        $("[name=name]").setValue(DataGenerator.generateName("ru"));
//        $("[name=phone]").setValue(DataGenerator.generatePhone("ru"));
//        $(".checkbox__text").click();
//        $(".button__text").click();
//        $("[data-test-id=success-notification]").shouldBe(visible, Duration.ofSeconds(14));
//        $("[data-test-id=success-notification] [class=notification__content]").shouldHave(exactText("Встреча успешно запланирована на " + date))
//                .shouldBe(visible);
//        $("[data-test-id=success-notification] [class=icon-button__content]").click();
//        //следующие 2 шага можно пропустить,
//        $("[data-test-id=date]").$("[class=input__control]").doubleClick().sendKeys(BACK_SPACE);
//        $("[data-test-id=date]").$("[class=input__control]").setValue(date);
//
//        $(".button__text").click();
//        $("[data-test-id=success-notification] [class=notification__content]").shouldHave(exactText("У вас уже запланирована встреча на эту дату "))
//                .shouldBe(visible);
//    }
}
