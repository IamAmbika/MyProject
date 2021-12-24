package scripts;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import pages.ETTPage;
import pages.LoginPage;

public class ValidLogin extends BaseTest {

	@Test
	public void verifyValidLogin() {
		
		String un = Excel.getRowData(EXCEL_PATH, "ValidLogin", 1, 0);
		String pw = Excel.getRowData(EXCEL_PATH, "ValidLogin", 1, 1);
		String msg = Excel.getRowData(EXCEL_PATH, "ValidLogin", 1, 2);
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.setUsername(un);
		
		loginPage.setPassword(pw);
		
		loginPage.clickLoginBTN();
		
		ETTPage ettPage = new ETTPage(driver);
		boolean result = ettPage.verifyHMPageTxt();
		Assert.assertTrue(result, msg);
	}
}
