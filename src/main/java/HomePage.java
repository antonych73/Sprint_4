import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class HomePage {
    public static final String HOME_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    @FindBy(how = How.ID,using = "accordion__heading-0")
    private SelenideElement questPrice;
    @FindBy(how = How.ID,using = "accordion__heading-1")
    private SelenideElement questSeveralScooters;
    @FindBy(how = How.ID,using = "accordion__heading-2")
    private SelenideElement questRentTime;
    @FindBy(how = How.ID,using = "accordion__heading-3")
    private SelenideElement questRentToday;
    @FindBy(how = How.ID,using = "accordion__heading-4")
    private SelenideElement questProlong;
    @FindBy(how = How.ID,using = "accordion__heading-5")
    private SelenideElement questBattery;
    @FindBy(how = How.ID,using = "accordion__heading-6")
    private SelenideElement questCancellation;
    @FindBy(how = How.ID,using = "accordion__heading-7")
    private SelenideElement questOutsideMoscow;


    //локатор панелей элементов аккардиона
    @FindBy(how = How.ID,using = "accordion__panel-0")
    private SelenideElement answPrice;
    @FindBy(how = How.ID,using = "accordion__panel-1")
    private SelenideElement answSeveralScooters;
    @FindBy(how = How.ID,using = "accordion__panel-2")
    private SelenideElement answRentTime;
    @FindBy(how = How.ID,using = "accordion__panel-3")
    private SelenideElement answRentToday;
    @FindBy(how = How.ID,using = "accordion__panel-4")
    private SelenideElement answProlong;
    @FindBy(how = How.ID,using = "accordion__panel-5")
    private SelenideElement answBattery;
    @FindBy(how = How.ID,using = "accordion__panel-6")
    private SelenideElement answCancellation;
    @FindBy(how = How.ID,using = "accordion__panel-7")
    private SelenideElement answOutsideMoscow;


    //локатор кнопки "Заказать" верхней
    @FindBy(how = How.CLASS_NAME,using = "Button_Button__ra12g")
    private SelenideElement topOrderButton;

    //локатор кнопки "Заказать" нижней
    @FindBy(how = How.XPATH,using = ".//div[@class='Home_FinishButton__1_cWm']//*[contains(text(),'Заказать')]")
    private SelenideElement bottomOrderButton;

    //локатор кнопки закрытия информационного окна о куках
    @FindBy (how = How.CLASS_NAME, using = "App_CookieButton__3cvqF")
    private SelenideElement cookieButton;

    public SelenideElement getQuestPrice() {
        return questPrice;
    }
    public SelenideElement getQuestSeveralScooters() {
        return questSeveralScooters;
    }
    public SelenideElement getQuestRentTime() {
        return questRentTime;
    }
    public SelenideElement getQuestRentToday() {
        return questRentToday;
    }
    public SelenideElement getQuestProlong() {
        return questProlong;
    }
    public SelenideElement getQuestBattery() {
        return questBattery;
    }
    public SelenideElement getQuestCancellation() {
        return questCancellation;
    }
    public SelenideElement getQuestOutsideMoscow() {
        return questOutsideMoscow;
    }

    public SelenideElement getAnswPrice() {
        return answPrice;
    }
    public SelenideElement getAnswSeveralScooters() {
        return answSeveralScooters;
    }
    public SelenideElement getAnswRentTime() {
        return answRentTime;
    }
    public SelenideElement getAnswRentToday() {
        return answRentToday;
    }
    public SelenideElement getAnswProlong() {
        return answProlong;
    }
    public SelenideElement getAnswBattery() {
        return answBattery;
    }
    public SelenideElement getAnswCancellation() {
        return answCancellation;
    }
    public SelenideElement getAnswOutsideMoscow() {
        return answOutsideMoscow;
    }

    public SelenideElement getCookieButton() {
        return cookieButton;
    }

    // метод клика по строке вопроса
    public void openQuestionPanel(SelenideElement accordionButton) {
        accordionButton.shouldBe(visible);
        accordionButton.scrollIntoView(true);
        accordionButton.click();
    }

    // метод получения текста ответа из открытой панели вопроса.
    public String getAnswerText(SelenideElement accordionPanel) {
        accordionPanel.shouldBe(visible);
        return accordionPanel.getText();
    }

    //метод клика по "верхней" кнопке "Заказать"
    public void clickTopOrderButton() {
        topOrderButton.scrollIntoView(true);
        topOrderButton.click();
    }

    //метод клика по "нижней" кнопке "Заказать"
    public void  clickBottomOrderButton() {
        bottomOrderButton.scrollIntoView(true);
        bottomOrderButton.click();
    }

    // метод клика по кнопке куков
    public void cookieClose () {
        if (getCookieButton().isDisplayed()) {
            getCookieButton().scrollTo().click();
        }
    }

}
