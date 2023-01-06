import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static org.junit.Assert.assertTrue;

public class OrderPage {
    //заголовок окна заказа
    @FindBy(how = How.XPATH,using = ".//div[@class='Order_Header__BZXOb']")
    private SelenideElement orderHeader;

    //поле "Имя"
    @FindBy(how = How.XPATH, using = ".//input [@placeholder='* Имя']")
    private SelenideElement orderFieldName;

    //поле "Фамилия"
    @FindBy(how = How.XPATH, using = ".//input [@placeholder='* Фамилия']")
    private SelenideElement orderFieldSurname;

    //поле "Адрес"
    @FindBy(how = How.XPATH, using = ".//input [@placeholder='* Адрес: куда привезти заказ']")
    private SelenideElement orderFieldAddress;

    //поле "Телефон"
    @FindBy(how = How.XPATH, using = ".//input [@placeholder='* Телефон: на него позвонит курьер']")
    private SelenideElement orderFieldTelephone;

    //поле "Станция"
    @FindBy(how = How.XPATH, using = ".//input [@placeholder='* Станция метро']")
    private SelenideElement orderFieldStation;

    //поле из списка "Станция"
    @FindBy(how = How.CLASS_NAME, using = "select-search__select")
    private SelenideElement orderFieldStationOption;

    //кнопка "Далее"
    @FindBy(how = How.XPATH, using = ".//div[@class='Order_NextButton__1_rCA']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private SelenideElement orderButtonFurther;

    // метод проверки текста шапки окна заказа
    public void checkHeaderText() {
        orderHeader.shouldHave(exactText("Для кого самокат"));
    }

    // метод заполнения полей на первой странице формы зказа - все поля
    public void fillOrderFieldsForWhomPage(String firstName,
                                           String secondName,
                                           String city,
                                           String telephone,
                                           String stationMetro) {
        orderFieldName.setValue(firstName);
        orderFieldSurname.setValue(secondName);
        orderFieldAddress.setValue(city);
        orderFieldStation.click();
        orderFieldStation.setValue(stationMetro);
        orderFieldStationOption.find(byText(stationMetro)).click();
        orderFieldTelephone.setValue(telephone);
    }

    // метод клика по кнопке "Далее" первой страницы формы заказа
    public void clickOrderButton() {
        orderButtonFurther.click();
    }

    // метод проверки наличия полей на первой странице формы заказа
    public void checkElementsOrderPage() {
        assertTrue("Нет поля 'Имя'", orderFieldName.is(visible));
        assertTrue("Нет поля 'Фамилия'", orderFieldSurname.is(visible));
        assertTrue("Нет поля 'Адрес: куда привезти заказ'", orderFieldAddress.is(visible));
        assertTrue("Нет поля Телефон: на него позвонит курьер", orderFieldTelephone.is(visible));
        assertTrue("Нет поля 'Станция метро'", orderFieldStation.is(visible));
        assertTrue("Нет кнопки 'Далее'", orderButtonFurther.isDisplayed());
    }
}
