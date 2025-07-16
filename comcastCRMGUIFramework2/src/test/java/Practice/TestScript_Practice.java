package Practice;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;

@Listeners(Practice.Listener_Practice.class)
public class TestScript_Practice extends Baseclass_practice {
	

	@Test(priority =0)
	public void GoodMorning() {
		System.out.println("execute GoodMorning");
	}
	
	@Test(priority =1)
	public void GoodAfternoon() {
		System.out.println("execute GoodAfternoon");
	}
	
	@Test(priority =2)
	public void GoodNight() {
		System.out.println("execute GoodNight");
	}


}
