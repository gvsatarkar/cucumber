package stepDefinition;

import java.util.List;
import java.util.Map;

import com.sun.tools.internal.xjc.Driver;

import commonLibrary.CommonLibrary;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ANZBorrowCalculator extends CommonLibrary {

	String USER_APPLICANT_TYPE = "//label[@for='application_type_single'] - XPATH";
	String NUMBER_OF_APPLICANTS = "//select[@title='Number of dependants'] - XPATH";
	String PROPERTY_YOU_LIKE_TO_BUY = "//label[@for='borrow_type_home'] - XPATH";
	String REGULAR_INCOME = "//div//input[@*='q2q1'] - XPATH";
	String OTHER_INCOME = "//div//input[@*='q2q2'] - XPATH";
	String LIVING_EXPENSE = "//div//input[@*='q3q1'] - XPATH";
	String CURRENT_HOME_LOAN = "//div//input[@*='q3q2'] - XPATH";
	String OTHER_LOAN = "//div//input[@*='q3q3'] - XPATH";
	String OTHER_COMMITMENT = "//div//input[@*='q3q4'] - XPATH";
	String CREDIT_CARD_PAYMENT = "//div//input[@*='q3q5'] - XPATH";
	String WORK_OUT_HOW_I_COULD_BORROW = "//button[contains(text(),'Work out how much I could borrow')] - XPATH";
	String CLICK_ON_START_OVER = "//div[3]//div//div//div//div[2]//div[2]//button//span - XPATH";
	String ERROR_TEXT = "//span[@class='borrow__error__text'] - XPATH";

	@Given("^open browser and navigate to ANZ application$")
	public void open_browser_and_navigate_to_ANZ_application() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// Write code here that turns the phrase above into concrete actions
		launchBrowserAndEnterURL();
		Thread.sleep(20000);
	}

	@Given("^select application type as single and number of dependents as zero$")
	public void select_application_type_as_single_and_number_of_dependents_as_zero(DataTable noOfDependents)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		List<Map<String, String>> data = noOfDependents.asMaps(String.class, String.class);
		String noOfDependentApplicants = readExcel(data.get(0).get("WorkBook"), data.get(0).get("Sheet"),
				data.get(0).get("ColumName"));
		click(USER_APPLICANT_TYPE);
		Thread.sleep(5000);
		selectByVisibleText(NUMBER_OF_APPLICANTS, noOfDependentApplicants);

	}

	@Given("^click on property you would like to buy$")
	public void click_on_property_you_would_like_to_buy() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		click(PROPERTY_YOU_LIKE_TO_BUY);
		Thread.sleep(5000);
	}

	@Given("^enter all your earnings$")
	public void enter_all_your_earnings(DataTable typesOfIncomes) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		List<Map<String, String>> data = typesOfIncomes.asMaps(String.class, String.class);
		String regularIncome = readExcel(data.get(0).get("WorkBook"), data.get(0).get("Sheet"),
				data.get(0).get("ColumName"));
		String otherIncome = readExcel(data.get(1).get("WorkBook"), data.get(1).get("Sheet"),
				data.get(1).get("ColumName"));
		enterText(REGULAR_INCOME, regularIncome);
		enterText(OTHER_INCOME, otherIncome);

	}

	@Given("^enter all your living expenses$")
	public void enter_all_your_living_expenses(DataTable expenses) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		List<Map<String, String>> data = expenses.asMaps(String.class, String.class);
		String livingExpense = readExcel(data.get(0).get("WorkBook"), data.get(0).get("Sheet"),
				data.get(0).get("ColumName"));
		String homeLoanExpense = readExcel(data.get(1).get("WorkBook"), data.get(1).get("Sheet"),
				data.get(1).get("ColumName"));
		String otherLoanPayments = readExcel(data.get(2).get("WorkBook"), data.get(2).get("Sheet"),
				data.get(2).get("ColumName"));
		String otherCommitments = readExcel(data.get(3).get("WorkBook"), data.get(3).get("Sheet"),
				data.get(3).get("ColumName"));
		String creditCardPayments = readExcel(data.get(4).get("WorkBook"), data.get(4).get("Sheet"),
				data.get(4).get("ColumName"));
		enterText(LIVING_EXPENSE, livingExpense);
		enterText(CURRENT_HOME_LOAN, homeLoanExpense);
		enterText(OTHER_LOAN, otherLoanPayments);
		enterText(OTHER_COMMITMENT, otherCommitments);
		enterText(CREDIT_CARD_PAYMENT, creditCardPayments);

	}

	@Then("^click on work out how much I could borrow$")
	public void click_on_work_out_how_much_I_could_borrow() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		click(WORK_OUT_HOW_I_COULD_BORROW);
		Thread.sleep(5000);
	}

	@When("^click on start over$")
	public void click_on_start_over() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		click(CLICK_ON_START_OVER);
		Thread.sleep(5000);
	}

	@Then("^ensure that all fields are cleared$")
	public void ensure_that_all_fields_are_cleared(DataTable afterClickOnStartOver) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		List<Map<String, String>> data = afterClickOnStartOver.asMaps(String.class, String.class);
		String afterClickOnStartOverButton = readExcel(data.get(0).get("WorkBook"), data.get(0).get("Sheet"),
				data.get(0).get("ColumName"));
		getText(afterClickOnStartOverButton, CURRENT_HOME_LOAN);
	}

	@Given("^fill living expense as one and remaining all fields as zero$")
	public void fill_living_expense_as_one_and_remaining_all_fields_as_zero(DataTable livingExpenseAsOne)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		List<Map<String, String>> data = livingExpenseAsOne.asMaps(String.class, String.class);
		String livingExpense = readExcel(data.get(0).get("WorkBook"), data.get(0).get("Sheet"),
				data.get(0).get("ColumName"));
		enterText(LIVING_EXPENSE, livingExpense);
	}

	@Then("^system should throw appropriate error messages$")
	public void system_should_throw_appropriate_error_messages(DataTable errorValidation) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		List<Map<String, String>> data = errorValidation.asMaps(String.class, String.class);
		String errorTextValidation = readExcel(data.get(0).get("WorkBook"), data.get(0).get("Sheet"),
				data.get(0).get("ColumName"));
		Thread.sleep(5000);
		verifyExpected(ERROR_TEXT, errorTextValidation);
	}

	@Then("^close all windows$")
	public void close_all_windows() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		closeAllWindows();
	}

}
