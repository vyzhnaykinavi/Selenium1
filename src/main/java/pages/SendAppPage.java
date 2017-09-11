package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class SendAppPage extends BasePage {

    @FindBy(name = "insured0_surname")
    WebElement lastNameInsured;
    @FindBy(name = "insured0_name")
    WebElement firstNameInsured;
    @FindBy(name = "insured0_birthDate")
    WebElement birthDateInsured;

    @FindBy(name = "surname")
    WebElement lastName;
    @FindBy(name = "name")
    WebElement firstName;
    @FindBy(name = "middlename")
    WebElement middleName;
    @FindBy(name = "birthDate")
    WebElement birthDate;

    @FindBy(name = "passport_series")
    WebElement passportSeries;
    @FindBy(name = "passport_number")
    WebElement passportNumber;
    @FindBy(name = "issueDate")
    WebElement issueDate;
    @FindBy(name = "issuePlace")
    WebElement issuePlace;


    @FindBy(css = "div.b-form-prog-note.ng-binding")
    public WebElement minimalCaseButton;
    @FindBy(css = "span.b-continue-btn")
    public WebElement continueButton;

    public SendAppPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void fillField(String fieldName, String value) {
        switch (fieldName) {
            case "Фамилия застрахованного":
                fillField(lastNameInsured, value);
                break;
            case "Имя застрахованного":
                fillField(firstNameInsured, value);
                break;
            case "Дата рождения застрахованного":
                driver.findElement(By.cssSelector("img.ui-datepicker-trigger")).click();
                fillField(birthDateInsured, value);
                break;
            case "Фамилия срахователя":
                fillField(lastName, value);
                break;
            case "Имя срахователя":
                fillField(firstName, value);
                break;
            case "Отчество срахователя":
                fillField(middleName, value);
                break;
            case "Дата рождения срахователя":
                driver.findElement(By.xpath("(//img[@alt='Выбрать дату'])[2]")).click();
                fillField(birthDate, value);
                break;
            case "Серия паспорта срахователя":
                fillField(passportSeries, value);
                break;
            case "Номер паспорта срахователя":
                fillField(passportNumber, value);
                break;
            case "Дата выдачи паспорта срахователя":
                driver.findElement(By.xpath("(//img[@alt='Выбрать дату'])[3]")).click();
                fillField(issueDate, value);
                break;
            case "Кем выдан паспорт срахователя":
                fillField(issuePlace, value);
                break;
            default:
                throw new AssertionError("Поле '" + fieldName + "' не объявлено на странице");
        }
    }

    public String getFillField(String fieldName) {
        switch (fieldName) {
            case "Фамилия застрахованного":
                return lastNameInsured.getAttribute("value");
            case "Имя застрахованного":
                return firstNameInsured.getAttribute("value");
            case "Дата рождения застрахованного":
                return birthDateInsured.getAttribute("value");
            case "Фамилия срахователя":
                return lastName.getAttribute("value");
            case "Имя срахователя":
                return firstName.getAttribute("value");
            case "Отчество срахователя":
                return middleName.getAttribute("value");
            case "Дата рождения срахователя":
                return birthDate.getAttribute("value");
            case "Серия паспорта срахователя":
                return passportSeries.getAttribute("value");
            case "Номер паспорта срахователя":
                return passportNumber.getAttribute("value");
            case "Дата выдачи паспорта срахователя":
                return issueDate.getAttribute("value");
            case "Кем выдан паспорт срахователя":
                return issuePlace.getAttribute("value");
        }
        throw new AssertionError("Поле не объявлено на странице");
    }

    public void checkFieldErrorMessage( String errorMessage) {
        assertEquals(
            errorMessage,
            driver.findElement(By.xpath("//div[contains(@ng-show,'tryNext && myForm.$invalid')]")).getText()
        );
    }
}
