package com.opencart.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.Constants.AppConstants;
import com.opencart.base.BaseTest;

public class NegativeRegistrationPgTest extends BaseTest {
	
	@BeforeClass
	public void RegisterPageSetup() {
		registerpg = loginpg.navigateToRegisterPg();
	}

	@DataProvider
	public Object [][] getTestData() {
		return new Object[][] {{"","","","",""}};
	}
	
	@Test(dataProvider="getTestData")
	public void NegativeTestcaseRegistrationTest(String firstName, String lastName,String email, 
			 String telephone, String password) {
		
		 registerpg.NegativeTestcaseRegistration(firstName, lastName, email, telephone, password);
		 List<String> exceptedlistmess = registerpg.NegativeMessages();

		 AssertJUnit.assertEquals(registerpg.FirstNameMess(),exceptedlistmess.get(0));
		 AssertJUnit.assertEquals(registerpg.LastNameMess(),exceptedlistmess.get(1));
		 AssertJUnit.assertEquals(registerpg.EmailMess(),exceptedlistmess.get(2));
		 AssertJUnit.assertEquals(registerpg.TelephoneMess(),exceptedlistmess.get(3));
		 AssertJUnit.assertEquals(registerpg.PasswordMess(),exceptedlistmess.get(4));
		 AssertJUnit.assertEquals(registerpg.PrivacyMess(),AppConstants.USER_REG_FAILURE_MESSG);
		 softAssert.assertAll();
	}

}
