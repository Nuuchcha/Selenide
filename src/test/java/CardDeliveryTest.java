import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardDeliveryTest {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    public void shuldBeCompleted(){
        open("http://localhost:9999");
        $("[data-test-id = 'city'] input").setValue("Челябинск");
        String planingDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id = 'date'] input").setValue(planingDate);
        $("[data-test-id = 'name'] input").setValue("Королев Евгений");
        $("[data-test-id = 'phone'] input").setValue("+79876543210");
        $("[data-test-id = 'agreement']").click();
        $(".button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planingDate));

    }
}
