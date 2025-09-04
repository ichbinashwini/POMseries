package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
	
	
	public static final int DEFAULT_SHORT_WAIT = 5;
	public static final int DEFAULT_MEDIUM_WAIT = 10;
	public static final int DEFAULT_LARGE_WAIT = 20;	

	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";

	public static List<String> EXPECTED_ACCCOUNT_PAGE_HEADERS_lIST = List.of("My Account", 		
																	"My Orders", 
																	"My Affiliate Account",
																	"Newsletter");
	public static List<String> EXPECTED_ACCCOUNT_PAGE_RIGHTSIDE_lINKS = List.of ("My Account", "Edit Account", "Password",
																				"Address Book", "Wish List", "Order History",
																				"Downloads", "Recurring payments", "Reward Points",
																				"Returns","Transactions", "Newsletter", "Logout");

public static final String USER_REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";

}
