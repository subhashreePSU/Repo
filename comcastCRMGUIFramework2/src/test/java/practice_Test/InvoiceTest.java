package practice_Test;

import org.jspecify.annotations.Nullable;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;

@Listeners(com.comcast.crm.listenerUtility.ListImpClass.class)
public class InvoiceTest extends BaseClass{

	@Test(retryAnalyzer=com.comcast.crm.listenerUtility.RetryListenerImplimentation.class)
	public void CreateInvoiceTest() {
		System.out.println("CreateInvoiceTest");
		@Nullable
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle,"Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
	@Test
	public void CreateInvoiceWithContactTest() {
		System.out.println("CreateInvoiceWithContactTest");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
	}
	
}
