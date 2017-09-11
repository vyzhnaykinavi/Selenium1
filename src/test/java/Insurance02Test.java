import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.MainPage;
import pages.SendAppPage;

import static org.junit.Assert.assertEquals;

public class Insurance02Test extends BaseTest {

    @Test
    public void testInsuranceTest02() throws Exception {
        driver.get(baseUrl + "/ru/person");

        MainPage mainPage = new MainPage(driver);
        wait.until(ExpectedConditions.visibilityOf(mainPage.menuBlock));
        mainPage.selectMenuItem("Застраховать себя");

        driver.findElement(By.linkText("Страхование путешественников")).click();
        assertEquals("Страхование путешественников", mainPage.title.getText());

        mainPage.InsuranceBanner.click();
        switchToNewWindow();

        SendAppPage sendAppPage = new SendAppPage(driver);
        wait.until(ExpectedConditions.visibilityOf(sendAppPage.minimalCaseButton));
        sendAppPage.minimalCaseButton.click();
        sendAppPage.continueButton.click();

        sendAppPage.fillField("Фамилия застрахованного", "Sokolov");
        sendAppPage.fillField("Имя застрахованного", "Oleg");
        sendAppPage.fillField("Дата рождения застрахованного", "12121990");
        sendAppPage.fillField("Фамилия срахователя", "Соколов");
        sendAppPage.fillField("Имя срахователя", "Олег");
        sendAppPage.fillField("Отчество срахователя", "Геннадьевич");
        sendAppPage.fillField("Дата рождения срахователя", "12121990");
        sendAppPage.fillField("Серия паспорта срахователя", "0810");
        sendAppPage.fillField("Номер паспорта срахователя", "941299");
        sendAppPage.fillField("Дата выдачи паспорта срахователя", "12122006");
        sendAppPage.fillField("Кем выдан паспорт срахователя", "прр плллрол");

        assertEquals("Sokolov", sendAppPage.getFillField("Фамилия застрахованного"));
        assertEquals("Oleg", sendAppPage.getFillField("Имя застрахованного"));
        assertEquals("12.12.1990", sendAppPage.getFillField("Дата рождения застрахованного"));
        assertEquals("Соколов", sendAppPage.getFillField("Фамилия срахователя"));
        assertEquals("Олег", sendAppPage.getFillField("Имя срахователя"));
        assertEquals("Геннадьевич", sendAppPage.getFillField("Отчество срахователя"));
        assertEquals("12.12.1990", sendAppPage.getFillField("Дата рождения срахователя"));
        assertEquals("0810", sendAppPage.getFillField("Серия паспорта срахователя"));
        assertEquals("941299", sendAppPage.getFillField("Номер паспорта срахователя"));
        assertEquals("12.12.2006", sendAppPage.getFillField("Дата выдачи паспорта срахователя"));
        assertEquals("прр плллрол", sendAppPage.getFillField("Кем выдан паспорт срахователя"));

        sendAppPage.continueButton.click();
        sendAppPage.checkFieldErrorMessage("Заполнены не все обязательные поля");
    }
}
