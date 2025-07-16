package com.crm.comcast.orgTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.ObjectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.generic.ObjectRepositoryUtility.OrganizationInformationPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.OrganizationsPage;
import com.comcast.crm.generic.WebDriverUtility.UtilityClassObject;

/**
 * author Subhashree_Panigrahi test class for organization module
 * 
 */
@Listeners(com.comcast.crm.listenerUtility.ListImpClass.class)
public class CreateOrgTest extends BaseClass {

	@Test(groups = { "smokeTest", "regressionTest" })
	public void createOrgTest() throws Exception {
		UtilityClassObject.getTest().log(Status.INFO, "Read Data From Excel");
		String orgName = Elib.getDataFromExcel("org", 1, 2) + Jalib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to org Page");
		hp.getOrglink().click();

		// click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create org page");
		op.getCreateNewOrgbtn().click();

		// enter all details and create organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		UtilityClassObject.getTest().log(Status.INFO, "Create a new org");
		cnop.CreateOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO, orgName + "----created------");

		// verify header msg expected result
		OrganizationInformationPage Oip = new OrganizationInformationPage(driver);
		String actOrgName = Oip.getHeaderMessage().getText();

		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " is create == PASS");
		} else {
			System.out.println(orgName + " is not create == FAIL");
		}

		// hp.logout();

	}

	@Test(groups = { "smokeTest", "regressionTest" })
	public void CreateOrganizationWithIndustriesTest() throws EncryptedDocumentException, IOException {

		String orgName = Elib.getDataFromExcel("org", 4, 2) + Jalib.getRandomNumber();
		String industry = Elib.getDataFromExcel("org", 4, 3) ;
		String type = Elib.getDataFromExcel("org", 4, 4);

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgbtn().click();

		// enter all details and create organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		// we have to select from dropdown
		cnop.CreateOrg(orgName, industry, type);
		cnop.getSaveButton().click();

		// verify header msg expected result
		OrganizationInformationPage Oip = new OrganizationInformationPage(driver);
		String actOrgName = Oip.getHeaderMessage().getText();

		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " is create == PASS");
		} else {
			System.out.println(orgName + " is not create == FAIL");
		}
		String actualorgName = Oip.getHeaderConfirmatio().getText();
		if (actualorgName.trim().equals(orgName)) {
			System.out.println(orgName + " information is created == PASS");
		} else {
			System.out.println(orgName + " information is not created  == FAIL");
		}
		// driver.quit();

	}

	@Test(groups = { "smokeTest", "regressionTest" })
	public void CreateOrgWithPhoneNumberTest() throws EncryptedDocumentException, IOException {

		String orgName = Elib.getDataFromExcel("org", 6, 2) + Jalib.getRandomNumber();
		String phno = Elib.getDataFromExcel("org", 6, 3) + Jalib.getRandomNumber();

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgbtn().click();

		// enter all details and create organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.CreateOrg(orgName);
		// cnop.getphoneNoTextField().sendKeys(phno);
		// cnop.getSaveButton().click();
		// verify header msg expected result
		OrganizationInformationPage Oip = new OrganizationInformationPage(driver);
		String actOrgName = Oip.getHeaderMessage().getText();

		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " header verified == PASS");
		} else {
			System.out.println(orgName + " header is verified == FAIL");
		}

		String actualorgName = Oip.getHeaderConfirmatio().getText();
		if (actualorgName.trim().equals(orgName)) {
			System.out.println(orgName + " information is created == PASS");
		} else {
			System.out.println(orgName + " information is not created  == FAIL");
		}
		// driver.quit();
	}

}
