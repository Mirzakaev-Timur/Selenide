package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestForm {

    public String generateDate(int Days) {
        return LocalDate.now().plusDays(Days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");

    }

    @Test
    void checkForValidValues() {
        String planningDate = generateDate(7);

        $("[data-test-id='city'] input").val("Уфа");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("Петров-Водкин Аркадий");
        $("[data-test-id='phone'] .input__control").val("+79994447788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(10));
}
}
