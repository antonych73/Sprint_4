import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class MakeOrderPositiveCaseTest {
    private static final int NUMBER_DRIVER = 2;
    private WebDriver driver;

    private final String firstName;
    private final String secondName;
    private final String city;
    private final String telephone;
    private final String stationMetro;

    private final String deliveryDate;
    private final String rentalPeriod;
    private final String colour;
    private final String comment;

    public MakeOrderPositiveCaseTest(String firstName,
                                     String secondName,
                                     String city,
                                     String telephone,
                                     String stationMetro,

                                     String deliveryDate,
                                     String rentalPeriod,
                                     String colour,
                                     String comment) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.city = city;
        this.telephone = telephone;
        this.stationMetro = stationMetro;

        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getCities() {
        return new Object[][]{
                {
                        "Мария", "Морозова", "Москва", "89031112233", "Коломенская",
                        "20", "двое суток", "чёрный жемчуг", "очень надо"
                },
                {
                        "Антон", "Морозов", "Москва", "89032224455", "Домодедовская",
                        "21", "трое суток", "чёрный жемчуг", "не очень надо"
                },
        };
    }

    @Before
    public void setBrowser() {
        assertTrue("Некорректный номер браузера", NUMBER_DRIVER == 1 || NUMBER_DRIVER == 2);
        if(NUMBER_DRIVER == 1) {
            driver = new FirefoxDriver();
        }
        if (NUMBER_DRIVER == 2) {
            driver = new ChromeDriver();
        }
        // Путь к каталогу, в котором находятся chromedriver.exe и geckodriver.exe прописан в системной переменной PATH ОС Windows
        WebDriverRunner.setWebDriver(driver);
    }

    @Test
    public void testMakeOrderPositiveCase() {

        HomePage homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);     // Начальная страница
        homePage.cookieClose();                                               // закрыть окно куки
        homePage.clickTopOrderButton();                                       // кликнуть по "верхней" кнопке "Заказать"

        OrderPage orderForWhomPage = page(OrderPage.class);                   // Страница "Для кого самокат"
        orderForWhomPage.checkHeaderText();                                   // проверка заголовка
        orderForWhomPage.checkElementsOrderPage();                            // проверка наличия эелементов
        orderForWhomPage.fillOrderFieldsForWhomPage(
                firstName, secondName, city, telephone, stationMetro);        // заполнить форму заказа валидными данными
        orderForWhomPage.clickOrderButton();                                  // кликнуть "Далее"

        RentPage orderForRentPage = page(RentPage.class);                     // Страница "Про аренду"
        orderForRentPage.checkHeaderText();                                   // проверка заголовка
        orderForRentPage.checkElementsRentForm();                             // проверка наличия эелементов
        orderForRentPage.fillOrderFieldsForRentPage(
                deliveryDate, rentalPeriod, colour, comment);                 // заполнить форму заказа валидными данными
        orderForRentPage.clickOrderButtonDo();                                // кликнуть по нижней кнопке "Заказать"
        orderForRentPage.clickOrderButtonYes();                               // кликнуть по кнопке "Да"
        orderForRentPage.checkSuccessOrder();                                 // проверка наличия текста об успешном создании заказа
        orderForRentPage.clickOrderButtonStatus();                            // кликнуть "Посмотреть статус"
    }

    @After
    public void teardown() {
        driver.quit();                                                       // Закрыть браузер
    }
}
