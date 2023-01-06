import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class ViewOrderBottomButtonTest {
    private static final int NUMBER_DRIVER = 2;
    private WebDriver driver;

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
    public void testViewOrderBottomButton() {

        HomePage homePage = open(HomePage.HOME_PAGE_URL, HomePage.class);     // Начальная страница
        homePage.cookieClose();                                               // закрыть окно куки
        homePage.clickBottomOrderButton();                                    // кликнуть по "нижней" кнопке заказа

        OrderPage orderForWhomPage = page(OrderPage.class);                   // Страница "Для кого самокат"
        orderForWhomPage.checkHeaderText();                                   // проверить заголовок страницы "Для кого самокат"
        orderForWhomPage.checkElementsOrderPage();                            // проверить наличие эелементов на странице "Для кого самокат"
    }

    @After
    public void teardown() {
        driver.quit();                                                        // Закрыть браузер
    }
}
