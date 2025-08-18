import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.nmt.pages.BookAviaPage;
import ru.nmt.pages.BookHotelPage;

public class BookTest {

    @Test
    void bookAviaTest() {
        // TODO: нужно вставить вашу ссылку на страницу бронирования авиабилетов
        Selenide.open(
                "https://travel.alfabank.ru/avia/book?tid=ALFARDSNOWE1000000090LEDAER20250702_RU-VTRIP..4.675" +
                        ".F1860000S1619900X597000S550000X1203700S1069900.OENCO2F2aWFjZW50ZXJ1dF9BbGZhO01JWFZFTF9UQ0hfMzc5MQ%3D%3D.U1Q%3D" +
                        ".-47.UT.362.LED.202507021145.VKO.202507021315.738.ULTOW.90.0.TUA.0.0PC.1P5K..1.0..0.0.0__36.DP.115.VKO" +
                        ".202507021930.AER.202507022300.73H.LECONALL.210.0.TUA.1.0PC.1PC..1.0..0.0.0");

        Selenide.page(BookAviaPage.class)
                .fillContactForm("ex@mail.ru", "9998887766");
    }

    @Test
    void bookHotelTest() {
        // TODO: нужно вставить вашу ссылку на страницу бронирования авиабилетов
        Selenide.open(
                "https://travel.alfabank.ru/avia/book?tid=ALFARDSNOWE1000000090LEDAER20250702_RU-VTRIP..4.675" +
                        ".F1860000S1619900X597000S550000X1203700S1069900.OENCO2F2aWFjZW50ZXJ1dF9BbGZhO01JWFZFTF9UQ0hfMzc5MQ%3D%3D.U1Q%3D" +
                        ".-47.UT.362.LED.202507021145.VKO.202507021315.738.ULTOW.90.0.TUA.0.0PC.1P5K..1.0..0.0.0__36.DP.115.VKO" +
                        ".202507021930.AER.202507022300.73H.LECONALL.210.0.TUA.1.0PC.1PC..1.0..0.0.0");

        Selenide.page(BookHotelPage.class)
                .fillContactForm("ex@mail.ru", "9998887766");
    }
}

