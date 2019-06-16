@tag 
Feature: ANZ home loan borrow calculator 


@regression 
Scenario: Filling home loan borrow application details 

	Given open browser and navigate to ANZ application 
	
	And select application type as single and number of dependents as zero 
		|WorkBook   |Sheet              |ColumName      |
		|TestData   |HomeLoanBorrowcal  |NoOfDependents | 
		
	And click on property you would like to buy 
	
	And enter all your earnings 
		|WorkBook   |Sheet              |ColumName      |
		|TestData   |HomeLoanBorrowcal  |Income         | 
		|TestData   |HomeLoanBorrowcal  |OtherIncome    |
		
	And enter all your living expenses 
		|WorkBook   |Sheet              |ColumName              |
		|TestData   |HomeLoanBorrowcal  |LivingExpenses         | 
		|TestData   |HomeLoanBorrowcal  |HomeLoanRePayments     |
		|TestData   |HomeLoanBorrowcal  |OtherLoanRePayments    | 
		|TestData   |HomeLoanBorrowcal  |OtherCommitments       |
		|TestData   |HomeLoanBorrowcal  |CreditCardLimits       |
		
	Then click on work out how much I could borrow 
	
	When click on start over 
	
	Then ensure that all fields are cleared 
		|WorkBook   |Sheet              |ColumName                           |
		|TestData   |HomeLoanBorrowcal  |AfterStartOverHomeLoanValue         | 
		
	Then close all windows 
	
	
@regression 
Scenario: validate error messages 



	Given open browser and navigate to ANZ application 
	
	#    And select application type as single and number of dependents as zero
	
	And click on property you would like to buy 
	
	And fill living expense as one and remaining all fields as zero 
		|WorkBook   |Sheet              |ColumName                   |
		|TestData   |HomeLoanBorrowcal  |LivingExpensesAsOne         | 
		
	When click on work out how much I could borrow 
	
	Then system should throw appropriate error messages 
		|WorkBook   |Sheet              |ColumName            |
		|TestData   |HomeLoanBorrowcal  |ErrorText            | 
		
	Then close all windows 
	
  