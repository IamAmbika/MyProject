package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ETTPage {

	@FindBy(xpath = "//td[.='Enter Time-Track']")
	private WebElement hmPageTxt;

	public ETTPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyHMPageTxt() {
		try {
			hmPageTxt.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
