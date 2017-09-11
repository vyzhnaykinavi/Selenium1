package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    @FindBy(xpath = "//ul[@class='alt-menu-mid__list']")
    private WebElement menuItems;

    @FindBy(xpath = "//div[contains(@class,' banner-group-carousel')]")
    public WebElement menuBlock;
    @FindBy(css = ".sbrf-rich-outer>h1")
    public WebElement title;
    @FindBy(css = "p > a > img")
    public WebElement InsuranceBanner;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void selectMenuItem(String itemName) {
        menuItems.findElement(By.partialLinkText(itemName)).click();
    }
}
