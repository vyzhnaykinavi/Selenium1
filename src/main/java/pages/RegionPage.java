package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegionPage extends BasePage {

    @FindBy(css = "span.region-list__arrow")
    public WebElement searchField;

    @FindBy(xpath = "//span[@class='region-list__name']")
    public WebElement title;

    @FindBy(xpath = "//li[@class='social__item']")
    public WebElement icons;

    public RegionPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public Boolean checkIcons(){
        try {
            driver.findElement(By.xpath("//li[@class='social__item']"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
