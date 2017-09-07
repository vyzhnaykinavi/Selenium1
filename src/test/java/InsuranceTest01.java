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

public class InsuranceTest01 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
    System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
    //driver = new FirefoxDriver();
    driver = new ChromeDriver();
    baseUrl = "http://www.sberbank.ru/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void testInsuranceTest01() throws Exception {
    driver.get(baseUrl + "ru/person");

    Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
    wait.until(ExpectedConditions.visibilityOf(
            driver.findElement(By.cssSelector("span.region-list__arrow"))));

    driver.findElement(By.cssSelector("span.region-list__arrow")).click();
    driver.findElement(By.linkText("Нижегородская область")).click();

    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='region-list__name']"))));

    assertEquals("Нижегородская область", driver.findElement(By.xpath("//span[@class='region-list__name']")).getText());

    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[@class='social__item']"))));

    WebElement webElem = driver.findElement(By.xpath("//li[@class='social__item']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: \"end\", behavior: \"smooth\"});", webElem);

    assertTrue(isElementPresent(By.xpath("//li[@class='social__item']")));

  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
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
