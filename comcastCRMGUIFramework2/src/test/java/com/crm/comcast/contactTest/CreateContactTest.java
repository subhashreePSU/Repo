package com.crm.comcast.contactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.ObjectRepositoryUtility.ContactPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.ContactSupportDateInformation;
import com.comcast.crm.generic.ObjectRepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.generic.ObjectRepositoryUtility.OrganizationsPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.SelectOrgPage;
/**
 * author Subhashree_Panigrahi
 * test class for contact module
 * 
 */
public class CreateContactTest extends BaseClass {

	@Test(groups={"smokeTest","regressionTest"})
	public void createContactTest() throws EncryptedDocumentException, IOException {
		// read data from excelfile
		String LastName = Elib.getDataFromExcel("contact", 1, 2) + Jalib.getRandomNumber();
		// navigateto the contact module
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();
		// click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getContactbutton().click();
		// enter all the details and create contact
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getlastNameEdit().sendKeys(LastName);

		// verify header info with expected result
		String actHeader = cp.getHeaderMsg().getText();
		boolean status = actHeader.contains(LastName);
		Assert.assertEquals(status, true);
		
	}

	@Test(groups={"smokeTest","regressionTest"})
	public void CreateContactWithSupportDateTest() throws EncryptedDocumentException, IOException {
		// read data from excelfile
		String LastName = Elib.getDataFromExcel("contact", 1, 2) + Jalib.getRandomNumber();
		// navigateto the contact module
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();
		// click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getContactplusSign().click();
		// get start date and end date
		String enddate = Jalib.getSystemDateYYYYDDMM();
		String startdate = Jalib.getSystemDateYYYYDDMM(30);
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getlastNameEdit().sendKeys(LastName);
		cncp.getStartDate().sendKeys(startdate);
		cncp.getEndDate().sendKeys(enddate);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		ContactSupportDateInformation csdi = new ContactSupportDateInformation(driver);
		String actstartdate = csdi.getStartdate().getText();
		boolean status = actstartdate.equals(startdate);
		Assert.assertEquals(status, true);
		
		String actenddate = csdi.getEnddate().getText();
		boolean status2 = actenddate.equals(enddate);
		Assert.assertEquals(status2, true);

	}

	@Test(groups={"smokeTest","regressionTest"})
	public void CreateContactWithOrgTest() throws EncryptedDocumentException, IOException, Exception {
		String OrgName = Elib.getDataFromExcel("contact", 7, 2) + Jalib.getRandomNumber();
		String ContactLastName = Elib.getDataFromExcel("contact", 7, 3) + Jalib.getRandomNumber();
		String parent=driver.getWindowHandle();

		// navigate to orglink
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// click on create new org button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgbtn().click();

		// create org
		CreatingNewOrganizationPage cobj = new CreatingNewOrganizationPage(driver);
		cobj.CreateOrg(OrgName);
		
		String actorgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if (actorgName.trim().equals(OrgName)) {
			System.out.println(OrgName + "information is created == PASS");
		} else {
			System.out.println(OrgName + "information is not created ==FAIL");
		}
		
		Thread.sleep(3000);
		// navigate to contact
		ContactPage cp = new ContactPage(driver);
		cp.getContactbutton().click();
		cp.getContactplusSign().click();

		
		// create new organization by passing organization name
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact(ContactLastName,OrgName,"Organization Name");
		driver.switchTo().window(parent);
		cncp.getSaveButton().click();
		
		if (actorgName.equals(OrgName)) {
			System.out.println(OrgName + " information is verified== PASS");
		} else {
			System.out.println(OrgName + " information is not verified==FAIL");
		}


	}
}
