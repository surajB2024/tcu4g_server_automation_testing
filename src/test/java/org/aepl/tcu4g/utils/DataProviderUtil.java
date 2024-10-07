package org.aepl.tcu4g.utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {

	@DataProvider(name = "loginData")
	public Object[][] provideLoginData() {
		return new Object[][] {
			{ "suraj.bhalerao@accoladeelectronics.com", "cqf9tnvl" },
		};
	}
}
