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
import pages.BasePage;
import pages.RegionPage;

public class Insurance01Test extends BaseTest {


    @Test
    public void testInsuranceTest01() throws Exception {
        driver.get(baseUrl + "/ru/person");
        RegionPage regionPage = new RegionPage(driver);

        wait.until(ExpectedConditions.visibilityOf(regionPage.searchField));
        regionPage.searchField.click();

        driver.findElement(By.linkText("Нижегородская область")).click();

        wait.until(ExpectedConditions.visibilityOf(regionPage.title));
        assertEquals("Нижегородская область", regionPage.title.getText());

        wait.until(ExpectedConditions.visibilityOf(regionPage.icons));
        regionPage.scrollToElement(regionPage.icons);
        assertTrue(regionPage.checkIcons());
    }


}
