import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class HomePageTest {
    private static final int NUMBER_DRIVER = 2;
    private WebDriver driver;

    static String TEXT_PRICE = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    static String TEXT_SEVERAL_SCOOTERS = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    static String TEXT_RENT_TIME = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    static String TEXT_RENT_TODAY = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    static String TEXT_PROLONG = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    static String TEXT_BATTERY = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    static String TEXT_CANCELLATION = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    static String TEXT_OUTSIDE_MOSCOW = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

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
    public void testQuestAndAnswCheck() {

        HomePage homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);     // Начальная страница
        homePage.cookieClose();                                               // закрыть окно куки

        // открытие элемента аккардиона 1 и сравнение фактического текста панели аккардиона с ожидаемым
        homePage.openQuestionPanel(homePage.getQuestPrice());
        assertThat(homePage.getAnswerText(homePage.getAnswPrice()), containsString(TEXT_PRICE));

        // открытие элемента аккардиона 2 и сравнение фактического текста панели аккардиона с ожидаемым
        homePage.openQuestionPanel(homePage.getQuestSeveralScooters());
        assertThat(homePage.getAnswerText(homePage.getAnswSeveralScooters()), containsString(TEXT_SEVERAL_SCOOTERS));

        // открытие элемента аккардиона 3 и сравнение фактического текста панели аккардиона с ожидаемым
        homePage.openQuestionPanel(homePage.getQuestRentTime());
        assertThat(homePage.getAnswerText(homePage.getAnswRentTime()), containsString(TEXT_RENT_TIME));

        // открытие элемента аккардиона 4 и сравнение фактического текста панели аккардиона с ожидаемым
        homePage.openQuestionPanel(homePage.getQuestRentToday());
        assertThat(homePage.getAnswerText(homePage.getAnswRentToday()), containsString(TEXT_RENT_TODAY));

        // открытие элемента аккардиона 5 и сравнение фактического текста панели аккардиона с ожидаемым
        homePage.openQuestionPanel(homePage.getQuestProlong());
        assertThat(homePage.getAnswerText(homePage.getAnswProlong()), containsString(TEXT_PROLONG));

        // открытие элемента аккардиона 6 и сравнение фактического текста панели аккардиона с ожидаемым
        homePage.openQuestionPanel(homePage.getQuestBattery());
        assertThat(homePage.getAnswerText(homePage.getAnswBattery()), containsString(TEXT_BATTERY));

        // открытие элемента аккардиона 7 и сравнение фактического текста панели аккардиона с ожидаемым
        homePage.openQuestionPanel(homePage.getQuestCancellation());
        assertThat(homePage.getAnswerText(homePage.getAnswCancellation()), containsString(TEXT_CANCELLATION));

        // открытие элемента аккардиона 8 и сравнение фактического текста панели аккардиона с ожидаемым
        homePage.openQuestionPanel(homePage.getQuestOutsideMoscow());
        assertThat(homePage.getAnswerText(homePage.getAnswOutsideMoscow()), containsString(TEXT_OUTSIDE_MOSCOW));
    }

    @After
    public void teardown() {
        driver.quit();                                                       // Закрыть браузер
    }
}
