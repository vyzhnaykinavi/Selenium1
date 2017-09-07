import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InsuranceTest02 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        baseUrl = "http://www.sberbank.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void testInsuranceTest02() throws Exception {
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);

        driver.get(baseUrl + "ru/person");
        //driver.findElement(By.xpath("//ul[@class='alt-menu-mid__list']/li[5]/a/span/span[1]")).click();
        driver.findElement(By.partialLinkText("Застраховать себя")).click();
        driver.findElement(By.linkText("Страхование путешественников")).click();
        assertEquals("Страхование путешественников", driver.findElement(By.cssSelector(".sbrf-rich-outer>h1")).getText());
        driver.findElement(By.cssSelector("p > a > img")).click();

        switchToNewWindow();
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.cssSelector("div.b-form-prog-note.ng-binding"))));
        driver.findElement(By.cssSelector("div.b-form-prog-note.ng-binding")).click();
        driver.findElement(By.cssSelector("span.b-continue-btn")).click();
        driver.findElement(By.name("insured0_surname")).clear();
        driver.findElement(By.name("insured0_surname")).sendKeys("Sokolov");
        driver.findElement(By.name("insured0_name")).clear();
        driver.findElement(By.name("insured0_name")).sendKeys("Oleg");
        driver.findElement(By.cssSelector("img.ui-datepicker-trigger")).click();
        driver.findElement(By.name("insured0_birthDate")).sendKeys("12121990");
        driver.findElement(By.name("surname")).clear();
        driver.findElement(By.name("surname")).sendKeys("Соколов");
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("Олег");
        driver.findElement(By.name("middlename")).clear();
        driver.findElement(By.name("middlename")).sendKeys("Геннадьевич");
        driver.findElement(By.xpath("(//img[@alt='Выбрать дату'])[2]")).click();
        driver.findElement(By.name("birthDate")).sendKeys("12121990");
        driver.findElement(By.name("passport_series")).clear();
        driver.findElement(By.name("passport_series")).sendKeys("0810");
        driver.findElement(By.name("passport_number")).clear();
        driver.findElement(By.name("passport_number")).sendKeys("941299");
        driver.findElement(By.xpath("(//img[@alt='Выбрать дату'])[3]")).click();
        driver.findElement(By.name("issueDate")).sendKeys("12122006");
        driver.findElement(By.name("issuePlace")).clear();
        driver.findElement(By.name("issuePlace")).sendKeys("прр плллрол");

        assertEquals("Sokolov", driver.findElement(By.name("insured0_surname")).getAttribute("value"));
        assertEquals("Oleg", driver.findElement(By.name("insured0_name")).getAttribute("value"));
        assertEquals("12.12.1990", driver.findElement(By.name("insured0_birthDate")).getAttribute("value"));
        assertEquals("Соколов", driver.findElement(By.name("surname")).getAttribute("value"));
        assertEquals("Олег", driver.findElement(By.name("name")).getAttribute("value"));
        assertEquals("Геннадьевич", driver.findElement(By.name("middlename")).getAttribute("value"));
        assertEquals("12.12.1990", driver.findElement(By.name("birthDate")).getAttribute("value"));
        assertEquals("0810", driver.findElement(By.name("passport_series")).getAttribute("value"));
        assertEquals("941299", driver.findElement(By.name("passport_number")).getAttribute("value"));
        assertEquals("12.12.2006", driver.findElement(By.name("issueDate")).getAttribute("value"));
        assertEquals("прр плллрол", driver.findElement(By.name("issuePlace")).getAttribute("value"));

        driver.findElement(By.cssSelector("span.b-continue-btn")).click();

        //driver.findElement(By.xpath("//*[@id='views']/section/form/section/section[5]/div[1]/span[2]")).click();

        assertEquals(
            "Заполнены не все обязательные поля",
            driver.findElement(By.xpath("//div[contains(@ng-show,'tryNext && myForm.$invalid')]")).getText()
        );
        int WE =9;
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private void switchToNewWindow() {
        Set<String> handles = driver.getWindowHandles();
        for (String winHandle : handles) {
            if (!winHandle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(winHandle);
            }
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
