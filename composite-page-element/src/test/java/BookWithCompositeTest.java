import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.nmt.pages.BookAviaWithComponentPage;

public class BookWithCompositeTest {

    @Test
    void bookAviaTest() {
        // TODO: нужно вставить вашу ссылку на страницу бронирования авиабилетов
        Selenide.open(
                "https://travel.alfabank.ru/avia/book?tid=ALFARDSNOWE1000000090MOWLED20250820_GI_36_RU_1000-TUA.5N.0.80.F372100S305000" +
                        ".OENCTUlYVkVMXzVOXzM0NjU%3D...RUB-36.5N.275.SVO.202508202030.LED.202508202150.738.FLTOW.80.0.TUA.0.0PC.1P10K..1" +
                        ".0..0.0.0");

        Selenide.page(BookAviaWithComponentPage.class)
                .fillContactForm("ex@mail.ru", "9998887766");
    }
}

