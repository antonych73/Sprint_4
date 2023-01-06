import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RentPage {
    @FindBy(how = How.XPATH,using = ".//div[@class='Order_Header__BZXOb']")
    private SelenideElement orderFormHeader;

    // локатор поля "Когда привезти самокат"
    @FindBy(how = How.XPATH, using = ".//input [@placeholder='* Когда привезти самокат']")
    private SelenideElement orderFieldDeliveryDate;

    //локатор поля выбора даты
    @FindBy(how = How.CLASS_NAME, using = "react-datepicker__month-container")
    private SelenideElement orderFieldDeliveryDateDatepicker;

    // локатор поля выбора длительности аренды
    @FindBy(how = How.CLASS_NAME, using = "Dropdown-placeholder")
    private SelenideElement orderFieldRentalPeriod;

    // локатор срока аренды
    @FindBy(how = How.CLASS_NAME, using = "Dropdown-menu")
    private SelenideElement orderFieldRentalPeriodOption;

    // локатор поля выбора цвета
    @FindBy(how = How.CLASS_NAME, using = "Order_Checkboxes__3lWSI")
    private SelenideElement orderFieldColor;

    // локатор поля "Комментарий для курьера"
    @FindBy(how = How.XPATH, using = ".//input [@placeholder='Комментарий для курьера']")
    private SelenideElement orderFieldComments;

    // локатор Для кнопки "Заказать"
    @FindBy(how = How.XPATH, using = ".//div[@class='Order_Buttons__1xGrp']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private SelenideElement orderButtonDo;

    // локатор Для кнопки "Да"
    @FindBy(how = How.XPATH, using = ".//div[@class='Order_Modal__YZ-d3']/div[@class='Order_Buttons__1xGrp']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private SelenideElement orderButtonYes;

    // локатор для кнопки "Посмотреть статус"
//    @FindBy(how = How.CLASS_NAME, using = "Order_NextButton__1_rCA")
    @FindBy(how = How.XPATH, using = ".//div[@class='Order_NextButton__1_rCA']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']")

    private SelenideElement orderButtonStatus;

    // локатор для текста "Заказ оформлен"
    @FindBy(how = How.CLASS_NAME, using = "Order_ModalHeader__3FDaJ")
    private SelenideElement sucсessOrder;


    public void clickOrderButtonDo(){
        orderButtonDo.click();
    }

    public void clickOrderButtonYes(){
        orderButtonYes.click();
    }

    public void clickOrderButtonStatus(){
        orderButtonStatus.click();
    }

    // метод проверки текста шапки окна заказа
    public void checkHeaderText() {
        orderFormHeader.shouldHave(exactText("Про аренду"));
    }

    // метод проверки наличия полей на первой странице формы заказа
    public void checkElementsRentForm() {
        assertTrue("Нет поля 'Когда привезти самокат'", orderFieldDeliveryDate.is(visible));
        assertTrue("Нет поля 'Срок аренды'", orderFieldRentalPeriod.is(visible));
        assertTrue("Нет поля 'Цвет самоката'", orderFieldColor.is(visible));
        assertTrue("Нет поля 'Комментарий для курьера'", orderFieldComments.is(visible));
        assertTrue("Нет кнопки 'Заказать'", orderButtonDo.isDisplayed());
    }

    // метод заполнения полей формы "Про аренду" - все поля
    public void fillOrderFieldsForRentPage(String deliveryDate,
                                           String rentalPeriod,
                                           String colour,
                                           String comment) {
        orderFieldDeliveryDate.click();
        orderFieldDeliveryDateDatepicker.find(byText(deliveryDate)).click();
        orderFieldRentalPeriod.click();
        orderFieldRentalPeriodOption.find(byText(rentalPeriod)).click();
        orderFieldColor.find(byText(colour)).click();
        orderFieldComments.setValue(comment);
    }
    // проверка наличия текста заголовка на форме успешно-созданного заказа
    public void checkSuccessOrder() {
        assertThat(sucсessOrder.getText(), containsString("Заказ оформлен"));
    }
}
